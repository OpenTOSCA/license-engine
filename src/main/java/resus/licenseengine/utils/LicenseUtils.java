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

package resus.licenseengine.utils;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spdx.html.InvalidLicenseTemplateException;
import org.spdx.rdfparser.InvalidSPDXAnalysisException;
import org.spdx.rdfparser.SPDXDocumentFactory;
import org.spdx.rdfparser.license.AnyLicenseInfo;
import org.spdx.rdfparser.license.LicenseInfoFactory;
import org.spdx.rdfparser.license.SpdxListedLicense;
import org.spdx.rdfparser.model.SpdxDocument;
import org.spdx.rdfparser.model.SpdxFile;

public class LicenseUtils {

	private static final Logger logger = LoggerFactory.getLogger(LicenseUtils.class);

	/**
	 * 
	 * @param file to be parsed
	 * 
	 * @return a {@link org.spdx.rdfparser.model.SpdxDocument} object based on the
	 *         given file
	 * 
	 */
	public static SpdxDocument getSpdxDocumentFromFile(File file) {

		logger.debug("Creating a SPDX-Document from file: {}...", file.getAbsolutePath());

		try {
			SpdxDocument spdxDocument = SPDXDocumentFactory.createSpdxDocument(file.getAbsolutePath());
			return spdxDocument;
		} catch (IOException | InvalidSPDXAnalysisException e) {
			logger.warn("Something went wrong while parsing the report file: {}.", e.toString());
		}
		return null;
	}

	/**
	 * 
	 * @param spdxDocument the {@link org.spdx.rdfparser.model.SpdxDocument} to be
	 *                     checked
	 * 
	 * @return the amount of files contained in the
	 *         {@link org.spdx.rdfparser.model.SpdxDocument} object.
	 */
	public static Integer getFilesCount(SpdxDocument spdxDocument) {

		logger.debug("Determining the amount of checked files...");

		try {
			return spdxDocument.getDocumentContainer().getFileReferences().length;

		} catch (InvalidSPDXAnalysisException e) {
			logger.warn("Something went wrong: {}.", e.toString());
		}
		return null;
	}

	/**
	 * 
	 * @param spdxDocument {@link org.spdx.rdfparser.model.SpdxDocument}
	 * 
	 * @return a map, containing all files contained in the
	 *         {@link org.spdx.rdfparser.model.SpdxDocument} object as the key, and
	 *         a list with the corresponding licenses as the value.
	 * 
	 * @throws InvalidSPDXAnalysisException
	 */
	public static Map<String, List<String>> getFileLicensesMap(SpdxDocument spdxDocument)
			throws InvalidSPDXAnalysisException {

		logger.debug("Creating the files to licenses mapping...");

		Map<String, List<String>> filesLicensesMapping = new HashMap<String, List<String>>();

		for (SpdxFile file : spdxDocument.getDocumentContainer().getFileReferences()) {

			String fileName = file.getName();

			AnyLicenseInfo[] licenses = file.getLicenseInfoFromFiles();
			List<String> licenseList = new ArrayList<String>();

			for (AnyLicenseInfo license : licenses) {
				licenseList.add(license.toString());
			}

			filesLicensesMapping.put(fileName, licenseList);

		}

		return filesLicensesMapping;
	}

	/**
	 * 
	 * @param spdxDocument {@link org.spdx.rdfparser.model.SpdxDocument}
	 * 
	 * @return a map, containing all licenses and their mappings to all
	 *         corresponding files contained in the
	 *         {@link org.spdx.rdfparser.model.SpdxDocument} object.
	 */
	public static Map<String, List<String>> getLicensesFilesMap(SpdxDocument spdxDocument) {

		logger.debug("Creating the licenses to files mapping...");

		Map<String, List<String>> licensesFilesMapping = new HashMap<String, List<String>>();
		String licenseString;

		try {
			for (SpdxFile file : spdxDocument.getDocumentContainer().getFileReferences()) {

				AnyLicenseInfo[] licenses = file.getLicenseInfoFromFiles();

				for (AnyLicenseInfo license : licenses) {

					licenseString = license.toString();

					if (licensesFilesMapping.containsKey(licenseString)) {

						licensesFilesMapping.get(licenseString).add(file.getName());

					} else {
						ArrayList<String> fileList = new ArrayList<String>();
						fileList.add(file.getName());
						licensesFilesMapping.put(licenseString, fileList);
					}
				}
			}
		} catch (InvalidSPDXAnalysisException e) {
			logger.warn("Something went wrong while analyzing the license report: {}.", e.toString());
			return null;
		}

		return licensesFilesMapping;
	}

	/**
	 * 
	 * @param licenseID
	 * 
	 * @return the corresponding license text to the given licenseID
	 * 
	 */
	public static String getLicenseText(String licenseID) {

		logger.debug("Returning the plain text for the license: {}...", licenseID);

		try {
			return LicenseInfoFactory.getListedLicenseById(licenseID).getLicenseText();
		} catch (InvalidSPDXAnalysisException e) {
			logger.warn("Can't find license with ID: {}", licenseID);
		}
		return null;
	}

	/**
	 * 
	 * @param licenseID
	 * 
	 * @return the corresponding license text as html to the given licenseID
	 * 
	 */
	public static String getLicenseTextHtml(String licenseID) {

		logger.debug("Returning the text as html for the license: {}...", licenseID);

		try {
			return LicenseInfoFactory.getListedLicenseById(licenseID).getLicenseTextHtml();
		} catch (InvalidLicenseTemplateException | InvalidSPDXAnalysisException e) {
			logger.warn("Can't find license with ID: {}", licenseID);
		}
		return null;
	}

	/**
	 * @return all available licenseIDs
	 */
	public static String[] getAllLicenses() {
		logger.debug("Returning all available licenses IDs...");
		return LicenseInfoFactory.getSpdxListedLicenseIds();
	}

	/**
	 * @return SpdxListedLicense object of license with the given licenseID
	 */
	public static SpdxListedLicense getLicense(String licenseID) {

		logger.debug("Returning the license with ID: {}...", licenseID);

		try {
			return LicenseInfoFactory.getListedLicenseById(licenseID);
		} catch (InvalidSPDXAnalysisException e) {
			logger.warn("Can't find license with ID: {}", licenseID);
		}
		return null;
	}

	/**
	 * @param licenseID
	 * 
	 * @return URL to choosealicense.com site of the specified license. Otherwise
	 *         null
	 */
	public static String getChooseALicenseURL(String licenseID) {

		logger.debug("Checking for a choosealicense.com URL for the license: {}...", licenseID);

		try {
			URL url = new URL("https://choosealicense.com/licenses/" + licenseID.toLowerCase());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			if (con.getResponseCode() == 200) {
				return url.toString();
			}
		} catch (IOException e) {
			logger.debug("License {} is not supported by choosealicense.com.", licenseID);
		}
		return null;
	}

}
