package badWeatherApp.serverUtility.responseCollector;

import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.json.JsonStringDeserializer;
import badWeatherApp.serverUtility.response.WeatherReadable;
import badWeatherApp.serverUtility.serverCommunication.Connectable;
import badWeatherApp.serverUtility.serverCommunication.CurrentWeatherRequestable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ResponseCollector {

    private final List<Connectable> requestServers;
    private final ForecastType forecastType;
    private final LocationDTO location;
    private List<WeatherReadable> forecastList;
    private LocalDateTime requestTime;

    public ResponseCollector(List<Connectable> requestServers, LocationDTO location, ForecastType forecastType) {
        this.requestServers = requestServers;
        this.forecastList = null;
        this.location = location;
        this.forecastType = forecastType;
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


    public List<Double> getMeasurement(Measurement measurement) {
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
            if (s instanceof CurrentWeatherRequestable)
                try {
                    switch (forecastType) {
                        case CURRENT:
                            readableList.add(getSingleForecastByCity((CurrentWeatherRequestable) s));
                            break;
                        case FORECAST:
                            readableList.add(getSingleForecastByCoordinates((CurrentWeatherRequestable) s));
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
        return readableList;
    }

    private WeatherReadable getSingleForecastByCity(CurrentWeatherRequestable server) throws IOException {
        return JsonStringDeserializer.deserialize(server.getCurrentWeatherByCity(location.getCity()), server.getCurrentWeatherResponseClass());
    }

    private WeatherReadable getSingleForecastByCoordinates(CurrentWeatherRequestable server) throws IOException {
        return JsonStringDeserializer.deserialize(server.getCurrentWeatherByCoordinates(location.getLat(), location.getLon()), server.getCurrentWeatherResponseClass());
    }

}
