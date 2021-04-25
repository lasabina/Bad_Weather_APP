package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.serverUtility.json.JsonStringDeserializer;
import badWeatherApp.serverUtility.response.weatherStack.WeatherstackResponse;
import badWeatherApp.serverUtility.serverCommunication.weatherStack.WeatherstackServer;

import java.io.IOException;

public class ServerTest {

    public static void main(String[] args) {

        WeatherstackServer weatherstackServer = new WeatherstackServer();
        try {
            String jsonLondyn = weatherstackServer.getCurrentForecastForCity("Londyn");
            WeatherstackResponse deserialize = JsonStringDeserializer.deserialize(jsonLondyn, WeatherstackResponse.class);
            System.out.println(deserialize.getHumidity());
            System.out.println(deserialize.getTemperature());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
