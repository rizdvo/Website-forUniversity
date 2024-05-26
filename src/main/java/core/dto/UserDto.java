package core.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserDto {
    String name;
    String password;
    String confirmPassword;
    String email;
}
