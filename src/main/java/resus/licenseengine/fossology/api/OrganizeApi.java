package resus.licenseengine.fossology.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import resus.licenseengine.fossology.model.Folder;
import resus.licenseengine.fossology.model.Info;
import resus.licenseengine.fossology.model.Upload;

/**
 * FOSSology API
 *
 * <p>Automate your fossology instance using REST API
 *
 */
@Path("/")
public interface OrganizeApi  {

    /**
     * Get the list of accessible folders
     *
     */
    @GET
    @Path("/folders")
    @Produces({ "application/json" })
    @Operation(summary = "Get the list of accessible folders", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "List of folders", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Folder.class)))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public List<Folder> foldersGet();

    /**
     * Delete a folder
     *
     */
    @DELETE
    @Path("/folders/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Delete a folder", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "202", description = "Folder scheduled to be deleted", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public Info foldersIdDelete(@PathParam("id") Integer id);

    /**
     * Get a single folder details
     *
     */
    @GET
    @Path("/folders/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Get a single folder details", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Details of the required folder", content = @Content(schema = @Schema(implementation = Folder.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public Folder foldersIdGet(@PathParam("id") Integer id);

    /**
     * Edit a folder&#x27;s description
     *
     */
    @PATCH
    @Path("/folders/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Edit a folder's description", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Folder is updated", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public Info foldersIdPatch(@PathParam("id") Integer id, @HeaderParam("name") String name, @HeaderParam("description") String description);

    /**
     * Copy/Move a folder
     *
     */
    @PUT
    @Path("/folders/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Copy/Move a folder", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "202", description = "Folder will be copied/moved", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public Info foldersIdPut(@PathParam("id") Integer id, @HeaderParam("parent") Integer parent, @HeaderParam("action") String action);

    /**
     * Create a new folder
     *
     */
    @POST
    @Path("/folders")
    @Produces({ "application/json" })
    @Operation(summary = "Create a new folder", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Folder with the same name already exists under the same parent", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "201", description = "Folder is created with new folder id in message", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public Info foldersPost(@HeaderParam("parentFolder") Integer parentFolder, @HeaderParam("folderName") String folderName, @HeaderParam("folderDescription") String folderDescription);

    /**
     * Delete upload by id
     *
     */
    @DELETE
    @Path("/uploads/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Delete upload by id", tags={  })
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
    @Operation(summary = "Get single upload by id", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Get upload", content = @Content(schema = @Schema(implementation = Upload.class))),
        @ApiResponse(responseCode = "503", description = "The ununpack agent has not started yet. Please check the 'Look-at' header for more information ", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public Upload uploadsIdGet(@PathParam("id") Integer id);

    @PATCH
    @Path("/uploads/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "202", description = "Upload will be moved", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public Info uploadsIdPatch(@PathParam("id") Integer id, @HeaderParam("folderId") Integer folderId, @HeaderParam("groupName") String groupName);

    @PUT
    @Path("/uploads/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "202", description = "Upload will be copied", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public Info uploadsIdPut(@PathParam("id") Integer id, @HeaderParam("folderId") Integer folderId);
}
