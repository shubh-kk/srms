package com.technohacks.srms.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notices")
public class Notice {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String title;

@Column(nullable = false, columnDefinition = "TEXT")
private String content;

@Column(nullable = false)
private LocalDate datePosted = LocalDate.now();

// getters & setters...
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }
public String getContent() { return content; }
public void setContent(String content) { this.content = content; }
public LocalDate getDatePosted() { return datePosted; }
public void setDatePosted(LocalDate datePosted) { this.datePosted = datePosted; }
}
