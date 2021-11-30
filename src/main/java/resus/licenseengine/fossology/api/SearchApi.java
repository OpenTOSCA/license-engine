package resus.licenseengine.fossology.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import resus.licenseengine.fossology.model.Info;
import resus.licenseengine.fossology.model.SearchResults;

/**
 * FOSSology API
 *
 * <p>Automate your fossology instance using REST API
 *
 */
@Path("/")
public interface SearchApi  {

    @GET
    @Path("/search")
    @Produces({ "application/json" })
    @Operation(summary = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SearchResults.class)))),
        @ApiResponse(responseCode = "200", description = "Some error occured. Check the \"message\"", content = @Content(schema = @Schema(implementation = Info.class))) })
    public List<SearchResults> searchGet(@HeaderParam("groupName") String groupName, @HeaderParam("searchType") String searchType, @HeaderParam("filename") String filename, @HeaderParam("tag") String tag, @HeaderParam("filesizemin") Integer filesizemin, @HeaderParam("filesizemax") Integer filesizemax, @HeaderParam("license") String license, @HeaderParam("copyright") String copyright);
}
