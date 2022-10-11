package nl.service;

import nl.domain.User;
import nl.domain.Workout;
import nl.repository.WorkoutRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class WorkoutService {

    @Inject
    WorkoutRepository workoutRepository;

    public WorkoutService(){

    }

    public List<Workout> allWorkouts() {
        return  workoutRepository.listAll();
    }

    public Workout addWorkout(Workout workout) {
        workoutRepository.persist(workout);
        return workout;
    }
}
