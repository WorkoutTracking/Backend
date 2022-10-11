package nl.service;

import nl.domain.User;
import nl.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public UserService() {
    }

    public List<User> allUsers() {
        return  userRepository.listAll();
    }

    public User addUser(User user) {
        userRepository.persist(user);
        return user;
    }
}
