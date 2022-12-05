package com.wt.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID workout_id;

    /*    @ManyToOne
    private Workout workout;*/
    private String name;
    @CreationTimestamp
    private LocalDateTime created_at;

    public Exercise() {
    }

    public Exercise(Workout workout, String name) {
        /*        this.workout = workout;*/
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*    public Workout getWorkout() {
            return workout;
        }*/
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    /*    public void setWorkout(Workout workout) {this.workout = workout;}*/
}
