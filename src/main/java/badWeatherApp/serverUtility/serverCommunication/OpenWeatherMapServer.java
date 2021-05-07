package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.json.JsonStringDeserializer;
import badWeatherApp.serverUtility.response.forecast.ForecastWeatherReadable;
import badWeatherApp.serverUtility.response.current.OpenCurrentWeatherCurrentResponse;
import badWeatherApp.serverUtility.response.forecast.OpenWeatherForecastResponse;
import badWeatherApp.serverUtility.response.current.CurrentWeatherReadable;

import java.io.IOException;

public class OpenWeatherMapServer implements CurrentWeatherRequestable, ForecastWeatherRequestable, LocationLookupable {

    @Override
    public String getServerName() {
        return "OpenWeatherMap";
    }

    @Override
    public String getBaseUrl() {
        return "http://api.openweathermap.org/";
    }

    @Override
    public String getApiKey() {
        return "5ea105b45cbca36fc189afcc0fd9ea5f";
    }

    @Override
    public String getCurrentWeatherByCity(String city) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "data/2.5/weather?q=" + city + "&appid=" + getApiKey() + "&units=metric");
    }

    @Override
    public String getCurrentWeatherByCoordinates(double lat, double lon) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + getApiKey() + "&units=metric");
    }

    @Override
    public Class<? extends CurrentWeatherReadable> getCurrentWeatherResponseClass() {
        return OpenCurrentWeatherCurrentResponse.class;
    }

    @Override
    public String getForecastByCity(String city) throws IOException {
        return null;
    }

    @Override
    public String getForecastByCoordinates(double lat, double lon) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=current,minutely,hourly,alerts" +
                "&appid=" + getApiKey() + "&units=metric");
    }

    @Override
    public Class<? extends ForecastWeatherReadable> getForecastResponseClass() {
        return OpenWeatherForecastResponse.class;
    }

    @Override
    public String getLocationFromCoordinates(double lat, double lon) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "geo/1.0/reverse?lat=" + lat + "&lon=" + lon + "&limit=5&appid=" + getApiKey());
    }
}
