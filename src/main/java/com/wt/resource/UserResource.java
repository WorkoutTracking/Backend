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

    @GET
    public List<UserAccount> allUsers() {
        return userService.allUsers();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        UserAccount user = userService.findUserById(id);
        return (user != null) ? Response.ok(user).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    @Path("/{name}/{user_email}")
    public Response addUser(@PathParam("name") String name, @PathParam("user_email") String userEmail) {
        try {
            UserAccount userAccount = userService.addUser(name, userEmail);
            return Response.created(URI.create("/api/users/" + userAccount.getId())).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_IMPLEMENTED).entity(ex.getMessage()).build();
        }
    }
}
