package com.wt.service;

import com.wt.domain.Set;
import com.wt.repository.SetRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SetService {
    @Inject
    SetRepository setRepository;

    public List<Set> allSets() {
        return setRepository.listAll();
    }

    public List<Set> findSetsByExerciseId(UUID exercise_id) {
        return setRepository.find("exercise_id", exercise_id).list();
    }

    public Set addSet(Set set) {
/*        workout.getExercises().add(exercise);
        exercise.setWorkout(workout);*/
        setRepository.persist(set);
        return set;
    }

}
