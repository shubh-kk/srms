package com.technohacks.srms.service;

import com.technohacks.srms.model.Result;
import com.technohacks.srms.model.Student;
import com.technohacks.srms.repository.ResultRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {
    private final ResultRepository repo;
    private final StudentService studentSvc;

    public ResultService(ResultRepository repo, StudentService studentSvc) {
        this.repo = repo;
        this.studentSvc = studentSvc;
    }

    public void save(Result r) {
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
