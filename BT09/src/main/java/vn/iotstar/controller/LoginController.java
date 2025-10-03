package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user,
                        BindingResult result,
                        HttpSession session,
                        Model model) {

        if(result.hasErrors()) {
            return "login";
        }

        User u = userService.login(user.getEmail(), user.getUserPassword());
        if(u != null) {
            session.setAttribute("ROLE", u.getRole()); // giả sử User entity có role: "ADMIN"/"USER"
            session.setAttribute("USER", u);

            if("ADMIN".equals(u.getRole())) return "redirect:/admin/home";
            if("USER".equals(u.getRole())) return "redirect:/user/home";
        }

        model.addAttribute("error", "Email hoặc password không đúng");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
