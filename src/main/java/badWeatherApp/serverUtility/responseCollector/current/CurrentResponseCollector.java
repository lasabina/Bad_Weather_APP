package badWeatherApp.serverUtility.responseCollector.current;

import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.json.JsonStringDeserializer;
import badWeatherApp.serverUtility.response.current.CurrentWeatherReadable;
import badWeatherApp.serverUtility.responseCollector.ForecastType;
import badWeatherApp.serverUtility.responseCollector.ResponseCollector;
import badWeatherApp.serverUtility.responseCollector.current.CurrentMeasurement;
import badWeatherApp.serverUtility.serverCommunication.Connectable;
import badWeatherApp.serverUtility.serverCommunication.CurrentWeatherRequestable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CurrentResponseCollector extends ResponseCollector {

    private List<CurrentWeatherReadable> currentWeatherList;

    public CurrentResponseCollector(List<Connectable> requestServers, LocationDTO location, ForecastType forecastType) {
        super(requestServers,forecastType,location);
    }

    public List<Double> getCurrentMeasurementFunction(CurrentMeasurement currentMeasurement) {
        if (currentWeatherList == null) {
            requestTime = LocalDateTime.now();
            currentWeatherList = getForecasts();
        }
        switch (currentMeasurement) {
            case TEMPERATURE:
                return getCurrentMeasurementFunction(CurrentWeatherReadable::getTemperature);
            case FEELS_LIKE:
                return getCurrentMeasurementFunction(CurrentWeatherReadable::getFeelsLikeTemperature);
            case WIND_SPEED:
                return getCurrentMeasurementFunction(CurrentWeatherReadable::getWindSpeed);
            case WIND_DIRECTION:
                return getCurrentMeasurementFunction(CurrentWeatherReadable::getWindDegree);
            case PRESSURE:
                return getCurrentMeasurementFunction(CurrentWeatherReadable::getPressure);
            case HUMIDITY:
                return getCurrentMeasurementFunction(CurrentWeatherReadable::getHumidity);
            default:
                return null;
        }
    }

    private List<Double> getCurrentMeasurementFunction(Function<CurrentWeatherReadable, Double> function) {
        List<Double> returnValues = new ArrayList<>();
        currentWeatherList.forEach(f -> returnValues.add(function.apply(f)));
        return returnValues;
    }

    private List<CurrentWeatherReadable> getForecasts() {
        if (currentWeatherList == null) {
            currentWeatherList = getForecastCollection();
        }
        return currentWeatherList;
    }

    private List<CurrentWeatherReadable> getForecastCollection() {
        List<CurrentWeatherReadable> readableList = new ArrayList<>();
                requestServers.forEach(s -> {
                    if (s instanceof CurrentWeatherRequestable) {
                        try {
                            readableList.add(getCurrentWeather((CurrentWeatherRequestable) s));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return readableList;
    }

    private CurrentWeatherReadable getCurrentWeather(CurrentWeatherRequestable server) throws IOException {
        switch (location.getInputType()) {
            case BY_CITY:
                return JsonStringDeserializer.deserialize(server.getCurrentWeatherByCity(location.getCity()), server.getCurrentWeatherResponseClass());
            case BY_COORDINATES:
                return JsonStringDeserializer.deserialize(server.getCurrentWeatherByCoordinates(getLatitude(), getLongitude()), server.getCurrentWeatherResponseClass());
            default:
                return null;
        }
    }
}
