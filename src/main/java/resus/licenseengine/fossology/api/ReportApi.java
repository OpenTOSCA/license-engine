package resus.licenseengine.fossology.api;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import resus.licenseengine.fossology.model.Info;

/**
 * FOSSology API
 *
 * <p>
 * Automate your fossology instance using REST API
 *
 */
@Path("/")
public interface ReportApi {

	/**
	 * Get the reports for a given upload
	 *
	 */
	@GET
	@Path("/report")
	@Produces({ "application/json" })
	@Operation(summary = "Get the reports for a given upload", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Report generation is scheduled. Link to download report will be in message", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public Info reportGet(@HeaderParam("Authorization") String authorization, @HeaderParam("uploadId") Integer uploadId,
			@HeaderParam("reportFormat") String reportFormat, @HeaderParam("groupName") String groupName);

	/**
	 * Download the report
	 *
	 */
	@GET
	@Path("/report/{id}")
	@Produces({ "text/plain", "application/zip", "application/json" })
	@Operation(summary = "Download the report", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Required report", content = @Content(schema = @Schema(implementation = File.class))),
			@ApiResponse(responseCode = "503", description = "Report is not ready yet. Check 'Retry-After' header.", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public File reportIdGet(@HeaderParam("Authorization") String authorization, @PathParam("id") Integer id,
			@HeaderParam("groupName") String groupName);
}
