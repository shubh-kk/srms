package com.technohacks.srms.service;


import com.technohacks.srms.model.ClassRoom;
import com.technohacks.srms.repository.ClassRoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassRoomService {
private final ClassRoomRepository repo;
public ClassRoomService(ClassRoomRepository repo) { this.repo = repo; }

public List<ClassRoom> listAll() { return repo.findAll(); }
public void save(ClassRoom cr) { repo.save(cr); }
public ClassRoom get(Long id) { return repo.findById(id).orElse(null); }
public void delete(Long id) { repo.deleteById(id); }
}
