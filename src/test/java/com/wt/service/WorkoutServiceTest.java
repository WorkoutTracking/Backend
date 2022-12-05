package com.wt.service;

import com.wt.domain.Workout;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
public class WorkoutServiceTest {

    @Inject
    WorkoutService workoutService;

    @Test
    @TestSecurity(user = "admin@gmail.com", roles = "user")
    public void When_Get_Workouts_By_User_Is_More_Than_Zero()
    {
        // Arrange
        String email = "admin@gmail.com";
        int lowestSize = 0;

        // Act
        List<Workout> workouts = workoutService.allWorkoutsByUser(email);

        // Assert
        Assertions.assertNotEquals(lowestSize, workouts.size());
    }
    @Test
    @TestSecurity(user = "admin@gmail.com", roles = "user")
    public void When_Get_Workouts_Fails_Because_TokenEmail_And_Email_Arent_From_The_Same_User()
    {
        // Arrange
        String email = "carlovankessel@gmail.com";

        // Act
        List<Workout> workouts = workoutService.allWorkoutsByUser(email);

        // Assert
        Assertions.assertNull(workouts);
    }

}