package badWeatherApp.serverUtility.responseCollector;

import badWeatherApp.serverUtility.json.JsonStringDeserializer;
import badWeatherApp.serverUtility.response.WeatherReadable;
import badWeatherApp.serverUtility.serverCommunication.Requestable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class ResponseCollector {

    private Set<Requestable> requestServers;
    private Set<WeatherReadable> forecastSet;

    public ResponseCollector(Set<Requestable> requestServers) {
        this.requestServers = requestServers;
        forecastSet = null;
    }

    public List<Double> getCurrentMeasurements(Measurement measurement, String city) {
        if (forecastSet == null) {
            forecastSet = getForecasts(city);
        }
        List<Double> returnValues = new ArrayList<>();
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

    private List<Double> getMeasurement(Function<WeatherReadable,Double> function) {
        List<Double> returnValues = new ArrayList<>();
        forecastSet.forEach(f -> returnValues.add(function.apply(f)));
        return returnValues;
    }

    private Set<WeatherReadable> getForecasts(String city) {
        if (forecastSet == null) {
            forecastSet = getForecastCollection(city);
        }
        return forecastSet;
    }

    private Set<WeatherReadable> getForecastCollection(String city) {
        Set<WeatherReadable> readableList = new HashSet<>();
        requestServers.forEach(s -> {
            try {
                readableList.add(getSingleForecast(s, city));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return readableList;
    }

    private WeatherReadable getSingleForecast(Requestable server, String city) throws IOException {
        return JsonStringDeserializer.deserialize(server.getCurrentForecastForCity(city), server.getResponseClass());
    }

}
