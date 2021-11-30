package resus.licenseengine.fossology.api;

import java.io.File;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import resus.licenseengine.fossology.model.Info;
import resus.licenseengine.fossology.model.Job;
import resus.licenseengine.fossology.model.ScanOptions;

/**
 * FOSSology API
 *
 * <p>
 * Automate your fossology instance using REST API
 *
 */
@Path("/")
public interface JobApi {

	/**
	 * Gets all jobs
	 *
	 * Returns all jobs with their status
	 *
	 */
	@GET
	@Path("/jobs")
	@Produces({ "application/json" })
	@Operation(summary = "Gets all jobs", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Job.class)))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public List<Job> jobsGet(@HeaderParam("Authorization") String authorization, @HeaderParam("limit") Integer limit,
			@HeaderParam("page") Integer page, @QueryParam("upload") Integer upload);

	/**
	 * Gets single job by id
	 *
	 * Returns job with the status
	 *
	 */
	@GET
	@Path("/jobs/{id}")
	@Produces({ "application/json" })
	@Operation(summary = "Gets single job by id", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Job.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public Job jobsIdGet(@HeaderParam("Authorization") String authorization, @PathParam("id") Integer id);

	/**
	 * Schedule an Analysis
	 *
	 * Schedule an Analysis of an existing upload
	 *
	 */
	@POST
	@Path("/jobs")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Operation(summary = "Schedule an Analysis", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Job Scheduled with job id in message", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public Info jobsPost(@HeaderParam("Authorization") String authorization, @HeaderParam("folderId") Integer folderId,
			@HeaderParam("uploadId") Integer uploadId, @HeaderParam("groupName") String groupName, ScanOptions body);

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
	public Info reportGet(@HeaderParam("uploadId") Integer uploadId, @HeaderParam("reportFormat") String reportFormat,
			@HeaderParam("groupName") String groupName);

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
	public File reportIdGet(@PathParam("id") Integer id, @HeaderParam("groupName") String groupName);
}
