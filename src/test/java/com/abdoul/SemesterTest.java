package com.abdoul;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.checkerframework.checker.units.qual.s;
import org.junit.Test;

import com.abdoul.dao.SemesterDAO;
import com.abdoul.models.Semester;

public class SemesterTest extends BaseTest{

    @Test
    public void testSaveSemester(){

        SemesterDAO semesterDAO = new SemesterDAO();
        Semester Semester = semesterDAO.saveSemester("Spring 2024", Date.valueOf("2024-01-01"), Date.valueOf("2024-05-31"));

        assertNotNull(Semester);

    }
    
}
