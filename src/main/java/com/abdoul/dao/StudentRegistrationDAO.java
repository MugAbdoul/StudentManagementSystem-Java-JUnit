package com.abdoul.dao;

import com.abdoul.models.Department;
import com.abdoul.models.Semester;
import com.abdoul.models.Student;
import com.abdoul.models.StudentRegistration;
import com.abdoul.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.UUID;
import java.sql.Date;
import java.util.List;

public class StudentRegistrationDAO {

    public void saveStudentRegistration(int registrationNumber, Date registrationDate, Student student, Semester semester,
            Department department) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

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
            return query.uniqueResult();
        }
    }

    public StudentRegistration getStudentRegistrationById(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(StudentRegistration.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public void updateStudentRegistration(StudentRegistration studentRegistration) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(studentRegistration);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStudentRegistration(UUID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            StudentRegistration studentRegistration = session.get(StudentRegistration.class, id);
            if (studentRegistration != null) {
                session.delete(studentRegistration);
                System.out.println("Student registration is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
