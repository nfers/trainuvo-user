package interfaces.rest.dto.request;

import java.util.List;
import domain.enums.Role;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String password;
    private String email;
    private List<Role> roles;
}
