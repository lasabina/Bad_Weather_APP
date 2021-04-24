package badWeatherApp.serverUtility.serverCommunication;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ServerExample {

    private final static String BASE_URL = "http://api.weatherstack.com/current?";
    private final static String API_KEY = "7b58749d523ef5f51a52de07ab83d093";

    public static String getResponseBody(String city) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + getApiUrl() + "&query=" + city)
                .build();

        OkHttpClient client = new OkHttpClient();

        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    private static String getApiUrl(){
        return "access_key=" + API_KEY;
    }

}
