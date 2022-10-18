package nl.domain;

import io.vertx.ext.auth.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class Workout {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @ManyToOne
    private UserAccount user;
    private String name;
    @CreationTimestamp
    private LocalDateTime created_at;

    public Workout() {
    }

    public Workout(String name) {
        this.name = name;
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
    public void setName(String name) {
        this.name = name;
    }

}
