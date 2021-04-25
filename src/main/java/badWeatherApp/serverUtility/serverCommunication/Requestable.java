package badWeatherApp.serverUtility.serverCommunication;

import java.io.IOException;

public interface Requestable {

    String getServerName();
    String getBaseUrl();
    String getApiKey();
    String getCurrentForecastForCity(String city) throws IOException;

}
