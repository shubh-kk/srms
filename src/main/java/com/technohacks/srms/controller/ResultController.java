package com.technohacks.srms.controller;

import com.technohacks.srms.model.Result;
import com.technohacks.srms.model.Student;
import com.technohacks.srms.model.Subject;
import com.technohacks.srms.service.ResultService;
import com.technohacks.srms.service.StudentService;
import com.technohacks.srms.service.SubjectService;
import com.technohacks.srms.service.ReportDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultController {
    private final ResultService resultSvc;
    private final StudentService studentSvc;
    private final SubjectService subjectSvc;

    public ResultController(ResultService resultSvc,StudentService studentSvc,SubjectService subjectSvc) {
        this.resultSvc = resultSvc;
        this.studentSvc = studentSvc;
        this.subjectSvc = subjectSvc;
    }

    // Show form to enter a result - Only admin access
    @GetMapping("/new")
    public String entryForm(HttpSession session, Model model) {
        if (session.getAttribute("adminUser") == null) {
            return "redirect:/login";
        }

        // Initialize a new Result with empty Student and Subject objects
        Result result = new Result();
        if (result.getStudent() == null) {
            result.setStudent(new Student());
        }
        if (result.getSubject() == null) {
            result.setSubject(new Subject());
        }

        model.addAttribute("result", result);
        model.addAttribute("students", studentSvc.listAll());
        model.addAttribute("subjects", subjectSvc.listAll());
        return "result-entry"; // must match the template name
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Result result) {
        // Debug info
        System.out.println("Student ID: " + (result.getStudent() != null ? result.getStudent().getId() : "null"));
        System.out.println("Subject ID: " + (result.getSubject() != null ? result.getSubject().getId() : "null"));
        System.out.println("Marks: " + result.getMarks());

        resultSvc.save(result);
        return "redirect:/results/new";
    }

    // Show all raw results (only admin)
    @GetMapping
    public String listAll(HttpSession session, Model model) {
        if (session.getAttribute("adminUser") == null)
            return "redirect:/login";
        model.addAttribute("results", resultSvc.listAll());
        return "results";
    }

    // Public Reporting page - No login required
    @GetMapping("/report")
    public String report(Model model) {
        List<ReportDTO> reports = resultSvc.buildReports();
        model.addAttribute("reports", reports);
        return "reports";
    }
    
    // View detailed results for a specific student
    @GetMapping("/student/{id}")
    public String studentReport(@PathVariable Long id, Model model) {
        Student student = studentSvc.get(id);
        if (student != null) {
            List<Result> studentResults = resultSvc.listByStudent(student);
            ReportDTO report = new ReportDTO(student, studentResults);
            model.addAttribute("report", report);
            return "student-report";
        }
        return "redirect:/results/report";
    }
}