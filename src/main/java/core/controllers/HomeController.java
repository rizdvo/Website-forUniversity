package core.controllers;

import core.entities.User;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Controller
@AllArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public String getHomePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("username", username);
            return "home";
        } else {
            return "redirect:/auth";
        }
    }
}
