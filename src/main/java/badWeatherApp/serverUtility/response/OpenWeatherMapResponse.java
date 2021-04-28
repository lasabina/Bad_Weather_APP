package badWeatherApp.serverUtility.response;

import badWeatherApp.serverUtility.response.WeatherReadable;

import java.time.LocalTime;

public class OpenWeatherMapResponse implements WeatherReadable {

    Coord coordObject;
    Main mainObject;
    Wind windObject;
    Sys sysObject;
    private String name;

    // Getter Methods
    public Main getMain() {
        return mainObject;
    }

    // Setter Methods
    public void setMain(Main mainObject) {
        this.mainObject = mainObject;
    }

    public Wind getWind() {
        return windObject;
    }

    public void setWind(Wind windObject) {
        this.windObject = windObject;
    }

    @Override
    public LocalTime getObservationTime() {
        return null;
    }

    @Override
    public double getTemperature() {
        return getMain().temp;
    }

    @Override
    public double getFeelsLikeTemperature() {
        return getMain().feels_like;
    }

    @Override
    public double getHumidity() {
        return getMain().humidity;
    }

    @Override
    public double getPressure() {
        return getMain().pressure;
    }

    @Override
    public double getWindSpeed() {
        return getWind().speed;
    }

    @Override
    public double getWindDegree() {
        return getWind().deg;
    }

    @Override
    public String getCity() {
        return name;
    }

    @Override
    public String getCountry() {
        return sysObject.getCountry();
    }

    public class Coord {
        private float lon;
        private float lat;

        // Getter Methods

        public float getLon() {
            return lon;
        }

        public void setLon(float lon) {
            this.lon = lon;
        }

        // Setter Methods

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }
    }

    public class Wind {
        private float speed;
        private float deg;

        // Setter Methods
        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public void setDeg(float deg) {
            this.deg = deg;
        }
    }

    public class Main {
        private float temp;
        private float feels_like;
        private float pressure;
        private float humidity;

        // Setter Methods
        public void setTemp(float temp) {
            this.temp = temp;
        }

        public void setFeels_like(float feels_like) {
            this.feels_like = feels_like;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }
    }

    public class Sys {
        private String country;


        // Getter Methods

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }


}