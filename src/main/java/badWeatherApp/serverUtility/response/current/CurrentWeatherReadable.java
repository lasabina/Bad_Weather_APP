package badWeatherApp.serverUtility.response.current;

import java.time.LocalTime;

public interface CurrentWeatherReadable {

    String getCity();
    String getCountry();
    LocalTime getObservationTime();
    double getTemperature();
    double getFeelsLikeTemperature();
    double getHumidity();
    double getPressure();
    double getWindSpeed();
    double getWindDegree();

}
