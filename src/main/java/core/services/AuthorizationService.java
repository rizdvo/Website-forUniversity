package core.services;

import core.entities.User;
import core.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class AuthorizationService {

    UserRepository userRepository;

    public boolean isAuthorized(String username, String password) {
        Optional<User> userOptional = userRepository.findBy(username, password);
        return userOptional.isPresent();
    }

    public Optional<User> findBy(String username) {
        return userRepository.findBy(username);
    }

    public Optional<User> create(String username, String password, String confirmPassword, String email) {
        if (password.equals(confirmPassword)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            userRepository.saveOrUpdate(user);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
