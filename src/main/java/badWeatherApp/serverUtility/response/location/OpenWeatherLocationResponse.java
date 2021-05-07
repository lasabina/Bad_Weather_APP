package badWeatherApp.serverUtility.response.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenWeatherLocationResponse implements LocationReadable {

    List<Location> locations;

    @JsonCreator
    public OpenWeatherLocationResponse(
            List<Location> locations) {
        this.locations = locations;
    }

    private static class Location {
        String locationName;
        String country;
        double latitude;
        double longitude;

        @JsonCreator
        public Location(
                @JsonProperty("name") String locationName,
                @JsonProperty("country") String country,
                @JsonProperty("lat") double latitude,
                @JsonProperty("lon") double longitude) {
            this.locationName = locationName;
            this.country = country;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "locationName='" + locationName + '\'' +
                    ", country='" + country + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }

    public int getSize() {
        return locations.size();
    }

    public void printAll() {
        for (Location location : locations) {
            System.out.println(location);
        }
    }

    @Override
    public String getName() {
        return locations.get(0).locationName;
    }

    @Override
    public String getCountry() {
        return locations.get(0).country;
    }

    @Override
    public double getLatitude() {
        return locations.get(0).latitude;
    }

    @Override
    public double getLongitude() {
        return locations.get(0).longitude;
    }

}
