package org.exoplatform.bch.activity;

import com.wordnik.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.exoplatform.bch.stream.StreamStorage;
import org.exoplatform.bch.swagger.ExoApiLevel;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by bdechateauvieux on 4/21/15.
 */
@Path("/activity")
@Api(value="/activity", description = "Operations about eXo Platform social activities")
@Produces("application/json")
public class ActivityResource {

    @GET
    @Path("/{activity}")
    @ApiOperation(value = "Get activity by Id",
            response = Activity.class,
            notes = ExoApiLevel.EXPERIMENTAL + "This can only be done by the logged in user.",
            position = 0)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid activity id supplied"),
            @ApiResponse(code = 404, message = "Activity not found") })
    public Response getActivity(
            @ApiParam(value = "The id that needs to be fetched. Use 0 for testing. ", required = true)
            @PathParam("activity") long id) {
        Activity activity = new Activity(id, "RDBMS Guidelines has been modified on wiki by Benoit");
        return Response.ok().entity(activity).build();
    }

    @POST
    @ApiOperation(value = "Create activity",
            response = Long.class,
            notes = "This can only be done by the logged in user.",
            position = 1)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid activity supplied"),
            @ApiResponse(code = 201, message = "returns the id of the activity")
    })
    public Response createActivity(
            @ApiParam(value = "Created activity object", required = true) Activity activity) {
        try {
            if (StringUtils.isBlank(activity.getTitle())) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            activity.setId(System.currentTimeMillis());
            StreamStorage.addActivity(activity);
            return Response.created(new URI("/api/activity/"+activity.getId())).entity(activity.getId()).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
