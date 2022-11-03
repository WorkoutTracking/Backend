package com.wt.resource;

import com.wt.domain.UserAccount;
import com.wt.domain.Workout;
import com.wt.service.WorkoutService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/api/workouts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkoutResource {

    @Inject
    private WorkoutService workoutService;


    public WorkoutResource(){

    }

    @GET
    public List<Workout> allWorkouts(){
        return workoutService.allWorkouts();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id){
        Workout workout = workoutService.findWorkoutById(id);
        if(workout == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            return Response.ok(workout).build();
        }
    }

    @POST
    @Transactional
    public Response addWorkout(Workout workout) {
        Workout workoutWithId = workoutService.addWorkout(workout);
        return Response.created(URI.create("/api/workouts/" + workoutWithId.getId())).build();
    }
}
