package com.technohacks.srms.service;


import com.technohacks.srms.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository repo;
    public AdminService(AdminRepository repo) { this.repo = repo; }

    public boolean authenticate(String user, String pass) {
        return repo.findByUsername(user)
                   .map(a -> a.getPassword().equals(pass))
                   .orElse(false);
    }
}
