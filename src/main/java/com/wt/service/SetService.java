package com.wt.service;

import com.wt.domain.Set;
import com.wt.repository.SetRepository;

import io.quarkus.logging.Log;
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

    private static final String INVALIDUSER = "Error: Invalid user";

    public List<Set> allSets() {
        return setRepository.listAll();
    }

    public List<Set> findSetsByExerciseId(UUID exerciseId) {
        return setRepository.find("#Set.getByExerciseAndOrderByCreated",
                Parameters.with("exerciseId", exerciseId)).list();
    }

    public String addSet(UUID exerciseId, String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException(INVALIDUSER);
        }
        if (exerciseId == null) {
            throw new IllegalArgumentException("exerciseId is not defined");
        }
        try {
            Set set = new Set(exerciseId, 0, 0, 0, 0, true);
            setRepository.persist(set);
            return "Set created";
        } catch (Exception error) {
            return error.getMessage();
        }
    }

    public String addSetFromPreviousExercise(UUID exerciseId, int sets, int reps, double weight, int rest) {
        if (exerciseId == null) {
            throw new IllegalArgumentException("Set is not defined");
        }
        try {
            Set newSet = new Set(exerciseId, sets, reps, weight, rest, true);  
            setRepository.persist(newSet);
            return "Set created";
        } catch (Exception error) {
            return error.getMessage();
        }
    }

    public String updateSet(UUID setId, int sets, int reps, double weight, int rest, String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException(INVALIDUSER);
        }
        if (setId == null) {
            throw new IllegalArgumentException("setId is not defined");
        }

        try {
            setRepository.update("#Set.updateSet",
                    Parameters.with("sets", sets)
                            .and("reps", reps)
                            .and("weight", weight)
                            .and("rest", rest)
                            .and("id", setId)
                            .and("placeHolder", false));
            return "Set updated";
        } catch (Exception error) {
            return error.getMessage();
        }
    }

    public String deleteSet(UUID id, String userEmail) {
        if (!userService.checkIfUserExists(userEmail) || !securityIdentity.getPrincipal().getName().equals(userEmail)) {
            throw new IllegalArgumentException(INVALIDUSER);
        }

        try {
            setRepository.delete("id", id);
            return "Set is deleted";
        } catch (Exception error) {
            return error.getMessage();
        }
    }
}
