package com.wt.resource;

import com.wt.domain.Exercise;
import com.wt.service.ExerciseService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/api/users/exercises")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ExerciseResource {
    @Inject
    private ExerciseService exerciseService;

    @GET
    public List<Exercise> allExercises() {
        return exerciseService.allExercises();
    }

    @GET
    @Path("/workout/{id}")
    public List<Exercise> getExercisisByWorkoutId(@PathParam("id") UUID workoutId) {
        return exerciseService.findExercisesByWorkoutId(workoutId);
    }

    @POST
    @Transactional
    @Path("/{workout}/{name}/{user_email}")
    public String addExercise(@PathParam("workout") UUID workoutId, @PathParam("name") String name, @PathParam("user_email") String userEmail) {
        return exerciseService.addExercise(workoutId, name, userEmail);
    }

    @DELETE
    @Path("/{id}/{user_email}")
    @Transactional
    public String deleteWorkout(@PathParam("id") UUID id, @PathParam("user_email") String userEmail) {
        return exerciseService.deleteExercise(id, userEmail);
    }
}