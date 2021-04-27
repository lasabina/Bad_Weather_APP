package badWeatherApp.serverUtility.responseCollector;

import badWeatherApp.serverUtility.json.JsonStringDeserializer;
import badWeatherApp.serverUtility.response.WeatherReadable;
import badWeatherApp.serverUtility.serverCommunication.Requestable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                for (WeatherReadable weatherReadable : forecastSet) {
                    returnValues.add(weatherReadable.getTemperature());
                }
                return returnValues;
            case FEELS_LIKE:
                for (WeatherReadable weatherReadable : forecastSet) {
                    returnValues.add(weatherReadable.getFeelsLikeTemperature());
                }
                return returnValues;
            case WIND_SPEED:
                for (WeatherReadable weatherReadable : forecastSet) {
                    returnValues.add(weatherReadable.getWindSpeed());
                }
                return returnValues;
            case WIND_DIRECTION:
                for (WeatherReadable weatherReadable : forecastSet) {
                    returnValues.add(weatherReadable.getWindDegree());
                }
                return returnValues;
            case PRESSURE:
                for (WeatherReadable weatherReadable : forecastSet) {
                    returnValues.add(weatherReadable.getPressure());
                }
                return returnValues;
            case HUMIDITY:
                for (WeatherReadable weatherReadable : forecastSet) {
                    returnValues.add(weatherReadable.getHumidity());
                }
                return returnValues;
            default:
                return null;
        }
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
