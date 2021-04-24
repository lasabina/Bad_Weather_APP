package badWeatherApp.serverUtility.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonDeserializer {

    public static <T> T deserialize(String jsonString, Class<T> targetClass) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T object = objectMapper.readValue(jsonString,targetClass);
        return object;
    }

}
