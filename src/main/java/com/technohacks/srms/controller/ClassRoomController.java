package com.technohacks.srms.controller;

import com.technohacks.srms.model.ClassRoom;
import com.technohacks.srms.service.ClassRoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classes")
public class ClassRoomController {
private final ClassRoomService svc;
public ClassRoomController(ClassRoomService svc) { this.svc = svc; }

@GetMapping
public String list(HttpSession session, Model model) {
 if (session.getAttribute("adminUser")==null) return "redirect:/login";
 model.addAttribute("classes", svc.listAll());
 return "class-rooms";
}

@GetMapping("/new")
public String form(Model model) {
 model.addAttribute("classRoom", new ClassRoom());
 return "class-room-form";
}

@PostMapping("/save")
public String save(@ModelAttribute ClassRoom classRoom) {
 svc.save(classRoom);
 return "redirect:/classes";
}

@GetMapping("/edit/{id}")
public String edit(@PathVariable Long id, Model model) {
 model.addAttribute("classRoom", svc.get(id));
 return "class-room-form";
}

@GetMapping("/delete/{id}")
public String delete(@PathVariable Long id) {
 svc.delete(id);
 return "redirect:/classes";
}
}
