package badWeatherApp.serverUtility.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OpenWeatherForecastResponse implements WeatherReadable {

    public double latitude;
    public double longitude;
    public List<Daily> dailyForecasts = new ArrayList<>();

    @JsonCreator
    public OpenWeatherForecastResponse(
            @JsonProperty("lat") double latitude,
            @JsonProperty("lon") double longitude,
            @JsonProperty("daily") List<Daily> dailyForecasts) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.dailyForecasts = dailyForecasts;
    }

    @Override
    public String getCity() {
        return "Unknown";
    }

    @Override
    public String getCountry() {
        return "Unknown";
    }

    @Override
    public LocalTime getObservationTime() {
        return null;
    }

    @Override
    public double getTemperature() {
        return 0;
    }

    @Override
    public double getFeelsLikeTemperature() {
        return 0;
    }

    @Override
    public double getHumidity() {
        return 0;
    }

    @Override
    public double getPressure() {
        return 0;
    }

    @Override
    public double getWindSpeed() {
        return 0;
    }

    @Override
    public double getWindDegree() {
        return 0;
    }

    private static class Daily {

        private Temperature temperature;

        private FeelsLikeTemperature feelsLikeTemperature;
        private double humidity;
        private double windSpeed;
        private double windDegree;

        @JsonCreator
        public Daily(@JsonProperty("temp") Temperature Temperature,
                     @JsonProperty("feels_like") FeelsLikeTemperature feelsLikeTemperature,
                     @JsonProperty("humidity") double humidity,
                     @JsonProperty("wind_speed") double windSpeed,
                     @JsonProperty("wind_deg") double windDegree) {
            this.temperature = Temperature;
            this.feelsLikeTemperature = feelsLikeTemperature;
            this.humidity = humidity;
            this.windSpeed = windSpeed;
            this.windDegree = windDegree;
        }

        private static class Temperature {
            private double dayTemp;
            private double minTemp;
            private double maxTemp;
            private double nightTemp;
            private double eveningTemp;
            private double morningTemp;

            @JsonCreator
            public Temperature(@JsonProperty("day") double dayTemp,
                               @JsonProperty("min") double minTemp,
                               @JsonProperty("max") double maxTemp,
                               @JsonProperty("night") double nightTemp,
                               @JsonProperty("eve") double eveningTemp,
                               @JsonProperty("morn") double morningTemp) {
                this.dayTemp = dayTemp;
                this.minTemp = minTemp;
                this.maxTemp = maxTemp;
                this.nightTemp = nightTemp;
                this.eveningTemp = eveningTemp;
                this.morningTemp = morningTemp;
            }
        }

        private static class FeelsLikeTemperature {
            private double dayTemp;
            private double nightTemp;
            private double eveningTemp;
            private double morningTemp;

            @JsonCreator
            public FeelsLikeTemperature(@JsonProperty("day") double dayTemp,
                                        @JsonProperty("night") double nightTemp,
                                        @JsonProperty("eve") double eveningTemp,
                                        @JsonProperty("morn") double morningTemp) {
                this.dayTemp = dayTemp;
                this.nightTemp = nightTemp;
                this.eveningTemp = eveningTemp;
                this.morningTemp = morningTemp;
            }
        }
    }

}
