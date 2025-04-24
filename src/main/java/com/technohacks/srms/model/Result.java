package com.technohacks.srms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private Double marks;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }
}
