package com.wt.service;

import com.wt.domain.Exercise;
import com.wt.repository.ExerciseRepository;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ExerciseService {
    @Inject
    ExerciseRepository exerciseRepository;

    @Inject
    UserService userService;

    @Inject
    SecurityIdentity securityIdentity;

    public List<Exercise> allExercises() {
        return exerciseRepository.listAll();
    }

    public List<Exercise> findExercisesByWorkoutId(UUID workoutId) {
        return exerciseRepository.find("workoutId", workoutId).list();
    }

    public String addExercise(UUID workoutId, String name, String userEmail) {
        if (userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            return "User not valid";
        }

        if (name == null || workoutId == null) {
            return "Name or WorkoutId is not defined";
        }

        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setWorkoutId(workoutId);

        exerciseRepository.persist(exercise);
        return "Exercise created";
    }

    public String deleteExercise(UUID id, String userEmail) {
        if (userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            return "User not valid";
        }

        long message = exerciseRepository.delete("id", id);

        if (message == 1) {
            return "Exercise is deleted";
        } else {
            return "Something went wrong";
        }
    }
}