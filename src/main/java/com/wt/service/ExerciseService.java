package com.wt.service;

import com.wt.domain.Exercise;
import com.wt.repository.ExerciseRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ExerciseService {
    @Inject
    ExerciseRepository exerciseRepository;

    public List<Exercise> allExercises() {
        return exerciseRepository.listAll();
    }

    public List<Exercise> findExercisesByWorkoutId(UUID workout_id) {
        return exerciseRepository.find("workout_id", workout_id).list();
    }

    public Exercise addExercise(Exercise exercise) {
/*        workout.getExercises().add(exercise);
        exercise.setWorkout(workout);*/
        exerciseRepository.persist(exercise);
        return exercise;
    }

}
