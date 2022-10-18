package nl.service;

import nl.domain.Exercise;
import nl.repository.ExerciseRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ExerciseService {
    @Inject
    ExerciseRepository exerciseRepository;

    public List<Exercise> allExercises() {
        return exerciseRepository.listAll();
    }

    public Exercise addExercise(Exercise exercise){
        exerciseRepository.persist(exercise);
        return exercise;
    }

}
