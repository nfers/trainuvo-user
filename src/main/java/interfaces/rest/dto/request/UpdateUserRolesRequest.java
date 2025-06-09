package interfaces.rest.dto.request;

import java.util.List;

import domain.enums.Role;
import lombok.Data;

@Data
public class UpdateUserRolesRequest {
    private List<Role> roles;
}
