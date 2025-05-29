package interfaces.rest.dto;

import domain.enums.Role;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private Role role;
}
