package com.technohacks.srms.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping({"/", ""})
  public String home(HttpSession session) {
    // If already logged in, go to students; otherwise show login
    return (session.getAttribute("adminUser") != null)
      ? "redirect:/students"
      : "redirect:/login";
  }
}
