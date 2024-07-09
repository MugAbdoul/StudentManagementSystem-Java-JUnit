package com.abdoul.models;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "students_courses")
public class StudentCourses {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    
    @JoinColumn(name = "course_code")
    @ManyToOne
    private Course course;
    
    @Column(name = "marks_in_course")
    private int marksIncourse;
    
    @JoinColumn(name = "registration_number")
    @ManyToOne
    private StudentRegistration studentRegistration;

    public StudentCourses() {
    }

    public StudentCourses(Course course, int marksIncourse, StudentRegistration studentRegistration) {
        this.course = course;
        this.marksIncourse = marksIncourse;
        this.studentRegistration = studentRegistration;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getMarksIncourse() {
        return marksIncourse;
    }

    public void setMarksIncourse(int marksIncourse) {
        this.marksIncourse = marksIncourse;
    }

    public StudentRegistration getStudentRegistration() {
        return studentRegistration;
    }

    public void setStudentRegistration(StudentRegistration studentRegistration) {
        this.studentRegistration = studentRegistration;
    }

    @Override
    public String toString() {
        return "StudentCourses [id=" + id + ", course=" + course + ", marksIncourse=" + marksIncourse
                + ", studentRegistration=" + studentRegistration + "]";
    }
    
}
