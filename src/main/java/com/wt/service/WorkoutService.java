package com.wt.service;

import com.wt.domain.UserAccount;
import com.wt.domain.Workout;
import com.wt.repository.WorkoutRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class WorkoutService {

    @Inject
    WorkoutRepository workoutRepository;

    public WorkoutService(){

    }

    public List<Workout> allWorkouts() {
        return  workoutRepository.listAll();
    }

    public Workout findWorkoutById(UUID id){ return workoutRepository.find("id", id).firstResult();}

    public Workout addWorkout(Workout workout) {
        if (workout.getName() == null || workout.getUser_email() == null){
            throw new IllegalArgumentException();
        }
        workoutRepository.persist(workout);
        return workout;
    }
}
