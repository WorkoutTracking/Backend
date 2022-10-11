package nl.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import nl.domain.Workout;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WorkoutRepository implements PanacheRepository<Workout> {
}
