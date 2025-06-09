package domain.model;

import domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @BsonId
    private ObjectId id;
    private String name;    
    private String email;
    private String password; 
    private List<Role> roles;
    @Builder.Default 
    private LocalDateTime createdAt = LocalDateTime.now();


}

