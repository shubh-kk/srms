package com.technohacks.srms.service;

import com.technohacks.srms.model.Result;
import com.technohacks.srms.model.Student;
import com.technohacks.srms.model.Subject;
import com.technohacks.srms.repository.ResultRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {
    private final ResultRepository repo;
    private final StudentService studentSvc;
    private final SubjectService subjectSvc;

    public ResultService(ResultRepository repo, StudentService studentSvc, SubjectService subjectSvc) {
        this.repo = repo;
        this.studentSvc = studentSvc;
        this.subjectSvc = subjectSvc;
    }

    public void save(Result r) {
        // Fetch the actual Student and Subject entities based on the IDs
        if (r.getStudent() != null && r.getStudent().getId() != null) {
            Student student = studentSvc.get(r.getStudent().getId());
            r.setStudent(student);
        }

        if (r.getSubject() != null && r.getSubject().getId() != null) {
            Subject subject = subjectSvc.get(r.getSubject().getId());
            r.setSubject(subject);
        }

        repo.save(r);
    }

    public List<com.technohacks.srms.model.Result> listAll() {
        return repo.findAll();
    }

    public List<com.technohacks.srms.model.Result> listByStudent(Student s) {
        return repo.findByStudent(s);
    }

    /** Build a report for each student */
    public List<ReportDTO> buildReports() {
        return studentSvc.listAll().stream()
                .map(s -> new ReportDTO(s, repo.findByStudent(s)))
                .collect(Collectors.toList());
    }
}
