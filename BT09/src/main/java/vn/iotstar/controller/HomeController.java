package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/admin/home")
    public String adminHome(HttpSession session) {
        String role = (String) session.getAttribute("ROLE");
        if(!"ADMIN".equals(role)) return "redirect:/login";
        return "home"; // home.html
    }

    @GetMapping("/user/home")
    public String userHome(HttpSession session) {
        String role = (String) session.getAttribute("ROLE");
        if(!"USER".equals(role)) return "redirect:/login";
        return "home"; // home.html
    }
}