package com.wt.service;

import com.wt.domain.Exercise;
import com.wt.repository.ExerciseRepository;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

import static io.quarkus.arc.ComponentsProvider.LOG;

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

    public Exercise getLatestAddedExerciseByWorkoutId(UUID workoutId) {
        return exerciseRepository.find("#Exercise.getByWorkoutIdAndOrderByCreated",
            Parameters.with("workoutId", workoutId)).firstResult();
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

    public Exercise checkIfExerciseNameExists(String userEmail, String name) {
        return exerciseRepository.find("#Exercise.getByUserAndNameAndOrderByCreated",
                Parameters.with("userEmail", userEmail).and("name", name)).firstResult();
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