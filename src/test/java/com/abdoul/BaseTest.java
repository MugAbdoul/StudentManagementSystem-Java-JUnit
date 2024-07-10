package com.abdoul;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class BaseTest {

    protected static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUp() {
        // Set up Hibernate session factory
        sessionFactory = new Configuration().configure().buildSessionFactory();

        // Create tables
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Create tables using Hibernate's hbm2ddl.auto property
            createTable(session, "Department");
            createTable(session, "Course");
            createTable(session, "Semester");
            createTable(session, "Student");
            createTable(session, "StudentRegistration");
            createTable(session, "StudentCourses");

            transaction.commit();
        }
    }

    private static void createTable(Session session, String tableName) {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY)", tableName);
        NativeQuery<?> query = session.createNativeQuery(sql);
        query.executeUpdate();
    }

    @AfterClass
    public static void tearDown() {
        // Drop tables
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            dropTable(session, "StudentCourses");
            dropTable(session, "StudentRegistration");
            dropTable(session, "Student");
            dropTable(session, "Semester");
            dropTable(session, "Course");
            dropTable(session, "Department");

            transaction.commit();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    private static void dropTable(Session session, String tableName) {
        String sql = String.format("DROP TABLE IF EXISTS %s CASCADE", tableName);
        NativeQuery<?> query = session.createNativeQuery(sql);
        query.executeUpdate();
    }
}
