package com.wt.service;

import com.wt.domain.Workout;
import com.wt.repository.WorkoutRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import io.quarkus.security.identity.SecurityIdentity;

@ApplicationScoped
public class WorkoutService {

    @Inject
    WorkoutRepository workoutRepository;
    @Inject
    UserService userService;
    @Inject
    SecurityIdentity securityIdentity;

    public WorkoutService() {

    }

    public List<Workout> allWorkouts() {
        return workoutRepository.listAll();
    }

    public List<Workout> allWorkoutsByUser(String user_email) {
        if (!securityIdentity.getPrincipal().getName().equals(user_email))
        {
            return null;
        }
        return workoutRepository.find("user_email", user_email).list();
    }

    public Workout findWorkoutById(UUID id) {
        return workoutRepository.find("id", id).firstResult();
    }

    public Workout addWorkout(String name, String user_email) {
        if (!userService.checkIfUserExists(user_email)) {
            return null;
        }
        if (!securityIdentity.getPrincipal().getName().equals(user_email))
        {
            return null;
        }
        if (name == null) {
            return null;
        }

        Workout workout = new Workout();
        workout.setName(name);
        workout.setUser_email(user_email);

        workoutRepository.persist(workout);
        return workout;
    }
}
