package domain.valueobjects;

import lombok.Value;

@Value
public class Email {
    String value;
    
    public Email(String email) {
        if (!isValid(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = email;
    }
    
    private boolean isValid(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
