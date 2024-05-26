package core.controllers;

import core.dto.UserDto;
import core.entities.User;
import core.services.AuthorizationService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Data
@Controller
@AllArgsConstructor
public class AuthorizationController {

    AuthorizationService authorizationService;

    @GetMapping("/auth")
    public String getAuthorizationPage(HttpSession session) {
        session.removeAttribute("username");
        return "authorization";
    }

    @GetMapping("/auth-message")
    public String getAuthorizationErrorPage(Model model) {
        model.addAttribute("message", "Invalid username or password");
        model.addAttribute("result", "danger");
        return "authorization";
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (authorizationService.isAuthorized(username, password)) {
           session.setAttribute("username", username);
            return "redirect:/home";
        } else {
            return "redirect:/auth-message";
        }
    }

    @PostMapping("/auth/register")
    public String register(@RequestBody UserDto userDto, Model model, HttpSession session) {
        Optional<User> registeredUser = authorizationService.findBy(userDto.getName());

        if (userDto.getName().isEmpty() || userDto.getPassword().isEmpty() || userDto.getConfirmPassword().isEmpty() || userDto.getEmail().isEmpty()) {
            model.addAttribute("message", "All fields are not required");
            model.addAttribute("result", "danger");
            return "authorization";
        }

        if (registeredUser.isPresent()) {
            model.addAttribute("message", "User already exists");
            model.addAttribute("result", "danger");
        } else {
            Optional<User> user = authorizationService.create(userDto.getName(), userDto.getPassword(), userDto.getConfirmPassword(), userDto.getEmail());
            if (user.isPresent()) {
                model.addAttribute("message", "User registered successfully");
                model.addAttribute("result", "success");
            } else {
                model.addAttribute("message", "Invalid confirm password");
                model.addAttribute("result", "danger");
            }
        }
        return "authorization";
    }

    @GetMapping("/auth/register-message")
    public String getRegisterMessage(HttpSession session, Model model) {
        String message = (String) session.getAttribute("message");
        String result = (String) session.getAttribute("result");
        if (message != null && result != null) {
            model.addAttribute("message", message);
            model.addAttribute("message", result);
            return "authorization";
        } else {
            return "redirect:/auth";
        }

    }
}
