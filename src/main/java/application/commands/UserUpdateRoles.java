package application.commands;

import java.util.List;

import domain.enums.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserUpdateRoles {
    private List<Role> roles;
}
