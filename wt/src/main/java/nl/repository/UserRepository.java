package nl.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import nl.domain.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
