package com.wt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import com.wt.domain.Exercise;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExerciseRepository implements PanacheRepository<Exercise> {

}
