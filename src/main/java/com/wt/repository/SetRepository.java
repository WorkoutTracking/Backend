package com.wt.repository;

import com.wt.domain.Set;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SetRepository implements PanacheRepository<Set> {

}
