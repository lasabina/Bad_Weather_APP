package badWeatherApp.databaseUtility.forecast.entity;


import badWeatherApp.databaseUtility.location.entity.Location;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Forecast.FIND_BY_CITY, query = "SELECT e from Forecast e WHERE e.city = :city ")
})

@NamedQueries({
        @NamedQuery(name = Forecast.FIND_BY_COUNTRY, query = "SELECT e from Forecast e WHERE e.country = :coutry ")
})

@Entity
@Table(name = "Forecasts")
public class Forecast {

    public static final String FIND_BY_CITY = "Forecast.findByCity";
    public static final String FIND_BY_COUNTRY = "Forecast.findByCountry";

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer idForecast;

    @Column(nullable = false)
    private LocalDateTime observationDate;

    @Column(nullable = false)
    private LocalDateTime forecastDate;

    @Column(nullable = false)
    private double temperature;

    @Column(nullable = false)
    private double feelsLike;

    @Column(nullable = false)
    private double preassure;

    @Column(nullable = false)
    private double humidity;

    @Column
    private double windSpeed;

    @Column
    private double windDegree;

    @ManyToOne
    private Location location;


    public Forecast() {
    }

    public Forecast(LocalDateTime observationDate, LocalDateTime forecastDate, double temperature, double feelsLike, double preassure, double humidity) {
        this.observationDate = observationDate;
        this.forecastDate = forecastDate;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.preassure = preassure;
        this.humidity = humidity;
    }
    public Forecast(LocalDateTime observationDate, LocalDateTime forecastDate, double temperature, double feelsLike, double preassure, double humidity, double windSpeed, double windDegree) {
        this.observationDate = observationDate;
        this.forecastDate = forecastDate;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.preassure = preassure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
    }

    public Integer getIdForecast() {
        return idForecast;
    }

    public void setIdForecast(int idForecast) {
        this.idForecast = idForecast;
    }

    public LocalDateTime getObservationDate() {
        return observationDate;
    }

    public void setObservationDate(LocalDateTime observationDate) {
        this.observationDate = observationDate;
    }

    public LocalDateTime getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDateTime forecastDate) {
        this.forecastDate = forecastDate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getPreassure() {
        return preassure;
    }

    public void setPreassure(double preassure) {
        this.preassure = preassure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(double windDegree) {
        this.windDegree = windDegree;
    }


    @Override
    public String toString() {
        return "Forecast{" +
                "idForecast=" + idForecast +
                ", observationDate=" + observationDate +
                ", forecastDate=" + forecastDate +
                ", temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", preassure=" + preassure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", windDegree=" + windDegree +
                '}';
    }
}
