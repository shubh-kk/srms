package com.technohacks.srms.repository;

import com.technohacks.srms.model.Result;
import com.technohacks.srms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudent(Student student);
}
