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

package resus.licenseengine.app.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import resus.licenseengine.app.LicenseEngine;
import resus.licenseengine.app.model.ProcessingStatus;
import resus.licenseengine.app.model.Software;

@RestController
@RequestMapping(value = "${server.endpoints.software.path}", consumes = { MediaType.APPLICATION_JSON }, produces = {
		MediaType.APPLICATION_JSON })
public class SoftwareController {

	@Value("${server.endpoints.software.path}")
	private String softwareEndpoint;

	private static final Logger logger = LoggerFactory.getLogger(SoftwareController.class);

	/**
	 * Creates a new software.
	 *
	 */
	@PostMapping(produces = { MediaType.TEXT_PLAIN })
	@Operation(summary = "Adds a new software for checking for licenses.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", headers = {
			@Header(name = HttpHeaders.LOCATION, description = "URL to check the status of the request") }, content = @Content, description = "OK. Request is queued for processing. Check location header."),
			@ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = Void.class)), description = "Request can't be processed. Check the response message for more information."),
			@ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Void.class)), description = "Some unexpected error occurred. Check the response message for more information.") })
	public ResponseEntity<String> createSoftware(@RequestBody final Software software) {

		if (!LicenseEngine.isFossologyAvailable()) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"No running fossology instance for checking the licenses can be found or is accessible. Processing aborted!");
		}

		String softwareID = software.getId();

		logger.debug("Creating new software with ID {}...", softwareID);

		if (LicenseEngine.addSoftware(softwareID, software)) {
			logger.debug("A new software with ID {} was created.", softwareID);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", softwareEndpoint + "/status/" + softwareID);
			return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);

		}
		logger.debug("A software with ID {} is already available.", softwareID);
		throw new ResponseStatusException(HttpStatus.CONFLICT, "A software with the given ID was already created!");

	}

	/**
	 * Returns the processing status of the software with the given ID.
	 *
	 */
	@GetMapping(path = "/status/{software-id}", consumes = { MediaType.WILDCARD })
	@Operation(summary = "Returns the processing status of the software with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "303", headers = {
					@Header(name = HttpHeaders.LOCATION, description = "URL to the created software resource") }, content = @Content, description = "Finished. Check location header!"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found") })
	public ResponseEntity<ProcessingStatus> getStatus(@PathVariable("software-id") String softwareID) {

		logger.debug("Requesting the processing status of the software with ID {}...", softwareID);

		Software software = LicenseEngine.getSoftware(softwareID);

		if (software != null) {
			ProcessingStatus status = software.getStatus();
			if (status == ProcessingStatus.FINISHED || status == ProcessingStatus.FAILED) {
				return ResponseEntity.status(HttpStatus.SEE_OTHER)
						.header(HttpHeaders.LOCATION, softwareEndpoint + "/" + softwareID).build();
			}

			return ResponseEntity.ok(software.getStatus());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	/**
	 * Returns the software with the given ID.
	 *
	 */
	@GetMapping(path = "/{software-id}", consumes = { MediaType.WILDCARD })
	@Operation(summary = "Returns the software with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found") })
	public ResponseEntity<Software> getSoftware(@PathVariable("software-id") String softwareID) {

		logger.debug("Requesting the software with ID {}...", softwareID);

		Software software = LicenseEngine.getSoftware(softwareID);
		if (software != null) {
			return ResponseEntity.ok(software);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	/**
	 * Returns the recommended licenses for the software with the given ID.
	 *
	 */
	@GetMapping(path = "/{software-id}/recommended-licenses", consumes = { MediaType.WILDCARD })
	@Operation(summary = "Returns the recommended licenses for the software with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
			@ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Void.class)), description = "Some unexpected error occurred. Check the response message for more information.") })
	public ResponseEntity<List<String>> getRecommendedLicenses(@PathVariable("software-id") String softwareID) {

		if (!LicenseEngine.isLicenseRecommenderAvailable()) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"No running license recommender service can be found or is accessible. Processing aborted!");
		}

		logger.debug("Requesting the recommended licenses for the software with ID {}...", softwareID);

		Software software = LicenseEngine.getSoftware(softwareID);
		if (software != null) {
			List<String> recommendedLicenses = LicenseEngine.getRecommendedLicenses(software);
			if (recommendedLicenses != null) {
				return ResponseEntity.ok(recommendedLicenses);
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Can't recommend compatible licenses based on the selected licenses: "
								+ software.getEffectiveLicenses());
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	/**
	 * Deletes the software with the given ID.
	 *
	 */
	@DeleteMapping("/{software-id}")
	@Operation(summary = "Deletes the software with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", content = @Content, description = "No Content") })
	public ResponseEntity<Void> deleteSoftware(@PathVariable("software-id") String softwareID) {

		logger.debug("Deleting the software with ID {}...", softwareID);

		LicenseEngine.deleteSoftware(softwareID);
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);
	}

	/**
	 * Returns all found licenses and files of the software with the given ID.
	 *
	 */
	@GetMapping(path = "/{software-id}/licenses", consumes = { MediaType.WILDCARD })
	@Operation(summary = "Returns all found licenses and files of the software with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found") })
	public ResponseEntity<Map<String, List<String>>> getAllLicensesWithFiles(@PathVariable("software-id") String softwareID,
			@RequestParam(defaultValue = "true") boolean effective) {

		logger.debug("Requesting the licenses for the software with ID {}...", softwareID);

		Software software = LicenseEngine.getSoftware(softwareID);

		if (software != null) {

			if (effective) {

				return ResponseEntity.ok(software.getEffectiveLicensesFilesMapping());
			}

			return ResponseEntity.ok(software.getLicenseFilesMapping());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	/**
	 * Returns the files with the requested license of the software with the given
	 * ID.
	 *
	 */
	@GetMapping(path = "/{software-id}/licenses/{license-id}", consumes = { MediaType.WILDCARD })
	@Operation(summary = "Returns the files with the requested license of the software with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found") })
	public ResponseEntity<List<String>> getAllFilesWithLicense(@PathVariable("software-id") String softwareID, @PathVariable("license-id") String licenseID,
			@RequestParam(defaultValue = "true") boolean effective) {

		logger.debug("Requesting the licenses for the software with ID {}...", softwareID);
		logger.debug("QueryParams for license: {} and effective: {}.", softwareID, licenseID, softwareID);

		Software software = LicenseEngine.getSoftware(softwareID);

		if (software != null) {

			if (effective && !licenseID.equals("")) {

				return ResponseEntity.ok(software.getEffectiveLicensesFilesMapping().get(licenseID));

			}

			if (!licenseID.equals("")) {

				return ResponseEntity.ok(software.getLicenseFilesMapping().get(licenseID));
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	/**
	 * Adds (a) file(s) to the files-excluded list of the software with the given
	 * ID. The licenses of these files are not used to determine the final license
	 * of the software.
	 *
	 */
	@PutMapping("/{software-id}/files-excluded")
	@Operation(summary = "Adds files to be excluded from determining the recommended licenses of the software with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found") })
	public ResponseEntity<List<String>> addExcludedFiles(@PathVariable("software-id") String softwareID,
			@RequestBody ArrayList<String> files) {

		logger.debug("Adding files ({}) to the files-excluded list for the software with ID {}...", files, softwareID);

		Software software = LicenseEngine.getSoftware(softwareID);

		if (software != null) {
			software.getExcludedFiles().addAll(files);
			return ResponseEntity.ok(software.getExcludedFiles());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	/**
	 * Deletes the files-excluded list of the software with the given ID.
	 *
	 */
	@DeleteMapping(path = "/{software-id}/files-excluded", consumes = { MediaType.WILDCARD })
	@Operation(summary = "Deletes the files-excluded list of the software with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", content = @Content, description = "No Content") })
	public ResponseEntity<Void> deleteExcludedFiles(@PathVariable("software-id") String softwareID) {

		logger.debug("Deleting the files-excluded list for the software with ID {}...", softwareID);

		Software software = LicenseEngine.getSoftware(softwareID);
		if (software != null) {
			software.getExcludedFiles().clear();
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);
	}

	/**
	 * Returns the files-excluded list of the software with the given ID.
	 *
	 */
	@GetMapping(path = "/{software-id}/files-excluded", consumes = { MediaType.WILDCARD })
	@Operation(summary = "Returns the files-excluded list of the software with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found") })
	public ResponseEntity<List<String>> getExcludedFiles(@PathVariable("software-id") String softwareID) {

		logger.debug("Requesting the files-excluded list for the software with ID {}...", softwareID);

		Software software = LicenseEngine.getSoftware(softwareID);
		if (software != null) {
			return ResponseEntity.ok(LicenseEngine.getSoftware(softwareID).getExcludedFiles());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

}
