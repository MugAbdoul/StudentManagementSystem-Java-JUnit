package com.abdoul.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.abdoul.models.Student;
import com.abdoul.utils.HibernateUtil;

public class StudentDAO {
    public Student saveStudent(String firstName, String lastName, Date dateOfBirth, char gender) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGender(gender);
        student.setDateOfBirth(dateOfBirth);

        try {
            session.save(student);
            transaction.commit();
            return student;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> getByFirstNameAndLastName(String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Student> query = session.createQuery("FROM Student WHERE firstName = :firstName AND lastName = :lastName", Student.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        List<Student> students = query.getResultList();
        session.close();
        return students;
    }

    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("FROM Student", Student.class).list();
        session.close();
        return students;
    }
}
