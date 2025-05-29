package domain.model;

import org.bson.codecs.pojo.annotations.BsonId;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Builder;
import lombok.Data;
import domain.enums.Role;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@MongoEntity(collection="users")
public class User extends PanacheMongoEntity {
    @BsonId
    private String id;
    private String name;
    private String email;
    private Role role;
}

