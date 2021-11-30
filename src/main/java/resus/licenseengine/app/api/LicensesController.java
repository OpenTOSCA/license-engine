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

import java.util.Arrays;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import resus.licenseengine.app.LicenseEngine;
import resus.licenseengine.app.model.License;
import resus.licenseengine.utils.LicenseUtils;

@RestController
@RequestMapping(value = "${server.endpoints.licenses.path}")
public class LicensesController {

	private static final Logger logger = LoggerFactory.getLogger(LicensesController.class);

	/**
	 * Returns all available licenseIDs.
	 *
	 */
	@GetMapping(produces = { MediaType.APPLICATION_JSON })
	@Operation(summary = "Returns all available license IDs.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))), description = "OK") })
	public ResponseEntity<String[]> getLicenses() {

		logger.debug("All available licenses are requested...");

		String[] ls = LicenseUtils.getAllLicenses();
		Arrays.sort(ls);
		return ResponseEntity.ok(ls);
	}

	/**
	 * Returns further information about the license with the given id.
	 *
	 */
	@GetMapping(value = "/{license-id}", produces = { MediaType.APPLICATION_JSON })
	@Operation(summary = "Returns further information about the license with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found") })
	public ResponseEntity<License> getLicense(@PathVariable("license-id") String licenseID) {

		logger.debug("Information requested about the license with ID {} ...", licenseID);

		License license = LicenseEngine.getLicense(licenseID);

		if (license != null) {
			return ResponseEntity.ok(license);
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	/**
	 * Returns the license text for the license with the given id.
	 *
	 */
	@GetMapping(value = "/{license-id}/text", produces = { MediaType.TEXT_PLAIN, MediaType.TEXT_HTML })
	@Operation(summary = "Returns the license text as text/html or plain/text for the license with the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found") })
	public ResponseEntity<String> getLicenseTextHTML(@PathVariable("license-id") String licenseID,
			@RequestHeader(required = false) String accept) {

		logger.debug("The text for the license with ID {} is requested as {}...", licenseID, accept);

		String licenseText;

		if (accept.equalsIgnoreCase(MediaType.TEXT_PLAIN)) {
			licenseText = LicenseUtils.getLicenseText(licenseID);

		} else {
			licenseText = LicenseUtils.getLicenseTextHtml(licenseID);
		}

		if (licenseText != null) {
			return ResponseEntity.ok(licenseText);
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
