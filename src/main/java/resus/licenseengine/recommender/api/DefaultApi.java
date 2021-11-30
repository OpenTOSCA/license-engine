package resus.licenseengine.recommender.api;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
import resus.licenseengine.recommender.model.HTTPValidationError;
import resus.licenseengine.recommender.model.License;

/**
 * FastAPI
 *
 * <p>No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 */
@Path("/")
public interface DefaultApi  {

    /**
     * Add License
     *
     */
    @POST
    @Path("/licenses/add/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(summary = "Add License", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful Response", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = HTTPValidationError.class))) })
    public Object addLicenseLicensesAddPost(License body);

    /**
     * Check License
     *
     */
    @POST
    @Path("/licenses/check/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(summary = "Check License", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful Response", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))),
        @ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = HTTPValidationError.class))) })
    public List<String> checkLicenseLicensesCheckPost(Set<String> set);

    /**
     * Delete License
     *
     */
    @DELETE
    @Path("/licenses/{license_id}")
    @Produces({ "application/json" })
    @Operation(summary = "Delete License", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful Response", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = HTTPValidationError.class))) })
    public Object deleteLicenseLicensesLicenseIdDelete(@PathParam("license_id") String licenseId);

    /**
     * Get All New Licenses
     *
     * Returns all available software licenses as License-Objects
     *
     */
    @GET
    @Path("/licenses/")
    @Produces({ "application/json" })
    @Operation(summary = "Get All New Licenses", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful Response", content = @Content(array = @ArraySchema(schema = @Schema(implementation = License.class)))) })
    public List<License> getAllNewLicensesLicensesGet();

    /**
     * Get License Conditions
     *
     */
    @GET
    @Path("/licenses/{license_id}/conditions")
    @Produces({ "application/json" })
    @Operation(summary = "Get License Conditions", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful Response", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = HTTPValidationError.class))) })
    public Object getLicenseConditionsLicensesLicenseIdConditionsGet(@PathParam("license_id") String licenseId);

    /**
     * Get License
     *
     */
    @GET
    @Path("/licenses/{license_id}")
    @Produces({ "application/json" })
    @Operation(summary = "Get License", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful Response", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = HTTPValidationError.class))) })
    public Object getLicenseLicensesLicenseIdGet(@PathParam("license_id") String licenseId);

    /**
     * Get License Limitations
     *
     */
    @GET
    @Path("/licenses/{license_id}/limitations")
    @Produces({ "application/json" })
    @Operation(summary = "Get License Limitations", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful Response", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = HTTPValidationError.class))) })
    public Object getLicenseLimitationsLicensesLicenseIdLimitationsGet(@PathParam("license_id") String licenseId);

    /**
     * Get License Permissions
     *
     */
    @GET
    @Path("/licenses/{license_id}/permissions")
    @Produces({ "application/json" })
    @Operation(summary = "Get License Permissions", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful Response", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = HTTPValidationError.class))) })
    public Object getLicensePermissionsLicensesLicenseIdPermissionsGet(@PathParam("license_id") String licenseId);

    /**
     * Update License
     *
     */
    @PUT
    @Path("/licenses/{license_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(summary = "Update License", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful Response", content = @Content(schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = HTTPValidationError.class))) })
    public Object updateLicenseLicensesLicenseIdPut(License body, @PathParam("license_id") String licenseId);
}
