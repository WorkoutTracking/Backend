package nl.domain;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.jdbc.Work;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@UserDefinition
public class UserAccount {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Username
    private String username;
    private String name;
    private String email;
    @Password
    private String password;
    @Roles
    private String role = "admin";
    @CreationTimestamp
    private LocalDateTime created_at;
    @OneToMany
    private Set<Workout> workouts;

    public UserAccount() {
    }

    public UserAccount(String username, String name, String email, String password, String role) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = BcryptUtil.bcryptHash(password);
        this.role = role;
    }

    public UUID getId() {
        return id;
    }
    public String getUsername() {
        return username;
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
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) { this.password = BcryptUtil.bcryptHash(password); }
}
