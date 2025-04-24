package com.technohacks.srms.service;


import com.technohacks.srms.model.Notice;
import com.technohacks.srms.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeService {
private final NoticeRepository repo;
public NoticeService(NoticeRepository repo) { this.repo = repo; }

public List<Notice> listAll() { return repo.findAll(); }
public void save(Notice n)  { repo.save(n); }
public Notice get(Long id)  { return repo.findById(id).orElse(null); }
public void delete(Long id) { repo.deleteById(id); }
}
