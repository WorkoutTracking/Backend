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

    private UUID workoutId;
    private String name;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Exercise() {
    }

    public Exercise(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public UUID getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(UUID workoutId) {
        this.workoutId = workoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
