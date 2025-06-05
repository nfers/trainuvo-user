package domain.model;

import domain.enums.Role;
import lombok.Builder;
import lombok.Data;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Data
@Builder
public class User {
    @BsonProperty(value = "id")
    @BsonId
    private ObjectId id;
    private String name;
    private String email;
    private Role role;


    @BsonCreator
    public User(@BsonId ObjectId id, @BsonProperty("name") String name, @BsonProperty("email") String email, @BsonProperty("role") Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
    
}

