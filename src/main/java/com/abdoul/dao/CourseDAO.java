package com.abdoul.dao;

import com.abdoul.models.Course;
import com.abdoul.models.Semester;
import com.abdoul.models.StudentCourses;
import com.abdoul.models.StudentRegistration;
import com.abdoul.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAO {

    public void saveCourse(String courseName, String courseCode, Semester semester) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Course course = new Course(courseName, courseCode, semester);

            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Course", Course.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Course getCourseByCode(String courseCode) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Course> query = session.createQuery("from Course where courseCode = :code", Course.class);
            query.setParameter("code", courseCode);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
