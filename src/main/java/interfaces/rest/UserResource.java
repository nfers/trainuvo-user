package interfaces.rest;

import application.UserApplicationService;
import application.commands.CreateUserCommand;
import interfaces.rest.dto.UserRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
            .role(request.getRole())
            .build();
        

        var user = applicationService.createUser(command);
        return Response.ok(user).build();
    }
}
