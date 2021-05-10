package badWeatherApp.serverUtility.serverCommunication;

import badWeatherApp.databaseUtility.location.entity.LocationDTO;

import java.io.IOException;

public interface LocationLookupable {

    String getLocationFromCoordinates(double lat, double lon) throws IOException;

}
