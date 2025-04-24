package com.technohacks.srms.repository;


import com.technohacks.srms.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> { }
