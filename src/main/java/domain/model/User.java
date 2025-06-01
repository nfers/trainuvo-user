package domain.model;

import domain.enums.Role;
import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Data
@Builder
public class User {
    @BsonId
    private ObjectId id;
    private String name;
    private String email;
    private Role role;
}

