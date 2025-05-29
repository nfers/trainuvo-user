package application.commands;

import domain.enums.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserCommand {
    String name;
    String email;
    Role role;
}
