package badWeatherApp.serverUtility.responseCollector;

import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.json.JsonStringDeserializer;
import badWeatherApp.serverUtility.response.WeatherReadable;
import badWeatherApp.serverUtility.serverCommunication.Requestable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ResponseCollector {

    private List<Requestable> requestServers;
    private List<WeatherReadable> forecastList;

    private LocationDTO location;
    private LocalDateTime requestTime;

    public ResponseCollector(List<Requestable> requestServers, LocationDTO location) {
        this.requestServers = requestServers;
        this.forecastList = null;
        this.location = location;
    }

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


    public LocalDateTime getObservationTime() {
        return requestTime;
    }

    public List<Double> getCurrentMeasurements(Measurement measurement) {
        if (forecastList == null) {
            requestTime = LocalDateTime.now();
            forecastList = getForecasts();
        }
        switch (measurement) {
            case TEMPERATURE:
                return getMeasurement(WeatherReadable::getTemperature);
            case FEELS_LIKE:
                return getMeasurement(WeatherReadable::getFeelsLikeTemperature);
            case WIND_SPEED:
                return getMeasurement(WeatherReadable::getWindSpeed);
            case WIND_DIRECTION:
                return getMeasurement(WeatherReadable::getWindDegree);
            case PRESSURE:
                return getMeasurement(WeatherReadable::getPressure);
            case HUMIDITY:
                return getMeasurement(WeatherReadable::getHumidity);
            default:
                return null;
        }
    }

    private List<Double> getMeasurement(Function<WeatherReadable, Double> function) {
        List<Double> returnValues = new ArrayList<>();
        forecastList.forEach(f -> returnValues.add(function.apply(f)));
        return returnValues;
    }

    private List<WeatherReadable> getForecasts() {
        if (forecastList == null) {
            forecastList = getForecastCollection();
        }
        return forecastList;
    }

    private List<WeatherReadable> getForecastCollection() {
        List<WeatherReadable> readableList = new ArrayList<>();
        requestServers.forEach(s -> {
            try {
                readableList.add(getSingleForecast(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return readableList;
    }

    private WeatherReadable getSingleForecast(Requestable server) throws IOException {
        return JsonStringDeserializer.deserialize(server.getCurrentForecastForCity(location.getCity()), server.getResponseClass());
    }

}
