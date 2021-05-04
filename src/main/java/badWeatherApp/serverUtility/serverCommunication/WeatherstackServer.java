package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.serverUtility.response.WeatherReadable;
import badWeatherApp.serverUtility.response.WeatherstackCurrentResponse;

import java.io.IOException;

public class WeatherstackServer implements CurrentWeatherRequestable, ForecastWeatherRequestable {

    @Override
    public String getServerName() {
        return "WeatherStack";
    }

    @Override
    public String getBaseUrl() {
        return "http://api.weatherstack.com/";
    }

    @Override
    public String getApiKey() {
        return "7b58749d523ef5f51a52de07ab83d093";
    }

    @Override
    public Class<? extends WeatherReadable> getCurrentWeatherResponseClass() {
        return WeatherstackCurrentResponse.class;
    }

    @Override
    public String getCurrentWeatherByCity(String city) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "current?access_key=" + getApiKey() + "&query=" + city);
    }

    @Override
    public String getCurrentWeatherByCoordinates(double lat, double lon) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "current?access_key=" + getApiKey() + "&query=" + lat + "," + lon);
    }

    @Override
    public String getForecastByCity(String city) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "forecast?access_key=" + getApiKey() + "&query=" + city + "&forecast_days=7&hourly=0");
    }

    @Override
    public String getForecastByCoordinates(double lon, double lat) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "forecast?access_key=" + getApiKey() + "&query=" + lat + "," + lon + "&forecast_days=7&hourly=0");
    }

    @Override
    public Class<? extends WeatherReadable> getForecastResponseClass() {
        return null;
    }
}
