package com.abdoul.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public int getTotalMarks(StudentRegistration studentRegistration) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(marksIncourse) FROM StudentCourses WHERE studentRegistration = :studentRegistration";
            Query<Integer> query = session.createQuery(hql, Integer.class);
            query.setParameter("studentRegistration", studentRegistration);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double normalizeMarksTo20(int totalMarks) {
        return (totalMarks / 100.0) * 20.0;
    }

    public String gradeStudent(double normalizedMarks) {
        if (normalizedMarks >= 16) {
            return "High Distinction";
        } else if (normalizedMarks >= 12 && normalizedMarks < 16) {
            return "Lower Distinction";
        } else if (normalizedMarks >= 10 && normalizedMarks < 12) {
            return "Pass";
        } else {
            return "Expel";
        }
    }
}
