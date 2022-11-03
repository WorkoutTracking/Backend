package com.wt.domain;

import io.quarkus.security.jpa.Roles;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String email;
    @Roles
    private String role = "admin";
    @CreationTimestamp
    private LocalDateTime created_at;
    @OneToMany
    private Set<Workout> workouts;

    public UserAccount() {
    }

    public UserAccount(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // workflow test 5
}