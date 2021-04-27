package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.serverUtility.json.JsonStringDeserializer;
import badWeatherApp.serverUtility.response.WeatherReadable;
import badWeatherApp.serverUtility.response.openWeatherMap.OpenWeatherMapResponse;
import badWeatherApp.serverUtility.response.weatherStack.WeatherstackResponse;
import badWeatherApp.serverUtility.serverCommunication.openWeatherMap.OpenWeatherMapServer;
import badWeatherApp.serverUtility.serverCommunication.weatherStack.WeatherstackServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerTest {

    public static void main(String[] args) {

        Requestable weatherstackServer = new WeatherstackServer();
        Requestable openWeatherMap = new OpenWeatherMapServer();

        Map<Requestable, Class<? extends WeatherReadable>> weatherMap = new HashMap<>(
                Map.of(new WeatherstackServer(), WeatherstackResponse.class,
                        new OpenWeatherMapServer(), OpenWeatherMapResponse.class));

        String city = "Warszawa";
        weatherMap.forEach((k, v) -> {
            System.out.println(k.getServerName());
            printAll(getWeatherReadableFromServer(k, v, city));
            System.out.println("\n");
        });

    }

    public static WeatherReadable getWeatherReadableFromServer(Requestable server, Class<? extends WeatherReadable> response, String city) {
        try {
            String json = server.getCurrentForecastForCity(city);
            return JsonStringDeserializer.deserialize(json, response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printAll(WeatherReadable weatherReadable) {
        System.out.println("Observation time - " + weatherReadable.getObservationTime());
        System.out.println("Temperature - " + weatherReadable.getTemperature());
        System.out.println("Feels like - " + weatherReadable.getFeelsLikeTemperature());
        System.out.println("Wind degree - " + weatherReadable.getWindDegree());
        System.out.println("Wind speed - " + weatherReadable.getWindSpeed());
        System.out.println("Pressure - " + weatherReadable.getPressure());
        System.out.println("Humidity - " + weatherReadable.getHumidity());
    }

}
