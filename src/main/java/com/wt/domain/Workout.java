package com.wt.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Workout {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @NotBlank(message = "User email cannot be blank.")
    private String userEmail;
    @NotBlank(message = "Name cannot be blank.")
    private String name;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Workout() {
    }

    public Workout(String userEmail, String name) {
        this.userEmail = userEmail;
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
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
