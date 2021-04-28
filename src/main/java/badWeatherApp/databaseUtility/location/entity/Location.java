package badWeatherApp.databaseUtility.location.entity;


import badWeatherApp.databaseUtility.forecast.entity.Forecast;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Location.FIND_LOCATION_BY_CITY, query = "SELECT e from Locations e WHERE e.city = :city "),
        @NamedQuery(name = Location.FIND_LOCATION_BY_COUNTRY, query = "SELECT e from Locations e WHERE e.country = :country ")
})

@Entity (name = "Locations")
public class Location {

    public static final String FIND_LOCATION_BY_CITY = "Location.findLocationByCity";
    public static final String FIND_LOCATION_BY_COUNTRY = "Location.findLocationByCountry";


    @Id
    @GeneratedValue
    private int idLocation;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(name = "Region")
    private String region;

    @Column(name = "Lat")
    private double lat;

    @Column(name = "Lon")
    private double lon;

    @OneToMany (mappedBy = "location")
    //@JoinColumn(name = "Location_id")
    private List<Forecast> forecasts;


    public Location() {
    }

    public Location(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public Location(int idLocation, String city, String country, String region, double lat, double lon, List<Forecast> forecasts) {
        this.idLocation = idLocation;
        this.city = city;
        this.country = country;
        this.region = region;
        this.lat = lat;
        this.lon = lon;
        this.forecasts = forecasts;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public String toString() {
        return "Location{" +
                "idLocation=" + idLocation +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", forecasts=" + forecasts +
                '}';
    }
}
