package com.abdoul;

import java.sql.Date;
import java.util.List;

import com.abdoul.dao.CourseDAO;
import com.abdoul.dao.DepartmentDAO;
import com.abdoul.dao.SemesterDAO;
import com.abdoul.dao.StudentDAO;
import com.abdoul.dao.StudentRegistrationDAO;
import com.abdoul.models.Course;
import com.abdoul.models.Department;
import com.abdoul.models.Semester;
import com.abdoul.models.Student;
import com.abdoul.models.StudentRegistration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        // StudentDAO studentDAO = new StudentDAO();
        // Student student = studentDAO.saveStudent("Mugisha", "Abdoullatif", Date.valueOf("2002-05-01"), 'M');

        // SemesterDAO semesterDAO = new SemesterDAO();
        // Semester Semester = semesterDAO.saveSemester("Spring 2024", Date.valueOf("2024-01-01"), Date.valueOf("2024-05-31"));

        // DepartmentDAO departmentDAO = new DepartmentDAO();
        // Department department = departmentDAO.saveDepartment("Software Engineering", "CS");

        // Semester semester = semesterDAO.getSemesterByName("Spring 2024");
        
        // CourseDAO courseDAO = new CourseDAO();
        // Course course = courseDAO.saveCourse("Software Engineering", "CS", semester);

        // Student studentR = studentDAO.getByFirstNameAndLastName("Mugisha", "Abdoullatif").get(0);


        // Department departmentR = departmentDAO.getDepartmentByCode("CS");

        // StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        // studentRegistrationDAO.registerStudent(24978, studentR, semester, departmentR);

        // StudentRegistration registration = studentRegistrationDAO.getByRegistrationNumber(24978);
        // System.out.println(registration.getRegistrationNumber());

        // StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        // StudentRegistration studentRegistration = studentRegistrationDAO.getByRegistrationNumber(24978);
        // System.out.println(studentRegistration.getRegistrationNumber());
    }
}