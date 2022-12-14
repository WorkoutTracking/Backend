package com.wt.resource;

import com.wt.domain.Exercise;
import com.wt.service.ExerciseService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Path("/workout/{id}/{user_email}")
    public Response getExercisisByWorkoutId(@PathParam("id") UUID workoutId, @PathParam("user_email") String userEmail) {
        try {
            List<Exercise> exercises = exerciseService.findExercisesByWorkoutId(workoutId, userEmail);
            if (exercises.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Warning: workout has no exercises").build();
            } else {
                return Response.ok(exercises).build();
            }
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Transactional
    @Path("/{workout}/{name}/{user_email}")
    public Response addExercise(@PathParam("workout") UUID workoutId, @PathParam("name") String name, @PathParam("user_email") String userEmail) {
        try {
            String message = exerciseService.addExercise(workoutId, name, userEmail);
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_IMPLEMENTED).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}/{user_email}")
    @Transactional
    public Response deleteWorkout(@PathParam("id") UUID id, @PathParam("user_email") String userEmail) {
        try {
            String message = exerciseService.deleteExercise(id, userEmail);
            return Response.status(Response.Status.OK).entity(message).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_MODIFIED).entity(ex.getMessage()).build();
        }
    }
}