package domain.ports;

import domain.model.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import java.util.Optional;

public interface UserRepository extends PanacheMongoRepository<User> {
    
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
    void delete(String id);
    
}