package databaseTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DatabaseTest {

    private static final SessionFactory sessionFactory = new Configuration()
            .configure("test/hibernate_test.cfg.xml")
            .addAnnotatedClass(TestUser.class)
            .buildSessionFactory();

    @BeforeEach
    void initialize() {
        try (Session session = sessionFactory.openSession()) {
            Set<TestUser> testUsers = new HashSet<>(Set.of(
                    new TestUser("Adam123", "mhk284a7sf2"),
                    new TestUser("Basia678", "876aisaB"),
                    new TestUser("Celina111", "kotnaplot"),
                    new TestUser("Dagmara", "ooooopppppppp11111")
            ));

            Transaction transaction = session.beginTransaction();
            for (TestUser t :
                    testUsers) {
                session.persist(t);
            }

            transaction.commit();
        }
    }

    @Test
    void shouldPrintAllFourUsers() {
        printAllUsers();
    }

    @Test
    void shouldAddNewUser() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            TestUser t1 = new TestUser("NewUser", "pezwrd");
            session.persist(t1);
            transaction.commit();
        }
        printAllUsers();
    }

    private void printAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<TestUser> query = session.createQuery("SELECT t FROM TestUser t", TestUser.class);
            List<TestUser> testUsers = query.getResultList();
            for (TestUser testUser : testUsers) {
                System.out.println(testUser);
            }
        }
    }

}
