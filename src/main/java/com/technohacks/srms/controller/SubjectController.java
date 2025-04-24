package com.technohacks.srms.controller;

import com.technohacks.srms.model.Subject;
import com.technohacks.srms.service.SubjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
	private final SubjectService svc;

	public SubjectController(SubjectService svc) {
		this.svc = svc;
	}

	@GetMapping
	public String list(HttpSession session, Model model) {
		if (session.getAttribute("adminUser") == null)
			return "redirect:/login";
		model.addAttribute("subjects", svc.listAll());
		return "subjects";
	}

	@GetMapping("/new")
	public String form(HttpSession session, Model model) {
		if (session.getAttribute("adminUser") == null) {
			return "redirect:/login";
		}
		model.addAttribute("subject", new Subject());
		return "subject-form"; // must match the template filename
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Subject subject) {
		svc.save(subject);
		return "redirect:/subjects";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("subject", svc.get(id));
		return "subject-form";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		svc.delete(id);
		return "redirect:/subjects";
	}
}
