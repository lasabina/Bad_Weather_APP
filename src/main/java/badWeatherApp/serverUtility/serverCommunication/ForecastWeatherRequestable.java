package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.serverUtility.response.forecast.ForecastWeatherReadable;

import java.io.IOException;

public interface ForecastWeatherRequestable extends Connectable{
    String getForecastByCity(String city) throws IOException;
    String getForecastByCoordinates(double lat, double lon) throws  IOException;
    Class<? extends ForecastWeatherReadable> getForecastResponseClass();
}
