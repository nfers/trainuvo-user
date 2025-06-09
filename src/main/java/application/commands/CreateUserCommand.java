package application.commands;

import domain.enums.Role;
import lombok.Builder;
import lombok.Value;
import java.util.List;

@Value
@Builder
public class CreateUserCommand {
    String name;
    String email;
    String password;
    List<Role> roles;
}
