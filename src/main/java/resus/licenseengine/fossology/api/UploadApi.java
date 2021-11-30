package resus.licenseengine.fossology.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import resus.licenseengine.fossology.model.Info;
import resus.licenseengine.fossology.model.OneOfbody;
import resus.licenseengine.fossology.model.Upload;
import resus.licenseengine.fossology.model.UploadLicenses;
import resus.licenseengine.fossology.model.UploadSummary;

/**
 * FOSSology API
 *
 * <p>
 * Automate your fossology instance using REST API
 *
 */
@Path("/")
public interface UploadApi {

	/**
	 * Uploads
	 *
	 * The uploads endpoint returns all uploads
	 *
	 */
	@GET
	@Path("/uploads")
	@Produces({ "application/json" })
	@Operation(summary = "Uploads", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "An array of uploads", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Upload.class)))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public List<Upload> uploadsGet();

	/**
	 * Delete upload by id
	 *
	 */
	@DELETE
	@Path("/uploads/{id}")
	@Produces({ "application/json" })
	@Operation(summary = "Delete upload by id", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Upload will be deleted", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public Info uploadsIdDelete(@PathParam("id") Integer id, @HeaderParam("groupName") String groupName);

	/**
	 * Get single upload by id
	 *
	 * Returns a single upload
	 *
	 */
	@GET
	@Path("/uploads/{id}")
	@Produces({ "application/json" })
	@Operation(summary = "Get single upload by id", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Get upload", content = @Content(schema = @Schema(implementation = Upload.class))),
			@ApiResponse(responseCode = "503", description = "The ununpack agent has not started yet. Please check the 'Look-at' header for more information ", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public Upload uploadsIdGet(@PathParam("id") Integer id);

	/**
	 * Get licenses found by agent
	 *
	 * Returns the list of licenses found by requested agent
	 *
	 */
	@GET
	@Path("/uploads/{id}/licenses")
	@Produces({ "application/json" })
	@Operation(summary = "Get licenses found by agent", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Get licenses", content = @Content(schema = @Schema(implementation = UploadLicenses.class))),
			@ApiResponse(responseCode = "412", description = "The agent has not been scheduled for the upload. Please check the response message. ", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "503", description = "The ununpack agent or queried agents have not started yet. Please check the 'Look-at' header for more information. ", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public UploadLicenses uploadsIdLicensesGet(@PathParam("id") Integer id, @QueryParam("agent") List<String> agent,
			@QueryParam("containers") @DefaultValue("true") Boolean containers);

	@PATCH
	@Path("/uploads/{id}")
	@Produces({ "application/json" })
	@Operation(summary = "", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Upload will be moved", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public Info uploadsIdPatch(@PathParam("id") Integer id, @HeaderParam("folderId") Integer folderId,
			@HeaderParam("groupName") String groupName);

	@PUT
	@Path("/uploads/{id}")
	@Produces({ "application/json" })
	@Operation(summary = "", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Upload will be copied", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public Info uploadsIdPut(@PathParam("id") Integer id, @HeaderParam("folderId") Integer folderId);

	/**
	 * Get single upload summary
	 *
	 * Returns summary for single upload
	 *
	 */
	@GET
	@Path("/uploads/{id}/summary")
	@Produces({ "application/json" })
	@Operation(summary = "Get single upload summary", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Get summary", content = @Content(schema = @Schema(implementation = UploadSummary.class))),
			@ApiResponse(responseCode = "503", description = "The ununpack agent has not started yet. Please check the 'Look-at' header for more information ", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public UploadSummary uploadsIdSummaryGet(@PathParam("id") Integer id);

	/**
	 * Post new upload to FOSSology
	 *
	 * Endpoint to create a new upload in FOSSology
	 *
	 */
	@POST
	@Path("/uploads")
	@Consumes({ "application/json", "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(summary = "Post new upload to FOSSology", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Upload is created", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public Info uploadsPost(@HeaderParam("Authorization") String authorization,
			@HeaderParam("folderId") Integer folderId, @HeaderParam("uploadDescription") String uploadDescription,
			@HeaderParam("public") String _public, @HeaderParam("ignoreScm") Boolean ignoreScm,
			@HeaderParam("groupName") String groupName, @HeaderParam("uploadType") String uploadType, OneOfbody body);

	/**
	 * Post new upload to FOSSology
	 *
	 * Endpoint to create a new upload in FOSSology
	 *
	 */
	@POST
	@Path("/uploads")
	@Consumes({ "application/json", "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(summary = "Post new upload to FOSSology", tags = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Upload is created", content = @Content(schema = @Schema(implementation = Info.class))),
			@ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
	public Info uploadsPost(@HeaderParam("folderId") Integer folderId,
			@Multipart(value = "fileInput", required = false) Attachment fileInputDetail,
			@HeaderParam("uploadDescription") String uploadDescription, @HeaderParam("public") String _public,
			@HeaderParam("ignoreScm") Boolean ignoreScm, @HeaderParam("groupName") String groupName,
			@HeaderParam("uploadType") String uploadType);
}
