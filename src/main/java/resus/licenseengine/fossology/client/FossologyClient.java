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

package resus.licenseengine.fossology.client;

import java.io.File;
import java.net.ConnectException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import resus.licenseengine.fossology.api.DefaultApi;
import resus.licenseengine.fossology.api.JobApi;
import resus.licenseengine.fossology.api.ReportApi;
import resus.licenseengine.fossology.api.UploadApi;
import resus.licenseengine.fossology.model.Analysis;
import resus.licenseengine.fossology.model.DefaultResponse;
import resus.licenseengine.fossology.model.Info;
import resus.licenseengine.fossology.model.InlineResponse200;
import resus.licenseengine.fossology.model.Job;
import resus.licenseengine.fossology.model.LicenseDecider;
import resus.licenseengine.fossology.model.ScanOptions;
import resus.licenseengine.fossology.model.TokenRequest;
import resus.licenseengine.fossology.model.TokenRequest.TokenScopeEnum;
import resus.licenseengine.fossology.model.UrlUpload;
import resus.licenseengine.fossology.model.VcsUpload;

public class FossologyClient {

	private static final Logger logger = LoggerFactory.getLogger(FossologyClient.class);

	private DefaultApi defaultApi;
	private UploadApi uploadApi;
	private JobApi jobApi;
	private ReportApi reportApi;
	private String token;

	/**
	 * 
	 * Creates a new client for accessing fossology.
	 * 
	 * @param endpoint of fossology
	 * @param username for accessing fossology
	 * @param password for accessing fossology
	 * @throws ConnectException
	 */
	public FossologyClient(String endpoint, String username, String password) throws Exception {

		logger.debug("Creating new fossology client for endpoint: {}, username: {}, and password: {} ....", endpoint,
				username, password);

		JacksonJsonProvider provider = new JacksonJsonProvider();
		List<JacksonJsonProvider> providers = new ArrayList<JacksonJsonProvider>();
		providers.add(provider);
		defaultApi = JAXRSClientFactory.create(endpoint, DefaultApi.class, providers);
		uploadApi = JAXRSClientFactory.create(endpoint, UploadApi.class, providers);
		jobApi = JAXRSClientFactory.create(endpoint, JobApi.class, providers);
		reportApi = JAXRSClientFactory.create(endpoint, ReportApi.class, providers);
		token = createToken(username, password);
	}

	/**
	 * 
	 * @return if a fossology instance is running and can be accessed.
	 */
	public boolean isAvailable() {

		logger.debug("Checking if a fossology instance is running and can be accessed...");
		String version = null;

		try {
			InlineResponse200 response = defaultApi.versionGet();
			version = response.getVersion();
		} catch (Exception e) {
			logger.warn("Can't find a running fossology instance that can be accessed.");
			logger.debug(e.toString());
			return false;
		}

		if (version != null) {
			logger.debug("A fossology instance is running and can be accessed. API version: {}", version);
			return true;
		}
		logger.warn("Can't find a running fossology instance that can be accessed.");
		return false;
	}

	/**
	 * 
	 * Creates an authorization token for accessing fossology.
	 * 
	 * @param username for accessing fossology
	 * @param password for accessing fossology
	 * @return the token
	 */
	private String createToken(String username, String password) throws Exception {

		logger.debug("Creating an authorization token for accessing fossology...");

		TokenRequest tokenreq = new TokenRequest();
		tokenreq.setPassword(username);
		tokenreq.setUsername(password);
		tokenreq.setTokenName(UUID.randomUUID().toString());
		tokenreq.setTokenScope(TokenScopeEnum.WRITE);
		tokenreq.setTokenExpire(LocalDate.now().plusDays(30).toString());

		DefaultResponse response = defaultApi.tokensPost(tokenreq);
		String token = response.getAuthorization();

		logger.debug("Authorization token created: {}", token);

		return token;
	}

	/**
	 * 
	 * Uploads a software from a git repository to fossology.
	 * 
	 * @param vcsUrl      of the repository
	 * @param vcsBranch   of the repository to be used
	 * @param description
	 * @return the ID of this upload job
	 */
	public Integer uploadVCS(String vcsUrl, String vcsBranch, String description) {

		VcsUpload vcsUpload = new VcsUpload();
		vcsUpload.setVcsBranch(vcsBranch);
		vcsUpload.setVcsName(description);
		vcsUpload.setVcsType(VcsUpload.VcsTypeEnum.GIT);
		vcsUpload.setVcsUrl(vcsUrl);

		Info info = uploadApi.uploadsPost(token, 1, description, "public", true, null, "vcs", vcsUpload);

		Integer id = Integer.parseInt(info.getMessage());

		logger.debug("Uploading software...: {} from: {}. JobID: {}", description, vcsUrl, id);

		return id;
	}

	/**
	 * 
	 * Uploads a software from a given URL to fossology.
	 * 
	 * @param url         where the software is located
	 * @param description
	 * @return the ID of this upload job
	 */
	public Integer uploadURL(String url, String description) {

		UrlUpload urlUpload = new UrlUpload();
		urlUpload.setName(description);
		urlUpload.setUrl(url);

		Info info = uploadApi.uploadsPost(token, 1, description, "public", true, null, "url", urlUpload);

		Integer id = Integer.parseInt(info.getMessage());

		logger.debug("Uploading software...: {} from: {}. JobID: {} ", description, url, id);

		return id;
	}

	/**
	 * 
	 * Starts the analyzing job in fossology for the given uploadID.
	 * 
	 * @param uploadID
	 * @return the ID of this analyze job
	 */
	public Integer startAnalyzeJob(Integer uploadID) {

		Analysis analysisConfig = new Analysis();
		analysisConfig.setBucket(false);
		analysisConfig.setCopyrightEmailAuthor(true);
		analysisConfig.setEcc(false);
		analysisConfig.setKeyword(false);
		analysisConfig.setMime(false);
		analysisConfig.setMonk(true);
		analysisConfig.setNomos(true);
		analysisConfig.setOjo(true);
		analysisConfig.setPackage(true);

		LicenseDecider deciderConfig = new LicenseDecider();
		deciderConfig.setBulkReused(true);
		deciderConfig.setNewScanner(true);
		deciderConfig.setNomosMonk(true);
		deciderConfig.setOjoDecider(true);

		ScanOptions scanOptions = new ScanOptions();
		scanOptions.setAnalysis(analysisConfig);
		scanOptions.setDecider(deciderConfig);

		Info info = jobApi.jobsPost(token, 1, uploadID, null, scanOptions);
		Integer id = Integer.parseInt(info.getMessage());

		logger.debug("Starting analyzing the uploaded software with ID: {}. AnlyzeJobID: {}", uploadID, id);

		return id;
	}

	/**
	 * 
	 * Waits till the given job is finished.
	 * 
	 * @param uploadJobID
	 * @param jobID
	 * @return true if the job finished successfully. Otherwise false.
	 */
	private boolean waitForJob(Integer uploadJobID, Integer jobID) {

		Job job = null;
		int eta;
		String status;

		do {
			if (uploadJobID != null) {
				List<Job> jobs = jobApi.jobsGet(token, null, null, uploadJobID);
				if (!jobs.isEmpty()) {
					job = jobs.get(0);
				}
			} else if (jobID != null) {
				job = jobApi.jobsIdGet(token, jobID);
			}

			eta = job.getEta();
			status = job.getStatus();

			try {
				Thread.sleep((eta / 4 + 5) * 1000); // wait between 5 seconds and eta/4 seconds
			} catch (InterruptedException e) {
				throw new RuntimeException("Unexpected interrupt", e);
			}
		} while (status.equals("Processing") || status.equals("Queued"));

		if (status.equals("Completed")) {
			return true;
		}

		return false;

	}

	/**
	 * 
	 * Waits till the upload job is finished.
	 * 
	 * @param jobID
	 * @return true if the upload was successfully. Otherwise false.
	 */
	public boolean waitForUploadJob(Integer jobID) {

		logger.debug("Waiting for finishing the upload job with ID: {}", jobID);
		return waitForJob(jobID, null);
	}

	/**
	 * 
	 * Waits till the analyze job is finished.
	 * 
	 * @param jobID
	 * @return true if the analyzing was successfully. Otherwise false.
	 */
	public boolean waitForAnalyzeJob(Integer jobID) {

		logger.debug("Waiting for finishing the analyze job with ID: {}", jobID);
		return waitForJob(null, jobID);
	}

	/**
	 * 
	 * Waits till the report creation job is finished.
	 * 
	 * @param jobID
	 * @return true if the report creation was successfully. Otherwise false.
	 */
	public boolean waitForReportJob(Integer jobID) {

		logger.debug("Waiting for finishing the report job with ID: {}", jobID);
		return waitForJob(null, jobID);
	}

	/**
	 * 
	 * Creates a report for the given uploadID in fossology.
	 * 
	 * @param uploadID
	 * @return the ID of the report to be created.
	 */
	public Integer createReport(Integer uploadID) {

		Info info = reportApi.reportGet(token, uploadID, "spdx2", null);
		String infoMessage = info.getMessage();
		Integer id = Integer.parseInt(infoMessage.substring(infoMessage.lastIndexOf("/") + 1));

		logger.debug("ID of the requested report for the upload with ID {}: {}", uploadID, id);

		return id;
	}

	/**
	 * 
	 * 
	 * @param reportID
	 * @return the report with the given ID from fossology.
	 */
	public File getReport(Integer reportID) {

		logger.debug("Getting the report with ID: uploadID", reportID);
		return reportApi.reportIdGet(token, reportID, null);

	}

}
