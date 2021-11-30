/*******************************************************************************
 * Copyright (c) 2020 IAAS, University of Stuttgart.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package resus.licenseengine.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spdx.rdfparser.license.SpdxListedLicense;
import org.spdx.rdfparser.model.SpdxDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import resus.licenseengine.app.model.License;
import resus.licenseengine.app.model.ProcessingStatus;
import resus.licenseengine.app.model.Software;
import resus.licenseengine.fossology.client.FossologyClient;
import resus.licenseengine.recommender.client.LicenseRecommenderClient;
import resus.licenseengine.utils.LicenseUtils;

@Component
public class LicenseEngine {

	private static final Logger logger = LoggerFactory.getLogger(LicenseEngine.class);

	private static String fossologyEndpoint;
	private static String fossologyUsername;
	private static String fossologyPassword;
	private static FossologyClient fossologyClient = null;
	private static String licenseRecommenderEndpoint;
	private static LicenseRecommenderClient licenseRecommenderClient = null;

	private static Map<String, Software> softwareMap = new HashMap<String, Software>();

	/**
	 * @param fossologyEndpoint
	 * @param fossologyUsername
	 * @param fossologyPassword
	 * @param licenseRecommenderEndpoint
	 */
	public LicenseEngine(@Value("${fossology.endpoint}") String fossologyEndpoint,
			@Value("${fossology.username}") String fossologyUsername,
			@Value("${fossology.password}") String fossologyPassword,
			@Value("${license.recommender.endpoint.path}") String licenseRecommenderEndpoint) {

		LicenseEngine.fossologyEndpoint = fossologyEndpoint;
		LicenseEngine.fossologyUsername = fossologyUsername;
		LicenseEngine.fossologyPassword = fossologyPassword;
		LicenseEngine.licenseRecommenderEndpoint = licenseRecommenderEndpoint;
	}

	/**
	 * 
	 * Processes the given software. Uploads the files, checks them for licenses,
	 * and creates a report.
	 * 
	 * @param software to be processed.
	 */
	public static void startProcessing(Software software) {

		String id = software.getId();

		logger.info("Starting the processing of a new software with ID: {} ...", id);

		software.setStatus(ProcessingStatus.UPLOADING);

		Integer uploadID = null;
		if (software.getBranch() != null) {
			uploadID = fossologyClient.uploadVCS(software.getUrl(), software.getBranch(), software.getName());
		} else {
			uploadID = fossologyClient.uploadURL(software.getUrl(), software.getName());
		}

		if (uploadID != null && fossologyClient.waitForUploadJob(uploadID)) {

			software.setStatus(ProcessingStatus.ANALYZING);

			Integer jobID = fossologyClient.startAnalyzeJob(uploadID);

			if (fossologyClient.waitForAnalyzeJob(jobID)) {

				Integer reportID = fossologyClient.createReport(uploadID);

				if (fossologyClient.waitForReportJob(reportID)) {

					File reportFile = fossologyClient.getReport(reportID);

					SpdxDocument spdxDocument = LicenseUtils.getSpdxDocumentFromFile(reportFile);

					if (spdxDocument != null) {

						Map<String, List<String>> licenseFilesMapping = LicenseUtils.getLicensesFilesMap(spdxDocument);
						Integer filesCount = LicenseUtils.getFilesCount(spdxDocument);

						software.setLicenseFilesMapping(licenseFilesMapping);
						software.setFiles(filesCount);

						logger.info("Processing of the new software with ID: {} finished.", id);
						software.setStatus(ProcessingStatus.FINISHED);

					} else {
						software.setStatus(ProcessingStatus.FAILED);
						logger.error("Created report can't be read! Software: {}", id);
					}

				} else {
					software.setStatus(ProcessingStatus.FAILED);
					logger.error("Creating report failed! Software: {}", id);
				}

			} else {
				software.setStatus(ProcessingStatus.FAILED);
				logger.error("Analyzing failed! Software: {}", id);
			}

		} else {
			software.setStatus(ProcessingStatus.FAILED);
			logger.error("Upload failed! Software: {}", id);
		}
	}

	/**
	 * @param id
	 * @return software with the given id. Or null, if no software can be found for
	 *         it.
	 */
	public static Software getSoftware(String id) {
		return softwareMap.get(id);
	}

	/**
	 * @param id       of the software to be added and processed.
	 * @param software to be processed.
	 * @return
	 */
	public static boolean addSoftware(String id, final Software software) {

		if (softwareMap.containsKey(id)) {
			logger.warn("A software with the provided ID: {} is already processed.", id);
			return false;
		}

		software.setStatus(ProcessingStatus.QUEUED);
		softwareMap.put(id, software);

		new Thread() {
			public void run() {
				LicenseEngine.startProcessing(software);
			}
		}.start();

		return true;
	}

	/**
	 * 
	 * @return if a fossology instance is running and can be accessed.
	 */
	public static boolean isFossologyAvailable() {

		if (fossologyClient == null) {
			try {
				fossologyClient = new FossologyClient(fossologyEndpoint, fossologyUsername, fossologyPassword);
			} catch (Exception e) {
				logger.warn(e.toString());
			}
		}

		if (fossologyClient != null) {
			if (fossologyClient.isAvailable()) {
				return true;
			}
		}
		logger.error("No running fossology instance for checking the licenses can be found or is accessible.");
		return false;
	}

	/**
	 * 
	 * @return if a license recommender service is running and can be accessed.
	 */
	public static boolean isLicenseRecommenderAvailable() {

		if (licenseRecommenderClient == null) {
			licenseRecommenderClient = new LicenseRecommenderClient(licenseRecommenderEndpoint);
		}

		if (licenseRecommenderClient != null) {
			if (licenseRecommenderClient.isAvailable()) {
				return true;
			}
		}
		logger.error("No running license recommender service can be found or is accessible.");
		return false;
	}

	/**
	 * 
	 * @param id of the software to be deleted.
	 */
	public static void deleteSoftware(String id) {

		softwareMap.remove(id);
		logger.info("Software with ID: {} was deleted.", id);
	}

	/**
	 * 
	 * @param software, for which licenses should be recommended.
	 * 
	 * @return recommended licenses.
	 */
	public static List<String> getRecommendedLicenses(Software software) {

		List<String> recommendedLicenses = null;
		Set<String> effectiveLicenses = software.getEffectiveLicenses();
		try {
			recommendedLicenses = licenseRecommenderClient.getCompatibleLicenses(effectiveLicenses);
		} catch (Exception e) {
			logger.error(e.toString());
			logger.warn("Can't recommend compatible licenses based on the selected licenses: {}",
					software.getEffectiveLicenses());
		}
		return recommendedLicenses;
	}

	/**
	 * 
	 * @param licenseID
	 * 
	 * @return License object with all relevant information.
	 */
	public static License getLicense(String licenseID) {

		License license = null;
		ArrayList<String> urls;

		SpdxListedLicense spdxLicense = LicenseUtils.getLicense(licenseID);

		if (spdxLicense != null) {

			urls = new ArrayList<String>(Arrays.asList(spdxLicense.getSeeAlso()));

			String url = LicenseUtils.getChooseALicenseURL(licenseID);

			if (url != null) {
				urls.add(url);
			}

			license = new License();
			license.setId(licenseID);
			license.setName(spdxLicense.getName());
			license.setNotes(spdxLicense.getComment());
			license.setFurtherInformation(urls);
		}

		return license;
	}

}
