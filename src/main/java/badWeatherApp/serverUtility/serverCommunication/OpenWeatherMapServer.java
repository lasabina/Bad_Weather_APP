package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.serverUtility.response.OpenWeatherCurrentResponse;
import badWeatherApp.serverUtility.response.OpenWeatherForecastResponse;
import badWeatherApp.serverUtility.response.WeatherReadable;

import java.io.IOException;

public class OpenWeatherMapServer implements CurrentWeatherRequestable, ForecastWeatherRequestable {
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
        return OpenWeatherCurrentResponse.class;
    }

    @Override
    public String getCurrentWeatherByCity(String city) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "/weather?q=" + city + "&appid=" + getApiKey() + "&units=metric");
    }

    @Override
    public String getCurrentWeatherByCoordinates(double lat, double lon) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "weather?lat=" + lat + "&lon=" + lon + "&appid=" + getApiKey() + "&units=metric");
    }

    @Override
    public String getForecastByCity(String city) throws IOException {
        return null;
    }

    @Override
    public String getForecastByCoordinates(double lat, double lon) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "onecall?lat=" + lat + "&lon=" + lon + "&exclude=current,minutely,hourly,alerts" +
                "&appid=" + getApiKey() + "&units=metric");
    }

    @Override
    public Class<? extends WeatherReadable> getForecastResponseClass() {
        return OpenWeatherForecastResponse.class;
    }
}
