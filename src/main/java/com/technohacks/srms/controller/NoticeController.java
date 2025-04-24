package com.technohacks.srms.controller;

import com.technohacks.srms.model.Notice;
import com.technohacks.srms.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoticeController {
	private final NoticeService svc;

	public NoticeController(NoticeService svc) {
		this.svc = svc;
	}

// Admin-only
	@GetMapping("/notices")
	public String listAdmin(HttpSession session, Model model) {
		if (session.getAttribute("adminUser") == null)
			return "redirect:/login";
		model.addAttribute("notices", svc.listAll());
		return "notices";
	}

	@GetMapping("/notices/new")
	public String form(Model model) {
		model.addAttribute("notice", new Notice());
		return "notice-form";
	}

	@PostMapping("/notices/save")
	public String save(@ModelAttribute Notice notice) {
		svc.save(notice);
		return "redirect:/notices";
	}

	@GetMapping("/notices/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("notice", svc.get(id));
		return "notice-form";
	}

	@GetMapping("/notices/delete/{id}")
	public String delete(@PathVariable Long id) {
		svc.delete(id);
		return "redirect:/notices";
	}

// Public-facing
	@GetMapping("/announcements")
	public String listPublic(Model model) {
		model.addAttribute("notices", svc.listAll());
		return "notice-list-public";
	}
}
