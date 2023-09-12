package com.wt.service;

import com.wt.domain.Workout;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@QuarkusTest
public class WorkoutServiceTest {

    @Inject
    WorkoutService workoutService;

    @Test
    @TestSecurity(user = "admin@gmail.nl", roles = "user")
    public void When_Get_Workouts_By_User_Is_More_Than_Zero() {
        // Arrange
        String email = "admin@gmail.nl";
        int lowestSize = 0;

        // Act
        List<Workout> workouts = workoutService.allWorkoutsByUser(email);

        // Assert
        Assertions.assertNotEquals(lowestSize, workouts.size());
    }

    @Test
    @TestSecurity(user = "admin@gmail.nl", roles = "user")
    public void When_Get_Workouts_Throws_Exception_Because_TokenEmail_And_Email_Arent_From_The_Same_User() {
        // Arrange
        String email = "admin@gmail.com";

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            workoutService.allWorkoutsByUser(email);
        });
    }

    @Test
    @Transactional
    @TestSecurity(user = "admin@gmail.nl", roles = "user")
    public void When_Add_Workout_Fails_Because_Name_Is_Null() {
        // Arrange
        String email = "admin@gmail.nl";
        String name = null;

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            workoutService.addWorkout(name, email);
        });
    }

    @Test
    @Transactional
    @TestSecurity(user = "admin@gmail.nl", roles = "user")
    public void When_Add_Workout_Works() {
        // Arrange
        String email = "admin@gmail.nl";
        String name = "Test";

        // Act
        Workout workout = workoutService.addWorkout(name, email);

        // Assert
        Assertions.assertEquals(name, workout.getName());
    }
}
