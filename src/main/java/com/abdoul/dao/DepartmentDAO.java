package com.abdoul.dao;

import com.abdoul.models.Department;
import com.abdoul.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class DepartmentDAO {

    public Department saveDepartment(String departmentName, String departmentCode) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Department department = new Department(departmentName, departmentCode);
            session.save(department);
            transaction.commit();

            return department;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return null;
    }

    public Department getDepartmentByCode(String departmentCode) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Department> query = session.createQuery("from Department where departmentCode = :code", Department.class);
            query.setParameter("code", departmentCode);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Department> getAllDepartments() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Department", Department.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
