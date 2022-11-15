package com.wt.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Workout {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String user_email;
    private String name;
    @CreationTimestamp
    private LocalDateTime created_at;
/*    @OneToMany(mappedBy = "workout")
    private List<Exercise> exercises = new ArrayList<>();*/

    public Workout() {
    }

    public Workout(String user_email, String name) {
        this.user_email = user_email;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUser_email() {
        return user_email;
    }
/*    public List<Exercise> getExercises() {return exercises;}*/
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    public void setName(String name) {
        this.name = name;
    }
/*    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        exercise.setWorkout(this);
    }*/
/*    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
        exercise.setWorkout(null);
    }*/

}
