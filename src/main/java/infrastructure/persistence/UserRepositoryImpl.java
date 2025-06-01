package infrastructure.persistence;

import domain.model.User;
import domain.ports.UserRepository;
import io.quarkus.logging.Log;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;

import org.bson.types.ObjectId;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @Inject
    PanacheMongoRepository<User> panacheRepo;

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(panacheRepo.findById(ObjectId.isValid(id) ? new ObjectId(id) : null));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(panacheRepo.find("email", email).firstResult());
    }

    @Override
    public void delete(String id) {
        panacheRepo.deleteById(ObjectId.isValid(id) ? new ObjectId(id) : null);
    }




}
