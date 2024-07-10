package com.abdoul;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.AfterAll;
// import org.junit.AfterClass;
// import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected static SessionFactory sessionFactory;

    @BeforeAll
    public static void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

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

    @AfterAll
    public static void tearDown() {
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
