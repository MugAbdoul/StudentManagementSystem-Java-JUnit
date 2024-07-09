package com.abdoul.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "semester")
public class Semester {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "semester_name")
    private String semesterName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy = "semester")
    private List<StudentRegistration> studentRegistrations = new ArrayList<>();

    @OneToMany(mappedBy = "semester")
    private List<Course> courses = new ArrayList<>();

    public Semester() {
    }

    public Semester(String semesterName, Date startDate, Date endDate) {
        this.semesterName = semesterName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<StudentRegistration> getStudentRegistrations() {
        return studentRegistrations;
    }
    
    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Semester [id=" + id + ", semesterName=" + semesterName + ", startDate=" + startDate + ", endDate="
                + endDate + ", studentRegistrations=" + studentRegistrations + "]";
    }
    
}
