package com.abdoul.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.abdoul.models.Student;
import com.abdoul.utils.HibernateUtil;

public class StudentDAO {
    public void saveStudent(String firstName, String lastName, Date dateOfBirth, char gender) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGender(gender);
        student.setDateOfBirth(dateOfBirth);

        session.save(student);
        transaction.commit();
        session.close();
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
}