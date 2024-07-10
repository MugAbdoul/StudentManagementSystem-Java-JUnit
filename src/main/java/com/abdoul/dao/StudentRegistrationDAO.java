package com.abdoul.dao;

import com.abdoul.models.Department;
import com.abdoul.models.Semester;
import com.abdoul.models.Student;
import com.abdoul.models.StudentRegistration;
import com.abdoul.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class StudentRegistrationDAO {

    public void registerStudent(int registrationNumber, Student student, Semester semester, Department department) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Date registrationDate = Date.valueOf(LocalDate.now());

            StudentRegistration studentRegistration = new StudentRegistration(registrationNumber, registrationDate, student, semester, department);
            session.save(studentRegistration);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static StudentRegistration getByRegistrationNumber(int registrationNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<StudentRegistration> query = session.createQuery(
                "FROM StudentRegistration WHERE registrationNumber = :registrationNumber", 
                StudentRegistration.class
            );
            query.setParameter("registrationNumber", registrationNumber);
            return query.getResultList().get(0);
        }
    }

    public List<StudentRegistration> getAllStudentRegistrations() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from StudentRegistration", StudentRegistration.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
