package badWeatherApp.serverUtility.response;

import java.time.LocalTime;

public interface WeatherReadable {

    LocalTime getObservationTime();

    double getTemperature();
    double getFeelsLikeTemperature();
    double getHumidity();
    double getPressure();
    double getWindSpeed();
    double getWindDegree();

}
