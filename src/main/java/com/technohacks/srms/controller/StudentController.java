package com.technohacks.srms.controller;


import com.technohacks.srms.model.Student;
import com.technohacks.srms.service.ClassRoomService;
import com.technohacks.srms.service.StudentService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired 
    ClassRoomService classSvc;
	
    private final StudentService svc;
    public StudentController(StudentService svc) { this.svc = svc; }

    // List students
    @GetMapping
    public String list(HttpSession session, Model model) {
        if (session.getAttribute("adminUser") == null) {
            return "redirect:/login";
        }
        model.addAttribute("students", svc.listAll());
        return "students";
    }

    // Show form
    @GetMapping("/new")
    public String form(Model model) {
      model.addAttribute("student", new Student());
      model.addAttribute("classes", classSvc.listAll());
      return "student-form";
    }
    

    // Save (add/edit)
    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        svc.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
      model.addAttribute("student", svc.get(id));
      model.addAttribute("classes", classSvc.listAll());
      return "student-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        svc.delete(id);
        return "redirect:/students";
    }
    
    
}
