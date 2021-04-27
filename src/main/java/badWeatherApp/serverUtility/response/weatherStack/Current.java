package badWeatherApp.serverUtility.response.weatherStack;

public class Current {

    private String observation_time;
    private int temperature;
    private int wind_speed;
    private int wind_degree;
    private int pressure;
    private int humidity;
    private int feelslike;

    public String getObservationTime() {
        return observation_time;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return wind_speed;
    }

    public double getWindDegree() {
        return wind_degree;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getFeelslikeTemperature() {
        return feelslike;
    }

    public void setObservation_time(String observation_time) {
        this.observation_time = observation_time;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setWind_speed(int wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setWind_degree(int wind_degree) {
        this.wind_degree = wind_degree;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setFeelslike(int feelslike) {
        this.feelslike = feelslike;
    }
}
