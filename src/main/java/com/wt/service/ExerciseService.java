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

    private static final String INVALIDUSER = "Error: Invalid user";

    public List<Exercise> allExercises() {
        return exerciseRepository.listAll();
    }

    public List<Exercise> findExercisesByWorkoutId(UUID workoutId, String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException(INVALIDUSER);
        }

        return exerciseRepository.find("workoutId", workoutId).list();
    }

    public String addExercise(UUID workoutId, String name, String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException(INVALIDUSER);
        }
        if (name == null || workoutId == null) {
            throw new IllegalArgumentException("Error: Name or WorkoutId is not defined");
        }

        try {
            Exercise exercise = new Exercise();
            exercise.setName(name);
            exercise.setWorkoutId(workoutId);

            exerciseRepository.persist(exercise);
            return "Exercise created";
        } catch (Exception error) {
            return error.getMessage();
        }
    }

    public String deleteExercise(UUID id, String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException(INVALIDUSER);
        }

        try {
            exerciseRepository.delete("id", id);
            return "Exercise is deleted";
        } catch (Exception error) {
            return error.getMessage();
        }
    }
}