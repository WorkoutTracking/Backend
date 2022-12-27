package com.wt.service;

import com.wt.domain.Exercise;
import com.wt.domain.Workout;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@QuarkusTest
public class ExerciseServiceTest {
    @Inject
    ExerciseService exerciseService;

    @Test
    @TestSecurity(user = "admin@yahoo.nl", roles = "user")
    public void When_Get_Exercises_By_Workout_And_User_Fails_Because_TokenEmail_And_Email_Arent_From_The_Same_User() {
        // Arrange
        String email = "carlovankessel@yahoo.nl";
        UUID workoutId = UUID.fromString("32b5646c-cecb-4518-ae17-bcd296990db7");

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exerciseService.findExercisesByWorkoutId(workoutId, email);
        });
    }
}
