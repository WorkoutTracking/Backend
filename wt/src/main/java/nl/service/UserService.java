package nl.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import nl.domain.UserAccount;
import nl.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public UserService() {
    }

    public List<UserAccount> allUsers() {
        return  userRepository.listAll();
    }

    public UserAccount findUserById(UUID id){ return userRepository.find("id", id).firstResult();}

    public UserAccount addUser(UserAccount userAccount) {
        userAccount.setPassword(BcryptUtil.bcryptHash(userAccount.getPassword()));
        userRepository.persist(userAccount);
        return userAccount;
    }
}
