package badWeatherApp.serverUtility.response.current;

import badWeatherApp.serverUtility.response.current.CurrentWeatherReadable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;

public class OpenCurrentWeatherCurrentResponse implements CurrentWeatherReadable {

    Coordinate coordinate;
    Current current;
    Wind wind;
    Location location;

    @JsonCreator
    public OpenCurrentWeatherCurrentResponse(@JsonProperty("coord") Coordinate coordinate,
                                             @JsonProperty("main") Current current,
                                             @JsonProperty("wind") Wind wind,
                                             @JsonProperty("sys") Location location) {
        this.coordinate = coordinate;
        this.current = current;
        this.wind = wind;
        this.location = location;
    }

    @Override
    public LocalTime getObservationTime() {
        return null;
    }

    @Override
    public double getTemperature() {
        return current.temperature;
    }

    @Override
    public double getFeelsLikeTemperature() {
        return current.feelsLikeTemperature;
    }

    @Override
    public double getHumidity() {
        return current.humidity;
    }

    @Override
    public double getPressure() {
        return current.pressure;
    }

    @Override
    public double getWindSpeed() {
        return wind.windSpeed;
    }

    @Override
    public double getWindDegree() {
        return wind.windDegree;
    }

    @Override
    public String getCity() {
        return "Unknown";
    }

    @Override
    public String getCountry() {
        return location.country;
    }

    private static class Coordinate {
        private float latitude;
        private float longitude;

        @JsonCreator
        public Coordinate(@JsonProperty("lat") float latitude,
                          @JsonProperty("lon") float longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    private static class Wind {
        private float windSpeed;
        private float windDegree;

        @JsonCreator
        public Wind(@JsonProperty("speed") float windSpeed,
                    @JsonProperty("deg") float windDegree) {
            this.windSpeed = windSpeed;
            this.windDegree = windDegree;
        }
    }

    private static class Current {
        private float temperature;
        private float feelsLikeTemperature;
        private float pressure;
        private float humidity;

        @JsonCreator
        public Current(@JsonProperty("temp") float temperature,
                       @JsonProperty("feels_like") float feelsLikeTemperature,
                       @JsonProperty("pressure") float pressure,
                       @JsonProperty("humidity") float humidity) {
            this.temperature = temperature;
            this.feelsLikeTemperature = feelsLikeTemperature;
            this.pressure = pressure;
            this.humidity = humidity;
        }
    }

    private static class Location {
        private String country;

        @JsonCreator
        public Location(@JsonProperty("country") String country) {
            this.country = country;
        }
    }
}