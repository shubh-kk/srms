package com.technohacks.srms.service;

import com.technohacks.srms.model.Student;
import java.util.List;

public class ReportDTO {
    private Student student;
    private double total;
    private double average;
    private String grade;
    private List<com.technohacks.srms.model.Result> results;

    // constructor
    public ReportDTO() {}
    
    public ReportDTO(Student student, List<com.technohacks.srms.model.Result> results) {
        this.student = student;
        this.results = results;
        this.total = results.stream().mapToDouble(r -> r.getMarks()).sum();
        this.average = results.isEmpty() ? 0 : total / results.size();
        this.grade = calculateGrade(this.average);
    }

    private String calculateGrade(double avg) {
        if (avg >= 90) return "A";
        if (avg >= 75) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "F";
    }

    // getters
    public Student getStudent() { return student; }
    public double getTotal()        { return total; }
    public double getAverage()      { return average; }
    public String getGrade()        { return grade; }
    public List<com.technohacks.srms.model.Result> getResults() { return results; }
}

