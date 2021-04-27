package badWeatherApp.serverUtility.serverCommunication.openWeatherMap;

import badWeatherApp.serverUtility.serverCommunication.RequestBuilder;
import badWeatherApp.serverUtility.serverCommunication.Requestable;

import java.io.IOException;

public class OpenWeatherMapServer implements Requestable {
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
    public String getCurrentForecastForCity(String city) throws IOException {
        return RequestBuilder.getResponse(getBaseUrl() + "/weather?q=" + city + "&appid=" + getApiKey()+ "&units=metric");
    }
}
