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

    private static final String INVALIDUSER = "Error: Invalid user";

    public List<Workout> allWorkouts() {
        return workoutRepository.listAll();
    }

    public List<Workout> allWorkoutsByUser(String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException(INVALIDUSER);
        }
        return workoutRepository.find("userEmail = '" + userEmail + "'", Sort.descending("createdAt")).list();
    }

    public Workout findWorkoutById(UUID id, String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException(INVALIDUSER);
        }
        return workoutRepository.find("id", id).firstResult();
    }

    public Workout addWorkout(String name, String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException(INVALIDUSER);
        }
        if (name == null)
        {
            throw new IllegalArgumentException("Error: Name is empty");
        }

        try {
            Workout workout = new Workout();
            workout.setName(name);
            workout.setUserEmail(userEmail);

            workoutRepository.persist(workout);
            return workout;
        } catch (Exception error) {
            throw new IllegalArgumentException("Error: there was a error when adding the workout. Message: "+error.getMessage());
        }

    }
}
