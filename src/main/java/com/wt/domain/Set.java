package com.wt.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Set {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID exercise_id;

    /*    @ManyToOne
    private Exercise exercise;*/
    private int sets;
    private int reps;
    private double weight;
    private int rest;
    @CreationTimestamp
    private LocalDateTime created_at;

    public Set() {
    }

    public Set(Exercise exercise, int sets, int reps, double weight, int rest) {
        /*        this.workout = workout;*/
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.rest = rest;
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

    /*    public Exercise getExercise() {
            return exercise;
        }*/
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    /*    public void setExercise(Exercise exercise) {this.exercise = exercise;}*/
}
