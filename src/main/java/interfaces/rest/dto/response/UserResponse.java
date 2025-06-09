package interfaces.rest.dto.response;

import lombok.Builder;
import lombok.Data;
import domain.enums.Role;
import java.util.List;

@Data
@Builder
public class UserResponse {
    private String name;
    private String email;
    private List<Role> roles;

    public static UserResponse from(String name, String email, List<Role> roles) {
        return UserResponse.builder()
                .name(name)
                .email(email)
                .roles(roles)
                .build();
    }
}
