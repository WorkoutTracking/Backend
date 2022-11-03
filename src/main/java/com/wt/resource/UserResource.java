package com.wt.resource;

import com.wt.domain.UserAccount;
import com.wt.service.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/api/users")
public class UserResource {
    @Inject
    private UserService userService;

    public UserResource(){

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserAccount> allUsers(){
        return userService.allUsers();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") UUID id){
        UserAccount user = userService.findUserById(id);
        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            return Response.ok(user).build();
        }
    }

    @POST
    @Transactional
    public Response addUser(UserAccount userAccount) {
        UserAccount userAccountWithId = userService.addUser(userAccount);
        return Response.created(URI.create("/api/users/" + userAccountWithId.getId())).build();
    }
}
