package com.technohacks.srms.repository;


import com.technohacks.srms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> { }
