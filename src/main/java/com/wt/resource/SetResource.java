package com.wt.resource;

import com.wt.domain.Set;
import com.wt.service.SetService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/api/users/sets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class SetResource {
    @Inject
    private SetService setService;

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
    public String addSet(@PathParam("exercise") UUID exerciseId, @PathParam("user_email") String userEmail) {
        return setService.addSet(exerciseId, userEmail);
    }

    @DELETE
    @Transactional
    @Path("/{set}/{user_email}")
    public String deleteSet(@PathParam("set") UUID setId, @PathParam("user_email") String userEmail) {
        return setService.deleteSet(setId, userEmail);
    }

    @PUT
    @Transactional
    @Path("/{set_id}/{sets}/{reps}/{weight}/{rest}/{user_email}")
    public String updateSet(
            @PathParam("set_id") UUID setId,
            @PathParam("sets") int sets,
            @PathParam("reps") int reps,
            @PathParam("weight") double weight,
            @PathParam("rest") int rest,
            @PathParam("user_email") String userEmail
    ) {
        return setService.updateSet(setId, sets, reps, weight, rest, userEmail);
    }
}
