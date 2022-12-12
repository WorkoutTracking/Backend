package com.wt.service;

import com.wt.domain.Workout;
import com.wt.repository.WorkoutRepository;
import io.quarkus.panache.common.Sort;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class WorkoutService {

    @Inject
    WorkoutRepository workoutRepository;
    @Inject
    UserService userService;
    @Inject
    SecurityIdentity securityIdentity;

    public List<Workout> allWorkouts() {
        return workoutRepository.listAll();
    }

    public List<Workout> allWorkoutsByUser(String userEmail) {
        if (!securityIdentity.getPrincipal().getName().equals(userEmail)) {
            return null;
        }
        return workoutRepository.find("userEmail = '" + userEmail + "'", Sort.descending("createdAt")).list();
    }

    public Workout findWorkoutById(UUID id, String userEmail) {
        if (!securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException("Error: Invalid user");
        }
        return workoutRepository.find("id", id).firstResult();
    }

    public Workout addWorkout(String name, String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail) || name == null) {
            throw new IllegalArgumentException("Error: Invalid user or workout name");
        }

        Workout workout = new Workout();
        workout.setName(name);
        workout.setUserEmail(userEmail);

        workoutRepository.persist(workout);
        return workout;
    }
}
