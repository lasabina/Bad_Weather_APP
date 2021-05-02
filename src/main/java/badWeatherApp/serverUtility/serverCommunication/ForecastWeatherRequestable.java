package badWeatherApp.serverUtility.serverCommunication;

import java.io.IOException;

public interface ForecastWeatherRequestable extends Connectable{
    String getForecastByCity(String city) throws IOException;
    String getForecastByCoordinates(double lat, double lon) throws  IOException;
}
