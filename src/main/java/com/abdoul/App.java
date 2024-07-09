package com.abdoul;

import java.util.List;

import com.abdoul.dao.StudentDAO;
import com.abdoul.models.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        StudentDAO studentDAO = new StudentDAO();

        List<Student> students = studentDAO.getByFirstNameAndLastName("Muhire", "karim");
        System.out.println(students.getFirst().getFirstName());
    }
}