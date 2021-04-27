package badWeatherApp.databaseUtility.forecast.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class ForecastDTO {

    private Integer idForecast;
    private Date observationDate;
    private Date forecastDate;
    private double temperature;
    private double feelsLike;
    private double preassure;
    private double humidity;
    private double windSpeed;
    private double windDegree;

    public ForecastDTO() {}

    public ForecastDTO(Integer idForecast, Date observationDate, Date forecastDate, double temperature, double feelsLike, double preassure, double humidity, double windSpeed, double windDegree) {
        this.idForecast = idForecast;
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

    public void setIdForecast(Integer idForecast) {
        this.idForecast = idForecast;
    }

    public Date getObservationDate() {
        return observationDate;
    }

    public void setObservationDate(Date observationDate) {
        this.observationDate = observationDate;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
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
}
