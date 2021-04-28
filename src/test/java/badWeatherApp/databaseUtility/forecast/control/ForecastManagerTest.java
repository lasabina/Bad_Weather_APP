package badWeatherApp.databaseUtility.forecast.control;

import badWeatherApp.databaseUtility.forecast.entity.Forecast;
import badWeatherApp.databaseUtility.location.entity.Location;
import databaseTest.TestUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForecastManagerTest {

    private static final SessionFactory sessionFactory = new Configuration()
            .configure("test/hibernate.cfg.xml")
            .addAnnotatedClass(Forecast.class)
            .addAnnotatedClass(Location.class)
            .buildSessionFactory();

    @Test
    public void shouldAddForecast() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Forecast forecast = new Forecast();
            session.persist(forecast);
            transaction.commit();
        }
        printAllForecasts();
    }

    private void printAllForecasts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Forecast> query = session.createQuery("SELECT f FROM Forecast f", Forecast.class);
            List<Forecast> forecasts = query.getResultList();
            for (Forecast forecast : forecasts) {
                System.out.println(forecast);
            }
        }
    }


}