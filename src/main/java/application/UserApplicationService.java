package application;


import java.util.List;

import application.commands.CreateUserCommand;
import domain.model.User;
import domain.ports.UserRepository;
import domain.valueobjects.Email;
import interfaces.rest.dto.response.UserResponse;
import jakarta.enterprise.context.ApplicationScoped;
import domain.enums.Role;

@ApplicationScoped
public class UserApplicationService {
    
    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserCommand command) {
        if (!Email.isValid(command.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        String email = command.getEmail();


        userRepository.findByEmail(email).ifPresent(u -> {
            throw new ConflictException("User with email " + email + " already exists");
        });

        User user = User.builder()
            .name(command.getName())
            .email(email)
            .roles(command.getRoles())
            .password(command.getPassword())
            .build();

        userRepository.persist(user);

        User created = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not created"));

        return UserResponse.from(
            created.getName(),
            created.getEmail(),
            created.getRoles()
        );
    }

    public UserResponse findUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .map(user -> UserResponse.from(
                user.getName(),
                user.getEmail(),
                user.getRoles()
            ))
            .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found"));

    }

    public UserResponse updateUserRoles(String email, List<Role> roles) {
        var user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found"));
        user.setRoles(roles);
        userRepository.persistOrUpdate(user);
        return UserResponse.from(
            user.getName(),
            user.getEmail(),
            user.getRoles()
        );
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
}
