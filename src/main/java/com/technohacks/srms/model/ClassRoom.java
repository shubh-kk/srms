package com.technohacks.srms.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "class_rooms")
public class ClassRoom {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String name;

@Column(nullable = false)
private String section;

// Bi-directional if you want to navigate back from ClassRoom to Students
@OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
private Set<Student> students;

// getters & setters...
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getSection() { return section; }
public void setSection(String section) { this.section = section; }
public Set<Student> getStudents() { return students; }
public void setStudents(Set<Student> students) { this.students = students; }
}
