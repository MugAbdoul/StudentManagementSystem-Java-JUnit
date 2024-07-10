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
    public StudentCourses saveStudentCourse(Course course, int marksIncourse, StudentRegistration studentRegistration) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            StudentCourses studentCourse = new StudentCourses(course, marksIncourse, studentRegistration);
            session.save(studentCourse);
            transaction.commit();

            return studentCourse;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public StudentCourses getStudentCourseById(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(StudentCourses.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTotalMarks(StudentRegistration studentRegistration) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(sc.marksIncourse) FROM StudentCourses sc WHERE sc.studentRegistration = :studentRegistration";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("studentRegistration", studentRegistration);
            Long totalMarks = query.uniqueResult();
            return totalMarks != null ? totalMarks.intValue() : 0;
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
