package nl.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import nl.domain.Exercise;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExerciseRepository implements PanacheRepository<Exercise> {

}
