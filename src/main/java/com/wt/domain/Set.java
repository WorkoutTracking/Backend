package com.wt.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "exerciseSet")
@NamedQuery(name = "Set.getByExerciseAndOrderByCreated", query = "from Set where exerciseId = :exerciseId order by createdAt")
@NamedQuery(name = "Set.updateSet", query = "update Set set sets = :sets, reps = :reps, weight = :weight, rest = :rest, placeHolder = :placeHolder where id = :id")
public class Set {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID exerciseId;
    private int sets;
    private int reps;
    private double weight;
    private int rest;
    private Boolean placeHolder;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Set() {
    }

    public Set(UUID exerciseId, int sets, int reps, double weight, int rest, Boolean placeHolder) {
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.rest = rest;
        this.placeHolder = placeHolder;
    }

    public UUID getId() {
        return id;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public void setPlaceHolder(Boolean placeHolder) { 
        this.placeHolder = placeHolder; 
    }

    public Boolean getPlaceHolder() {
        return placeHolder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getExerciseId() { 
        return exerciseId; 
    }    

}
