package com.technohacks.srms.service;

import com.technohacks.srms.model.Subject;
import com.technohacks.srms.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectService {
private final SubjectRepository repo;
public SubjectService(SubjectRepository repo) { this.repo = repo; }

public List<Subject> listAll() { return repo.findAll(); }
public void save(Subject s) { repo.save(s); }
public Subject get(Long id) { return repo.findById(id).orElse(null); }
public void delete(Long id) { repo.deleteById(id); }
}
