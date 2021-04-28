package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.serverUtility.response.WeatherReadable;
import badWeatherApp.serverUtility.response.WeatherstackResponse;
import badWeatherApp.serverUtility.serverCommunication.RequestBuilder;
import badWeatherApp.serverUtility.serverCommunication.Requestable;

import java.io.IOException;

public class WeatherstackServer implements Requestable {
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
    public String getCurrentForecastForCity(String city) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "current?access_key=" + getApiKey() + "&query=" + city);
    }

    @Override
    public Class<? extends WeatherReadable> getResponseClass() {
        return WeatherstackResponse.class;
    }
}
