package com.technohacks.srms.repository;


import com.technohacks.srms.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> { }
