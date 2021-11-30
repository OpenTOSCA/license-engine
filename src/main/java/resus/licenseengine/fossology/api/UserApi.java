package resus.licenseengine.fossology.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import resus.licenseengine.fossology.model.Info;
import resus.licenseengine.fossology.model.User;

/**
 * FOSSology API
 *
 * <p>Automate your fossology instance using REST API
 *
 */
@Path("/")
public interface UserApi  {

    @GET
    @Path("/users")
    @Produces({ "application/json" })
    @Operation(summary = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public List<User> usersGet();

    /**
     * Delete user by id
     *
     */
    @DELETE
    @Path("/users/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Delete user by id", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "202", description = "User will be deleted", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public Info usersIdDelete(@PathParam("id") Integer id);

    /**
     * Get user by id
     *
     */
    @GET
    @Path("/users/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Get user by id", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "User with the given id", content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public User usersIdGet(@PathParam("id") Integer id);
}
