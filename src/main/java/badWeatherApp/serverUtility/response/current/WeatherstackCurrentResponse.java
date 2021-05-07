package badWeatherApp.serverUtility.response.current;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;

public class WeatherstackCurrentResponse implements CurrentWeatherReadable {

    Current current;
    Location location;

    private static class Current {

        private final String observationTime;
        private final int temperature;
        private final int windSpeed;
        private final int windDegree;
        private final int pressure;
        private final int humidity;
        private final int feelsLikeTemperature;

        @JsonCreator
        public Current(
                @JsonProperty("observation_time") String observationTime,
                @JsonProperty("temperature") int temperature,
                @JsonProperty("wind_speed") int windSpeed,
                @JsonProperty("wind_degree") int windDegree,
                @JsonProperty("pressure") int pressure,
                @JsonProperty("humidity") int humidity,
                @JsonProperty("feelslike") int feelsLikeTemperature) {
            this.observationTime = observationTime;
            this.temperature = temperature;
            this.windSpeed = windSpeed;
            this.windDegree = windDegree;
            this.pressure = pressure;
            this.humidity = humidity;
            this.feelsLikeTemperature = feelsLikeTemperature;
        }
    }

    private static class Location {
        private final String name;
        private final String country;
        private final String region;
        private final String latitude;
        private final String longitude;

        @JsonCreator
        public Location(
                @JsonProperty("name") String name,
                @JsonProperty("country") String country,
                @JsonProperty("region") String region,
                @JsonProperty("lat") String latitude,
                @JsonProperty("lon") String longitude) {
            this.name = name;
            this.country = country;
            this.region = region;
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    @JsonCreator
    public WeatherstackCurrentResponse(
            @JsonProperty("current") Current current,
            @JsonProperty("location") Location location) {
        this.current = current;
        this.location = location;
    }

    @Override
    public String getCity() {
        return location.name;
    }

    @Override
    public String getCountry() {
        return location.country;
    }

    @Override
    public LocalTime getObservationTime() {
        return LocalTime.parse(current.observationTime.substring(0,5));
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
        return current.windSpeed;
    }

    @Override
    public double getWindDegree() {
        return current.windDegree;
    }
}
