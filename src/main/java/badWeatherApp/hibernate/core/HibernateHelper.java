package badWeatherApp.hibernate.core;

import badWeatherApp.databaseUtility.location.entity.Location;
import badWeatherApp.databaseUtility.forecast.entity.Forecast;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum HibernateHelper {

    INSTANCE;
    private SessionFactory sessionFactory;

    HibernateHelper() {
        sessionFactory = new Configuration()
                .configure("test/hibernate_test.cfg.xml")
                .addAnnotatedClass(Location.class)
                .addAnnotatedClass(Forecast.class)
                .buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
