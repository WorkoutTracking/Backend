package com.wt.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NamedQuery(name = "Exercise.getByUserAndNameAndOrderByCreated", query = "SELECT new Exercise(e.id, e.workoutId, e.name, e.createdAt) FROM Exercise e, Workout w " +
        "WHERE e.workoutId = w.id " +
        "AND w.userEmail = :userEmail " +
        "AND e.name = :name " +
        "order by e.createdAt DESC")
@NamedQuery(name = "Exercise.getByWorkoutIdAndOrderByCreated", query = "FROM Exercise WHERE workoutId = :workoutId order by createdAt DESC")

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

    public Exercise(UUID id, UUID workoutId, String name, LocalDateTime createdAt) {
        this.id = id;
        this.workoutId = workoutId;
        this.name = name;
        this.createdAt = createdAt;
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
