package com.wt.resource;

import com.wt.domain.Exercise;
import com.wt.domain.Set;
import com.wt.service.ExerciseService;
import com.wt.service.SetService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/api/users/sets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class SetResource {
    @Inject
    private SetService setService;

    public SetResource(){

    }
    @GET
    public List<Set> allSets(){
        return setService.allSets();
    }

    @GET
    @Path("/exercise/{id}")
    public List<Set> getSetsByExerciseId(@PathParam("id") UUID exercise_id){
        return setService.findSetsByExerciseId(exercise_id);
    }

    @POST
    @Transactional
    public Response addSet(Set set){
        Set setWithId = setService.addSet(set);
        return Response.created(URI.create("/api/users/sets/" + setWithId.getId())).build();
    }

}
