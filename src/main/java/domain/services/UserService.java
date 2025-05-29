package domain.services;

import java.util.List;

import domain.model.User;
import domain.ports.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    
    private final UserRepository repository;
    
    @Inject
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    public User createUser(User user) {
        repository.findByEmail(user.getEmail())
                 .ifPresent(u -> {
                     throw new IllegalArgumentException("Email already exists");
                 });
        return repository.save(user);
    }
    
    public User getUserById(String id) {
        return repository.findById(id)
                         .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email)
                         .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    
    public void deleteUser(String id) {
        if (!repository.findById(id).isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        repository.delete(id);
    }       

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User updateUser(User user) {
        if (!repository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        return repository.save(user);
    }

    public boolean userExists(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public boolean emailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }
}

