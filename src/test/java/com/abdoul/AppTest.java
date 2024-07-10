package com.abdoul;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.abdoul.dao.CourseDAO;
import com.abdoul.dao.DepartmentDAO;
import com.abdoul.dao.SemesterDAO;
import com.abdoul.dao.StudentCoursesDAO;
import com.abdoul.dao.StudentDAO;
import com.abdoul.dao.StudentRegistrationDAO;
import com.abdoul.models.Course;
import com.abdoul.models.Department;
import com.abdoul.models.Semester;
import com.abdoul.models.Student;
import com.abdoul.models.StudentCourses;
import com.abdoul.models.StudentRegistration;

@TestMethodOrder(OrderAnnotation.class)
public class AppTest {

    private static StudentDAO studentDAO;
    private static SemesterDAO semesterDAO;
    private static DepartmentDAO departmentDAO;
    private static CourseDAO courseDAO;
    private static StudentCoursesDAO studentCoursesDAO;

    @BeforeAll
    public static void setUp() {
        studentDAO = new StudentDAO();
        semesterDAO = new SemesterDAO();
        departmentDAO = new DepartmentDAO();
        courseDAO = new CourseDAO();
        studentCoursesDAO = new StudentCoursesDAO();
    }

    @Test
    @Order(1)
    public void testSaveStudent() {

        Student student = studentDAO.saveStudent("Mugisha", "Abdoullatif", Date.valueOf("2002-05-01"), 'M');
        assertNotNull(student);

    }

    @Test
    @Order(2)
    public void testGetByFirstNameAndLastName() {

        Student student = studentDAO.getByFirstNameAndLastName("Mugisha", "Abdoullatif");
        assertNotNull(student);
        assertEquals("Mugisha", student.getFirstName());
        assertEquals("Abdoullatif", student.getLastName());

    }

    @Test
    @Order(3)
    public void testSaveSemester() {

        Semester semester = semesterDAO.saveSemester("Spring 2024", Date.valueOf("2024-01-01"), Date.valueOf("2024-05-31"));
        assertNotNull(semester);

    }

    

    @Test
    @Order(4)
    public void testSaveCourse() {

        Semester semester = semesterDAO.getSemesterByName("Spring 2024");
    
        Course course1 = courseDAO.saveCourse("Software Engineering", "CS101", semester);
        Course course2 = courseDAO.saveCourse("Database Systems", "CS102", semester);
        Course course3 = courseDAO.saveCourse("Algorithms", "CS103", semester);
        Course course4 = courseDAO.saveCourse("Operating Systems", "CS104", semester);
        Course course5 = courseDAO.saveCourse("Computer Networks", "CS105", semester);
    
        assertNotNull(course1);
        assertNotNull(course2);
        assertNotNull(course3);
        assertNotNull(course4);
        assertNotNull(course5);

    }

    @Test
    @Order(5)
    public void testSaveDepartment() {

        Department department = departmentDAO.saveDepartment("Software Engineering", "CS");
        assertNotNull(department);

    }
    
    @Test
    @Order(6)
    public void testRegisterStudent() {

        Student student = studentDAO.getByFirstNameAndLastName("Mugisha", "Abdoullatif");
        assertNotNull(student);

        Semester semester = semesterDAO.getSemesterByName("Spring 2024");
        assertNotNull(semester);

        Department department = departmentDAO.getDepartmentByCode("CS");
        assertNotNull(department);

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        studentRegistrationDAO.registerStudent(24978, student, semester, department);

        StudentRegistration registration = studentRegistrationDAO.getByRegistrationNumber(24978);
        assertNotNull(registration);

    }

    @Test
    @Order(7)
    public void testSaveStudentScore() {

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        StudentRegistration registration = studentRegistrationDAO.getByRegistrationNumber(24978);

        assertNotNull(registration);

        Course course1 = courseDAO.getCourseByCode("CS101");
        assertNotNull(course1);

        StudentCourses studentCourse1 = studentCoursesDAO.saveStudentCourse(course1, 20, registration);
        assertNotNull(studentCourse1);

        Course course2 = courseDAO.getCourseByCode("CS102");
        assertNotNull(course2);

        StudentCourses studentCourse2 = studentCoursesDAO.saveStudentCourse(course2, 18, registration);
        assertNotNull(studentCourse2);

        Course course3 = courseDAO.getCourseByCode("CS103");
        assertNotNull(course3);

        StudentCourses studentCourse3 = studentCoursesDAO.saveStudentCourse(course3, 15, registration);
        assertNotNull(studentCourse3);

        Course course4 = courseDAO.getCourseByCode("CS104");
        assertNotNull(course4);

        StudentCourses studentCourse4 = studentCoursesDAO.saveStudentCourse(course4, 12, registration);
        assertNotNull(studentCourse4);

        Course course5 = courseDAO.getCourseByCode("CS105");
        assertNotNull(course5);

        StudentCourses studentCourse5 = studentCoursesDAO.saveStudentCourse(course5, 17, registration);
        assertNotNull(studentCourse5);

    }


    @Test
    @Order(8)
    public void testGetTotalMarks() {

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        StudentRegistration studentRegistration = studentRegistrationDAO.getByRegistrationNumber(24978);
        
        int totalMarks = studentCoursesDAO.getTotalMarks(studentRegistration);
        assertEquals(82, totalMarks);
    }

    @Test
    @Order(9)
    public void testNormalizeMarksTo20() {

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        StudentRegistration studentRegistration = studentRegistrationDAO.getByRegistrationNumber(24978);
        
        int totalMarks = studentCoursesDAO.getTotalMarks(studentRegistration);

        double normalizedMarks = studentCoursesDAO.normalizeMarksTo20(totalMarks);
        assertEquals(16.4, normalizedMarks);
    }

    @Test
    @Order(10)
    public void testGradeStudent() {

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        StudentRegistration studentRegistration = studentRegistrationDAO.getByRegistrationNumber(24978);
        
        int totalMarks = studentCoursesDAO.getTotalMarks(studentRegistration);

        double normalizedMarks = studentCoursesDAO.normalizeMarksTo20(totalMarks);

        String grade = studentCoursesDAO.gradeStudent(normalizedMarks);
        assertEquals("High Distinction", grade);
        
    }
}
