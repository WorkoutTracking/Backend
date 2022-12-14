package com.wt.resource;

import com.wt.domain.Set;
import com.wt.service.SetService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/api/users/sets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class SetResource {
    @Inject
    SetService setService;

    @GET
    public List<Set> allSets() {
        return setService.allSets();
    }

    @GET
    @Path("/exercise/{id}")
    public List<Set> getSetsByExerciseId(@PathParam("id") UUID exerciseId) {
        return setService.findSetsByExerciseId(exerciseId);
    }

    @POST
    @Transactional
    @Path("/{exercise}/{user_email}")
    public Response addSet(@PathParam("exercise") UUID exerciseId, @PathParam("user_email") String userEmail) {
        try {
            String message = setService.addSet(exerciseId, userEmail);
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_MODIFIED).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{set}/{user_email}")
    public Response deleteSet(@PathParam("set") UUID setId, @PathParam("user_email") String userEmail) {
        try {
            String message = setService.deleteSet(setId, userEmail);
            return Response.status(Response.Status.OK).entity(message).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_MODIFIED).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{set_id}/{sets}/{reps}/{weight}/{rest}/{user_email}")
    public Response updateSet(
            @PathParam("set_id") UUID setId,
            @PathParam("sets") int sets,
            @PathParam("reps") int reps,
            @PathParam("weight") double weight,
            @PathParam("rest") int rest,
            @PathParam("user_email") String userEmail
    ) {
        try {
            String message = setService.updateSet(setId, sets, reps, weight, rest, userEmail);
            return Response.status(Response.Status.OK).entity(message).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_MODIFIED).entity(ex.getMessage()).build();
        }
    }
}
