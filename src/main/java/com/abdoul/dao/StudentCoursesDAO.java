package com.abdoul.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.abdoul.models.Course;
import com.abdoul.models.StudentCourses;
import com.abdoul.models.StudentRegistration;
import com.abdoul.utils.HibernateUtil;

public class StudentCoursesDAO {
    public void saveStudentCourse(Course course, int marksIncourse, StudentRegistration studentRegistration) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            StudentCourses studentCourse = new StudentCourses(course, marksIncourse, studentRegistration);
            session.save(studentCourse);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public StudentCourses getStudentCourseById(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(StudentCourses.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<StudentCourses> getAllStudentCourses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from StudentCourses", StudentCourses.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateStudentCourse(StudentCourses studentCourse) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(studentCourse);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStudentCourse(UUID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            StudentCourses studentCourse = session.get(StudentCourses.class, id);
            if (studentCourse != null) {
                session.delete(studentCourse);
                System.out.println("Student course is deleted");
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
