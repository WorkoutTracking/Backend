package com.wt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import com.wt.domain.Workout;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WorkoutRepository implements PanacheRepository<Workout> {
}
