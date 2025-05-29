package application;

import application.commands.CreateUserCommand;
import domain.model.User;
import domain.ports.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserApplicationService {
    
    private final UserRepository userRepository;
    
    @Inject
    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User createUser(CreateUserCommand command) {
        var user = User.builder()
            .name(command.getName())
            .email(command.getEmail())
            .role(command.getRole())
            .build();
            
        return userRepository.save(user);
    }
}
