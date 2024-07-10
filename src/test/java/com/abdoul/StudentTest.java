package com.abdoul;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.abdoul.dao.StudentDAO;
import com.abdoul.models.Student;

public class StudentTest extends BaseTest{

    @Test
    public void testSaveStudent() {
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.saveStudent("Mugisha", "Abdoullatif", Date.valueOf("2002-05-01"), 'M');

        assertNotNull(student);;
    }

    @Test
    public void testGetByFirstNameAndLastName() {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getByFirstNameAndLastName("Mugisha", "Abdoullatif");
        assertEquals("Mugisha", students.getFirst().getFirstName());
        assertEquals("Abdoullatif", students.getFirst().getLastName());
    }
}
