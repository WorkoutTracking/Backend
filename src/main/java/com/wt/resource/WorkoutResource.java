package com.wt.resource;

import com.wt.domain.Workout;
import com.wt.service.WorkoutService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/api/users/workouts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class WorkoutResource {

    @Inject
    WorkoutService workoutService;

    @GET
    public List<Workout> allWorkouts() {
        return workoutService.allWorkouts();
    }

    @GET
    @Path("/user/{user_email}")
    public List<Workout> allWorkoutsByUser(@PathParam("user_email") String user_email) {
        return workoutService.allWorkoutsByUser(user_email);
    }

    @GET
    @Path("/{id}/{user_email}")
    public Response getById(@PathParam("id") UUID id, @PathParam("user_email") String userEmail) {
        try {
            Workout workout = workoutService.findWorkoutById(id, userEmail);
            if (workout == null)
            {
                return Response.status(Response.Status.NOT_FOUND).entity("Error: workout not found").build();
            } else {
                return Response.ok(workout).build();
            }
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Transactional
    @Path("/{name}/{user_email}")
    public Response addWorkout(@PathParam("name") String name, @PathParam("user_email") String user_email) {
        try {
            Workout workout = workoutService.addWorkout(name, user_email);
            return Response.ok(workout).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(ex.getMessage()).build();
        }
    }
}
