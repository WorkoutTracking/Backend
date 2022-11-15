package com.wt.resource;

import com.wt.domain.Exercise;
import com.wt.domain.Workout;
import com.wt.service.ExerciseService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/api/users/exercises")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ExerciseResource {
    @Inject
    private ExerciseService exerciseService;

    public ExerciseResource(){

    }
    @GET
    public List<Exercise> allExercises(){
        return exerciseService.allExercises();
    }

    @GET
    @Path("/workout/{id}")
    public List<Exercise> getExercisisByWorkoutId(@PathParam("id") UUID workout_id){
        return exerciseService.findExercisesByWorkoutId(workout_id);
    }

    @POST
    @Transactional
    public Response addExercise(Exercise exercise){
        Exercise exerciseWithId = exerciseService.addExercise(exercise);
        return Response.created(URI.create("/api/users/exercises/" + exerciseWithId.getId())).build();
    }

}
