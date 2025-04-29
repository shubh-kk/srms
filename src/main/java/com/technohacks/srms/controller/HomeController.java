package com.technohacks.srms.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  // @GetMapping({"/", ""})
  // public String home(HttpSession session) {
  //   // If already logged in, go to students; otherwise show login
  //   return (session.getAttribute("adminUser") != null)
  //     ? "redirect:/students"
  //     : "redirect:/login";
  // }

  @GetMapping({"/", "/home"})
  public String home(Model model, HttpSession session) {
    // expose the adminUser session attribute to Thymeleaf
    model.addAttribute("adminUser", session.getAttribute("adminUser"));
    return "home";   // render home.html
  }

  // @GetMapping("/")
  // public String home() {
  //     return "home";
  // }
  
  // // Redirect any requests to /home to the root URL
  // @GetMapping("/home")
  // public String redirectHome() {
  //     return "redirect:/";
  // }
}
