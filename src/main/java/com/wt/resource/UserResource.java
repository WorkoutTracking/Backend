package com.wt.resource;

import com.wt.domain.UserAccount;
import com.wt.service.UserService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/api/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class UserResource {
    @Inject
    private UserService userService;

    public UserResource() {

    }

    @GET
    public List<UserAccount> allUsers() {
        return userService.allUsers();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        UserAccount user = userService.findUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(user).build();
        }
    }

    @POST
    @Transactional
    @Path("/{name}/{user_email}")
    public Response addUser(@PathParam("name") String name, @PathParam("user_email") String user_email) {
        UserAccount userAccount = userService.addUser(name, user_email);
        if (userAccount == null) {
            return Response.notModified().build();
        } else {
            return Response.created(URI.create("/api/users/" + userAccount.getId())).build();
        }
    }
}
