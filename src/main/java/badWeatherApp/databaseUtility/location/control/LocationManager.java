package badWeatherApp.databaseUtility.location.control;


import badWeatherApp.databaseUtility.forecast.entity.Forecast;
import badWeatherApp.databaseUtility.forecast.entity.ForecastDTO;
import badWeatherApp.databaseUtility.location.entity.Location;
import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.hibernate.core.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LocationManager {


    public static List<Location> getLocationByCity(String city) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            List<Location> locations = session.createNamedQuery(Location.FIND_LOCATION_BY_CITY, Location.class)
                    .setParameter("city", city).getResultList();
            return new ArrayList<>(locations);
        }
    }

    public static List<Location> getLocationByCountry(String country) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            List<Location> locations = session.createNamedQuery(Location.FIND_LOCATION_BY_COUNTRY, Location.class)
                    .setParameter("country", country).getResultList();
            return new ArrayList<>(locations);
        }
    }

    public static Integer addLocation(LocationDTO locationDTO) {
        Location location = new Location(locationDTO.getCity(),locationDTO.getCountry());
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(location);
            transaction.commit();
        }
        return location.getIdLocation();
    }

    public static boolean removeLocation(Integer id) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Location location = session.find(Location.class, id);
            if(location == null){
                return false;
            }
            Transaction transaction = session.beginTransaction();
            session.remove(location);
            transaction.commit();
            return true;
        }
    }


}
