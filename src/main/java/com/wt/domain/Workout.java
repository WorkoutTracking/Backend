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
    private String user_email;
    @NotBlank(message = "Name cannot be blank.")
    private String name;
    @CreationTimestamp
    private LocalDateTime created_at;

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

    public LocalDateTime getCreated_at() {
        return created_at;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
