package nl.resource;

import nl.domain.User;
import nl.service.UserService;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/users")
public class UserResource {
    @Inject
    private UserService userService;

    public UserResource(){

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> allUsers(){
        return userService.allUsers();
    }

    @POST
    @Transactional
    public Response addUser(User user) {
        User userWithId = userService.addUser(user);
        return Response.created(URI.create("/api/users" + userWithId.getId())).build();
    }
}
