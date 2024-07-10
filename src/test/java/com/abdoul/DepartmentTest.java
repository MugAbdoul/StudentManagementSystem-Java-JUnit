package com.abdoul;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.abdoul.dao.DepartmentDAO;
import com.abdoul.models.Department;

public class DepartmentTest extends BaseTest{

    @Test
    public void testSaveDepartment(){

        DepartmentDAO departmentDAO = new DepartmentDAO();
        Department department = departmentDAO.saveDepartment("Software Engineering", "SENG 123");

        assertNotNull(department);

    }
}
