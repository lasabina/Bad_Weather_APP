package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.serverUtility.response.current.CurrentWeatherReadable;

import java.io.IOException;

public interface CurrentWeatherRequestable extends Connectable{

    String getCurrentWeatherByCity(String city) throws IOException;
    String getCurrentWeatherByCoordinates(double lat, double lon) throws IOException;
    Class<? extends CurrentWeatherReadable> getCurrentWeatherResponseClass();


}
