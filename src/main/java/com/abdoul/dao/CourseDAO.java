package com.abdoul.dao;

import com.abdoul.models.Course;
import com.abdoul.models.Semester;
import com.abdoul.models.StudentCourses;
import com.abdoul.models.StudentRegistration;
import com.abdoul.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

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

    public Course getCourseById(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Course.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTotalMarksInSemester(int registrationNumber) {
        StudentRegistration studentRegistration = StudentRegistrationDAO.getByRegistrationNumber(registrationNumber);
        if (studentRegistration == null) {
            throw new IllegalArgumentException("Invalid registration number: " + registrationNumber);
        }
        
        int totalMarks = 0;
        List<StudentCourses> courses = studentRegistration.getStudentCourses();
        for (StudentCourses course : courses) {
            totalMarks += course.getMarksIncourse();
        }
        
        // Assuming each course is graded out of 20 marks and a student has taken 5 courses
        return (totalMarks * 100) / (5 * 20);
    }

    public List<Course> getAllCourses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Course", Course.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateCourse(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCourse(UUID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            if (course != null) {
                session.delete(course);
                System.out.println("Course is deleted");
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
