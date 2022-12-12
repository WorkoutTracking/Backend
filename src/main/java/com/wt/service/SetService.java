package com.wt.service;

import com.wt.domain.Set;
import com.wt.repository.SetRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SetService {
    @Inject
    SetRepository setRepository;

    @Inject
    UserService userService;

    @Inject
    SecurityIdentity securityIdentity;

    public List<Set> allSets() {
        return setRepository.listAll();
    }

    public List<Set> findSetsByExerciseId(UUID exerciseId) {
        return setRepository.find("#Set.getByExerciseAndOrderByCreated",
                Parameters.with("exerciseId", exerciseId)).list();
    }

    public String addSet(UUID exerciseId, String userEmail) {
        if (!userService.checkIfUserExists(userEmail)) {
            return "User does not exist";
        }
        if (!securityIdentity.getPrincipal().getName().equals(userEmail)) {
            return "User not the same as token user";
        }
        if (exerciseId == null) {
            return "ExerciseId is not defined";
        }

        Set set = new Set(exerciseId, 0, 0, 0, 0);
        setRepository.persist(set);
        return "Set created";
    }

    public String updateSet(UUID setId, int sets, int reps, double weight, int rest, String userEmail) {
        if (!userService.checkIfUserExists(userEmail)) {
            return "User does not exist";
        }
        if (!securityIdentity.getPrincipal().getName().equals(userEmail)) {
            return "User not the same as token user";
        }
        if (setId == null) {
            return "ExerciseId is not defined";
        }

        try {
            setRepository.update("#Set.updateSet",
                    Parameters.with("sets", sets)
                            .and("reps", reps)
                            .and("weight", weight)
                            .and("rest", rest)
                            .and("id", setId));
            return "Set updated";
        } catch (Exception error) {
            return String.format("{\"message\": \"%s\"}", error.getMessage());
        }
    }

    public String deleteSet(UUID id, String userEmail) {
        if (!userService.checkIfUserExists(userEmail)) {
            return "User does not exist";
        }
        if (!securityIdentity.getPrincipal().getName().equals(userEmail)) {
            return "User does not equal token";
        }

        try {
            setRepository.delete("id", id);
            return "Set is deleted";
        } catch (Exception error) {
            return "Something went wrong";
        }
    }
}
