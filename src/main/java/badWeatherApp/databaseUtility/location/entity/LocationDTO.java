package badWeatherApp.databaseUtility.location.entity;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LocationDTO {

    private int idLocation;
    private String city;
    private String country;
    private String region;
    private double lat;
    private double lon;

    public LocationDTO() {}

    public LocationDTO(String city) {
        this.city = city;
    }

    public LocationDTO(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public LocationDTO(int idLocation, String city, String country, String region, double lat, double lon) {
        this.idLocation = idLocation;
        this.city = city;
        this.country = country;
        this.region = region;
        this.lat = lat;
        this.lon = lon;
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
}
