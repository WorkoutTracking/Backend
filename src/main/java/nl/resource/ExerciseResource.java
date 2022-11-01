package nl.resource;

import nl.domain.Exercise;
import nl.service.ExerciseService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/exercises")
public class ExerciseResource {
    @Inject
    private ExerciseService exerciseService;

    public ExerciseResource(){

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exercise> allExercises(){
        return exerciseService.allExercises();
    }

    @POST
    @Transactional
    public Response addExercise(Exercise exercise){
        Exercise exerciseWithId = exerciseService.addExercise(exercise);
        return Response.created(URI.create("/api/exercises/" + exerciseWithId.getId())).build();
    }

}
