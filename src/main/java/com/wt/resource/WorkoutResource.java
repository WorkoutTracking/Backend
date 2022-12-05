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
    private WorkoutService workoutService;


    public WorkoutResource() {

    }

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
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        Workout workout = workoutService.findWorkoutById(id);
        if (workout == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(workout).build();
        }
    }

    @POST
    @Transactional
    public Response addWorkout(Workout workout) {
        Workout workoutWithId = workoutService.addWorkout(workout);
        return Response.ok(workoutWithId).build();
    }
}
