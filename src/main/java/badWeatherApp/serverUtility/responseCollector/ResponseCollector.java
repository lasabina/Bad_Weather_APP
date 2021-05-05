package badWeatherApp.serverUtility.responseCollector;

import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.serverCommunication.Connectable;

import java.time.LocalDateTime;
import java.util.List;

public abstract class ResponseCollector {

    protected final List<Connectable> requestServers;
    protected final ForecastType forecastType;
    protected final LocationDTO location;
    protected LocalDateTime requestTime;

    public ResponseCollector(List<Connectable> requestServers, ForecastType forecastType, LocationDTO location) {
        this.requestServers = requestServers;
        this.forecastType = forecastType;
        this.location = location;
    }

    public LocalDateTime getObservationTime() {
        return requestTime;
    }

    public ForecastType getForecastType() {
        return forecastType;
    }

    //LOCATION GETTERS
    public int getLocationId() {
        return location.getIdLocation();
    }

    public String getCity() {
        return location.getCity();
    }

    public String getCountry() {
        return location.getCountry();
    }

    public String getRegion() {
        return location.getRegion();
    }

    public double getLatitude() {
        return location.getLat();
    }

    public double getLongitude() {
        return location.getLon();
    }

}
