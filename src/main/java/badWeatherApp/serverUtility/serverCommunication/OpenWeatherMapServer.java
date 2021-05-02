package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.serverUtility.response.WeatherReadable;
import badWeatherApp.serverUtility.response.OpenWeatherMapResponse;

import java.io.IOException;

public class OpenWeatherMapServer implements CurrentWeatherRequestable {
    @Override
    public String getServerName() {
        return "OpenWeatherMap";
    }

    @Override
    public String getBaseUrl() {
        return "http://api.openweathermap.org/data/2.5/";
    }

    @Override
    public String getApiKey() {
        return "5ea105b45cbca36fc189afcc0fd9ea5f";
    }

    @Override
    public Class<? extends WeatherReadable> getCurrentWeatherResponseClass() {
        return OpenWeatherMapResponse.class;
    }

    @Override
    public String getCurrentWeatherByCity(String city) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "/weather?q=" + city + "&appid=" + getApiKey()+ "&units=metric");
    }

    @Override
    public String getCurrentWeatherByCoordinates(double lat, double lon) throws IOException {
        return null;
    }
}
