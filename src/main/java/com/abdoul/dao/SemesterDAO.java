package com.abdoul.dao;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.abdoul.models.Semester;
import com.abdoul.utils.HibernateUtil;

public class SemesterDAO {
    
    public Semester saveSemester(String semesterName, Date starDate, Date endDate) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Semester semester = new Semester(semesterName, starDate, endDate);
            session.save(semester);
            transaction.commit();

            return semester;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public Semester getSemesterByName(String semesterName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Semester> query = session.createQuery("from Semester where semesterName = :name", Semester.class);
            query.setParameter("name", semesterName);
            return query.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Semester> getAllSemesters() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Semester", Semester.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
