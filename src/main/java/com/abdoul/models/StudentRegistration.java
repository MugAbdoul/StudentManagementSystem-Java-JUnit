package com.abdoul.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "student_registration")
public class StudentRegistration {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "registration_number")
    private int registrationNumber;

    @Column(name = "registration_date")
    private Date registrationDate;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

    @JoinColumn(name = "semester_id")
    @ManyToOne
    private Semester semester;

    @JoinColumn(name = "department_id")
    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "studentRegistration")
    private List<StudentCourses> StudentCourses = new ArrayList<>();

    public StudentRegistration() {
    }
  
    public StudentRegistration(int registrationNumber, Date registrationDate, Student student, Semester semester,
            Department department) {
        this.registrationNumber = registrationNumber;
        this.registrationDate = registrationDate;
        this.student = student;
        this.semester = semester;
        this.department = department;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<StudentCourses> getStudentCourses() {
        return StudentCourses;
    }

    @Override
    public String toString() {
        return "StudentRegistration [id=" + id + ", registrationNumber=" + registrationNumber + ", registrationDate="
                + registrationDate + ", student=" + student + ", semester=" + semester + ", department=" + department
                + "]";
    }
    
}
