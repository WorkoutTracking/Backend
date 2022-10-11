package nl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Workout {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID userId;
    @GeneratedValue(generator = "DATETIME")
    private LocalDateTime createdAt;

    public Workout() {
    }

    public Workout(UUID userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
