package com.technohacks.srms.service;


import com.technohacks.srms.model.Student;
import com.technohacks.srms.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;
    public StudentService(StudentRepository repo) { this.repo = repo; }

    public List<Student> listAll() { return repo.findAll(); }
    public void save(Student s) { repo.save(s); }
    public Student get(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
}
