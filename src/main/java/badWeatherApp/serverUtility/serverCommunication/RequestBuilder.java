package badWeatherApp.serverUtility.serverCommunication;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class RequestBuilder {

    public static String getResponse(String query) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(query)
                .build();
        return client.newCall(request).execute().body().string();
    }

}
