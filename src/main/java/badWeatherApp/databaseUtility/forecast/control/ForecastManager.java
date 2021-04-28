package badWeatherApp.databaseUtility.forecast.control;

import badWeatherApp.databaseUtility.forecast.entity.Forecast;
import badWeatherApp.databaseUtility.forecast.entity.ForecastDTO;
import badWeatherApp.hibernate.core.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ForecastManager {

    public static List<Forecast> getForecastByCity(String city) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            List<Forecast> forecasts = session.createNamedQuery(Forecast.FIND_BY_CITY, Forecast.class)
                    .setParameter("city", city).getResultList();
            return new ArrayList<>(forecasts);
//            return forecasts
//                    .stream().collect(Collectors.toList());
        }
    }

    public static List<Forecast> getForecastByCountry(String country) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            List<Forecast> forecasts = session.createNamedQuery(Forecast.FIND_BY_COUNTRY, Forecast.class)
                    .setParameter("country", country).getResultList();
            return new ArrayList<>(forecasts);
        }
    }

    public static Integer addForecast(ForecastDTO forecastDTO) {
        Forecast forecast = new Forecast(forecastDTO.getObservationDate(), forecastDTO.getForecastDate(),forecastDTO.getTemperature(),forecastDTO.getFeelsLike(),forecastDTO.getPreassure(),forecastDTO.getHumidity());
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(forecast);
            transaction.commit();
        }
        return forecast.getIdForecast();
    }

    public static boolean removeForecastbyID(Integer id) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Forecast forecast = session.find(Forecast.class, id);
            if(forecast == null){
                return false;
            }
            Transaction transaction = session.beginTransaction();
            session.remove(forecast);
            transaction.commit();
            return true;
        }
    }

    public static boolean removeForecastbyCity(String city) {
        //trzeba przes Selecta - aktualnie szuka po kluczu, którym jest ID
        //todo
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Forecast forecast = session.find(Forecast.class, city);
            if(forecast == null){
                return false;
            }
            Transaction transaction = session.beginTransaction();
            session.remove(forecast);
            transaction.commit();
            return true;
        }
    }
}