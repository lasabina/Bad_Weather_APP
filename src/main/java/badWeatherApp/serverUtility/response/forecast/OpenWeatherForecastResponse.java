package badWeatherApp.serverUtility.response.forecast;

import badWeatherApp.serverUtility.response.forecast.ForecastWeatherReadable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class OpenWeatherForecastResponse implements ForecastWeatherReadable {

    private double latitude;
    private double longitude;
    private String timezone;
    private List<Daily> dailyForecasts = new ArrayList<>();

    @JsonCreator
    public OpenWeatherForecastResponse(
            @JsonProperty("lat") double latitude,
            @JsonProperty("lon") double longitude,
            @JsonProperty("timezone") String timezone,
            @JsonProperty("daily") List<Daily> dailyForecasts) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
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
    public LocalDateTime getForecastTime(int daysFromNow) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(dailyForecasts.get(daysFromNow - 1).timeInEpoch), ZoneId.of(timezone));
    }

    @Override
    public double getMinTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).temperature.minTemp;
    }

    @Override
    public double getMaxTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).temperature.maxTemp;
    }

    @Override
    public double getDayTimeTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).temperature.dayTemp;
    }

    @Override
    public double getNightTimeTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).temperature.nightTemp;
    }

    @Override
    public double getEveningTimeTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).temperature.eveningTemp;
    }

    @Override
    public double getMorningTimeTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).temperature.morningTemp;
    }

    @Override
    public double getDayTimeFeelsLikeTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).feelsLikeTemperature.dayTemp;
    }

    @Override
    public double getNightTimeFeelsLikeTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).feelsLikeTemperature.nightTemp;
    }

    @Override
    public double getEveningTimeFeelsLikeTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).feelsLikeTemperature.eveningTemp;
    }

    @Override
    public double getMorningTimeFeelsLikeTemperature(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).feelsLikeTemperature.morningTemp;
    }

    @Override
    public double getHumidity(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).humidity;
    }

    @Override
    public double getPressure(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).pressure;
    }

    @Override
    public double getWindSpeed(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).windSpeed;
    }

    @Override
    public double getWindDegree(int daysFromNow) {
        return dailyForecasts.get(daysFromNow-1).windDegree;
    }


    private static class Daily {

        private Temperature temperature;
        private FeelsLikeTemperature feelsLikeTemperature;
        private double humidity;
        private double pressure;
        private double windSpeed;
        private double windDegree;
        private long timeInEpoch;

        @JsonCreator
        public Daily(@JsonProperty("temp") Temperature Temperature,
                     @JsonProperty("feels_like") FeelsLikeTemperature feelsLikeTemperature,
                     @JsonProperty("humidity") double humidity,
                     @JsonProperty("pressure") double pressure,
                     @JsonProperty("wind_speed") double windSpeed,
                     @JsonProperty("wind_deg") double windDegree,
                     @JsonProperty("dt") long timeInEpoch) {
            this.temperature = Temperature;
            this.feelsLikeTemperature = feelsLikeTemperature;
            this.humidity = humidity;
            this.pressure = pressure;
            this.windSpeed = windSpeed;
            this.windDegree = windDegree;
            this.timeInEpoch = timeInEpoch;
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
