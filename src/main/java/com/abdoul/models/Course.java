package com.abdoul.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code")
    private String courseCode;

    @JoinColumn(name = "semester_id")
    @ManyToOne
    private Semester semester;

    @OneToMany(mappedBy = "course")
    private List<StudentCourses> StudentCourses = new ArrayList<>();

    public Course() {
    }

    public Course(String courseName, String courseCode, Semester semester) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.semester = semester;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
    
    public List<StudentCourses> getStudentCourses() {
        return StudentCourses;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", courseName=" + courseName + ", courseCode=" + courseCode + ", semester="
                + semester + "]";
    }

    
}
