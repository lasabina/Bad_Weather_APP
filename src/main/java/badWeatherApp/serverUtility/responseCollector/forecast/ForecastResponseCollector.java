package badWeatherApp.serverUtility.responseCollector.forecast;

import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.json.JsonStringDeserializer;
import badWeatherApp.serverUtility.response.forecast.ForecastWeatherReadable;
import badWeatherApp.serverUtility.responseCollector.ForecastType;
import badWeatherApp.serverUtility.responseCollector.ResponseCollector;
import badWeatherApp.serverUtility.responseCollector.forecast.ForecastMeasurement;
import badWeatherApp.serverUtility.serverCommunication.Connectable;
import badWeatherApp.serverUtility.serverCommunication.CurrentWeatherRequestable;
import badWeatherApp.serverUtility.serverCommunication.ForecastWeatherRequestable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class ForecastResponseCollector extends ResponseCollector {

    private List<ForecastWeatherReadable> forecastList;

    public ForecastResponseCollector(List<Connectable> requestServers, ForecastType forecastType, LocationDTO location) {
        super(requestServers, forecastType, location);
    }

    public List<Double> getForecastMeasurement(ForecastMeasurement forecastMeasurement, int daysFromNow) {
        if (forecastList == null) {
            requestTime = LocalDateTime.now();
            forecastList = getForecasts();
        }
        switch (forecastMeasurement) {
            case TEMPERATURE_MIN:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getMinTemperature,daysFromNow);
            case TEMPERATURE_MAX:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getMaxTemperature,daysFromNow);
            case TEMPERATURE_DAY:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getDayTimeTemperature,daysFromNow);
            case TEMPERATURE_NIGHT:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getNightTimeTemperature,daysFromNow);
            case TEMPERATURE_EVENING:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getEveningTimeTemperature,daysFromNow);
            case TEMPERATURE_MORNING:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getMorningTimeTemperature,daysFromNow);
            case FEELS_LIKE_TEMPERATURE_DAY:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getDayTimeFeelsLikeTemperature,daysFromNow);
            case FEELS_LIKE_TEMPERATURE_NIGHT:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getNightTimeFeelsLikeTemperature,daysFromNow);
            case FEELS_LIKE_TEMPERATURE_EVENING:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getEveningTimeFeelsLikeTemperature,daysFromNow);
            case FEELS_LIKE_TEMPERATURE_MORNING:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getMorningTimeFeelsLikeTemperature,daysFromNow);
            case WIND_SPEED:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getWindSpeed,daysFromNow);
            case WIND_DIRECTION:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getWindDegree,daysFromNow);
            case PRESSURE:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getPressure,daysFromNow);
            case HUMIDITY:
                return getForecastMeasurementFunction(ForecastWeatherReadable::getHumidity,daysFromNow);
            default:
                throw new IllegalArgumentException("Wrong parameter");
        }
    }

    private List<Double> getForecastMeasurementFunction(BiFunction<ForecastWeatherReadable, Integer, Double> function, Integer daysFromNow) {
        List<Double> returnValues = new ArrayList<>();
        forecastList.forEach(forecast -> returnValues.add(function.apply(forecast,daysFromNow)));
        return returnValues;
    }

    private List<ForecastWeatherReadable> getForecasts() {
        if (forecastList == null) {
            forecastList = getForecastCollection();
        }
        return forecastList;
    }

    private List<ForecastWeatherReadable> getForecastCollection() {
        List<ForecastWeatherReadable> readableList = new ArrayList<>();
        requestServers.forEach(s -> {
            if (s instanceof CurrentWeatherRequestable) {
                try {
                    readableList.add(getForecast((ForecastWeatherRequestable) s));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return readableList;
    }

    private ForecastWeatherReadable getForecast(ForecastWeatherRequestable server) throws IOException {
        switch (location.getInputType()) {
            case BY_CITY:
                return JsonStringDeserializer.deserialize(server.getForecastByCity(getCity()), server.getForecastResponseClass());
            case BY_COORDINATES:
                return JsonStringDeserializer.deserialize(server.getForecastByCoordinates(getLatitude(), getLongitude()), server.getForecastResponseClass());
            default:
                return null;
        }
    }
}
