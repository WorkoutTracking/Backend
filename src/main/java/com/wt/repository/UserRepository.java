package com.wt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import com.wt.domain.UserAccount;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserAccount> {

}
