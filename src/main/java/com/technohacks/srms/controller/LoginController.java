package com.technohacks.srms.controller;

import com.technohacks.srms.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    private final AdminService adminService;
    public LoginController(AdminService adminService) { this.adminService = adminService; }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
        @RequestParam String username,
        @RequestParam String password,
        HttpSession session,
        Model model) 
    {
        if (adminService.authenticate(username, password)) {
            session.setAttribute("adminUser", username);
            return "redirect:/";
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
