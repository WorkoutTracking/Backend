package nl.resource;

import nl.domain.Workout;
import nl.service.WorkoutService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

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

    @POST
    @Transactional
    public Response addWorkout(Workout workout) {
        Workout workoutWithId = workoutService.addWorkout(workout);
        return Response.created(URI.create("/api/workouts/" + workoutWithId.getId())).build();
    }
}
