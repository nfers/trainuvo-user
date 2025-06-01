package application;

import application.commands.CreateUserCommand;
import domain.model.User;
import domain.ports.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserApplicationService {
    
    @Inject
    UserRepository userRepository;

    public User createUser(CreateUserCommand command) {
        var user = User.builder()
            .name(command.getName())
            .email(command.getEmail())
            .role(command.getRole())
            .build();
            
        userRepository.persist(user);
        
        return userRepository.findByEmail(command.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("User with email " + command.getEmail() + " not created"));
    }
}
