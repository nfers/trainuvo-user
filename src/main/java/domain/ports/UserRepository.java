package domain.ports;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import java.util.Optional;
import domain.model.User;
import org.bson.types.ObjectId;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(find("email", email).firstResult());
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(findByIdOptional(new ObjectId(id)).orElse(null));
    }

    public void deleteByEmail(String email) {
        findByEmail(email).ifPresent(this::delete);
    }
}