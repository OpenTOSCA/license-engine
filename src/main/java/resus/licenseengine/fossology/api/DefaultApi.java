package resus.licenseengine.fossology.api;

import java.time.LocalDate;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import resus.licenseengine.fossology.model.DefaultResponse;
import resus.licenseengine.fossology.model.Info;
import resus.licenseengine.fossology.model.InlineResponse200;
import resus.licenseengine.fossology.model.TokenRequest;

/**
 * FOSSology API
 *
 * <p>Automate your fossology instance using REST API
 *
 */
@Path("/")
public interface DefaultApi  {

    /**
     * Get a login session registered (deprecated, use /tokens)
     *
     * Get a login session registered using the username and password 
     *
     */
    @GET
    @Path("/auth")
    @Produces({ "application/json" })
    @Operation(summary = "Get a login session registered (deprecated, use /tokens)", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "299", description = "Resource is deprecated. Use /tokens", content = @Content(schema = @Schema(implementation = DefaultResponse.class))),
        @ApiResponse(responseCode = "404", description = "UserName or password incorrect", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public DefaultResponse authGet(@QueryParam("username")String username, @QueryParam("password")String password);

    /**
     * Generate a new token
     *
     * Generate a new token to access REST API 
     *
     */
    @POST
    @Path("/tokens")
    @Consumes({ "application/json", "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @Operation(summary = "Generate a new token", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Token generated", content = @Content(schema = @Schema(implementation = DefaultResponse.class))),
        @ApiResponse(responseCode = "404", description = "UserName or password incorrect", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public DefaultResponse tokensPost(TokenRequest body);

    /**
     * Generate a new token
     *
     * Generate a new token to access REST API 
     *
     */
    @POST
    @Path("/tokens")
    @Consumes({ "application/json", "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @Operation(summary = "Generate a new token", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Token generated", content = @Content(schema = @Schema(implementation = DefaultResponse.class))),
        @ApiResponse(responseCode = "404", description = "UserName or password incorrect", content = @Content(schema = @Schema(implementation = Info.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public DefaultResponse tokensPost(@Multipart(value = "username")  String username, @Multipart(value = "password")  String password, @Multipart(value = "token_name")  String tokenName, @Multipart(value = "token_scope")  String tokenScope, @Multipart(value = "token_expire")  LocalDate tokenExpire);

    /**
     * Get the current API information
     *
     * Get the current API version and supported authentication methods 
     *
     */
    @GET
    @Path("/version")
    @Produces({ "application/json" })
    @Operation(summary = "Get the current API information", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The version and security information", content = @Content(schema = @Schema(implementation = InlineResponse200.class))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public InlineResponse200 versionGet();
}
