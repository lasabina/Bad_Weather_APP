package badWeatherApp.serverUtility.serverCommunication;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ServerExampleTest {

    @Test
    void shouldPrintResponseBodyForLondon(){
        try {
            System.out.println(ServerExample.getResponseBody("London"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}