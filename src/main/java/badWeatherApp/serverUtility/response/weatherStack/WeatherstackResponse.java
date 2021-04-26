package badWeatherApp.serverUtility.response.weatherStack;

import badWeatherApp.serverUtility.response.WeatherReadable;

import java.time.LocalTime;

public class WeatherstackResponse implements WeatherReadable {

    Current current;

    public WeatherstackResponse() {
    }

    @Override
    public LocalTime getObservationTime() {
        return LocalTime.parse(current.getObservationTime().substring(0,5));
    }

    @Override
    public double getTemperature() {
        return current.getTemperature();
    }

    @Override
    public double getFeelsLikeTemperature() {
        return current.getFeelslikeTemperature();
    }

    @Override
    public double getHumidity() {
        return current.getHumidity();
    }

    @Override
    public double getPressure() {
        return current.getPressure();
    }

    @Override
    public double getWindSpeed() {
        return current.getWindSpeed();
    }

    @Override
    public double getWindDegree() {
        return current.getWindDegree();
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
