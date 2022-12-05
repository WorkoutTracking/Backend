package com.wt.service;

import com.wt.domain.UserAccount;
import com.wt.repository.UserRepository;

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
        return userRepository.listAll();
    }

    public UserAccount findUserById(UUID id) {
        return userRepository.find("id", id).firstResult();
    }

    public UserAccount addUser(String name, String user_email) {
        if (checkIfUserExists(user_email))
        {
            return null;
        } else {
            UserAccount userAccount = new UserAccount();
            userAccount.setName(name);
            userAccount.setEmail(user_email);

            userRepository.persist(userAccount);
            return userAccount;
        }
    }

    public boolean checkIfUserExists(String email)
    {
        long count = userRepository.find("email", email).count();
        return count != 0;
    }
}
