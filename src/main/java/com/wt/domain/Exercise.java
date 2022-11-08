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
    private String name;
    @CreationTimestamp
    private LocalDateTime created_at;

    public Exercise(){
    }

    public Exercise(UUID workout_id, String name) {
        this.workout_id = workout_id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public UUID getWorkout_id() {
        return workout_id;
    }
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    public void setName(String name) {
        this.name = name;
    }
}
