package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.serverUtility.response.WeatherReadable;

import java.io.IOException;

public interface Connectable {

    String getServerName();
    String getBaseUrl();
    String getApiKey();
    }
