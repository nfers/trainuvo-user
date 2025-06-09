package interfaces.rest;

import application.UserApplicationService;
import application.commands.CreateUserCommand;
import application.commands.UserUpdateRoles;
import interfaces.rest.dto.request.UpdateUserRolesRequest;
import interfaces.rest.dto.request.UserRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserApplicationService applicationService;

    @Inject
    public UserResource(UserApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @POST
    public Response createUser(UserRequest request) {
        var command = CreateUserCommand.builder()
                .name(request.getName())
                .email(request.getEmail())
                .roles(request.getRoles())
                .password(request.getPassword())
                .build();

        try {
            var user = applicationService.createUser(command);
            return Response.ok(user).build();
        } catch (application.ConflictException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(e.getMessage())
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{email}")
    public Response findUserByEmail(@PathParam("email") String email) {
        try {
            var user = applicationService.findUserByEmail(email);
            return Response.ok(user).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PATCH
    @Path("/{email}")
    public Response updateUser(@PathParam("email") String email, UpdateUserRolesRequest request) {
        var command = UserUpdateRoles.builder()
                .roles(request.getRoles())
                .build();
        try {
            var user = applicationService.updateUserRoles(email, command.getRoles());
            return Response.ok(user).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{email}")
    public Response deleteUser(@PathParam("email") String email) {
        try {
            applicationService.deleteUserByEmail(email);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }
}