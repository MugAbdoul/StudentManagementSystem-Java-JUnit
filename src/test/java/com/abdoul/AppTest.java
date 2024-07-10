package com.abdoul;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

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

    @AfterAll
    public static void tearDown() {
        // Clean up resources if needed
    }

    private static void dropTable(Session session, String tableName) {
        String sql = String.format("DROP TABLE IF EXISTS %s CASCADE", tableName);
        NativeQuery<?> query = session.createNativeQuery(sql);
        query.executeUpdate();
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
        Student student = studentDAO.getByFirstNameAndLastName("Mugisha", "Abdoullatif").get(0);
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
    public void testSaveDepartment() {
        Department department = departmentDAO.saveDepartment("Software Engineering", "CS");
        assertNotNull(department);
    }

    @Test
    @Order(5)
    public void testSaveCourse() {
        Semester semester = semesterDAO.getSemesterByName("Spring 2024");
        Course course = courseDAO.saveCourse("Software Engineering", "CS", semester);
        assertNotNull(course);
    }

    @Test
    @Order(6)
    public void testRegisterStudent() {
        Student student = studentDAO.getByFirstNameAndLastName("Mugisha", "Abdoullatif").get(0);
        Semester semester = semesterDAO.getSemesterByName("Spring 2024");
        Department department = departmentDAO.getDepartmentByCode("CS");

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        studentRegistrationDAO.registerStudent(24978, student, semester, department);

        StudentRegistration registration = studentRegistrationDAO.getByRegistrationNumber(24978);
        assertNotNull(semester);
        assertNotNull(department);
    }

    @Test
    @Order(7)
    public void testSaveStudentScore() {
        Course course = courseDAO.getCourseByCode("CS");

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        StudentRegistration registration = studentRegistrationDAO.getByRegistrationNumber(24978);
        System.out.println(registration.getRegistrationNumber());

        StudentCourses studentCourses = studentCoursesDAO.saveStudentCourse(course, 20, registration);
        assertNotNull(studentCourses);
    }

    @Test
    @Order(8)
    public void testGetTotalMarks() {
        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        StudentRegistration studentRegistration = studentRegistrationDAO.getByRegistrationNumber(24978);
        System.out.println(studentRegistration.getRegistrationNumber());
        // Calculate total marks
        int totalMarks = studentCoursesDAO.getTotalMarks(studentRegistration);
        assertEquals(100, totalMarks); // Assuming 20 is the only marks saved
    }

    // @Test
    // @Order(9)
    // public void testNormalizeMarksTo20() {
    //     double normalizedMarks = studentCoursesDAO.normalizeMarksTo20(100);
    //     assertEquals(20.0, normalizedMarks);
    // }

    // @Test
    // @Order(10)
    // public void testGradeStudent() {
    //     String grade = studentCoursesDAO.gradeStudent(18.0);
    //     assertEquals("High Distinction", grade);
    // }
}
