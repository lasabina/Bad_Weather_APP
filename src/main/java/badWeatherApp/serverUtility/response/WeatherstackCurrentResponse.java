package badWeatherApp.serverUtility.response;

import badWeatherApp.serverUtility.response.WeatherReadable;

import java.time.LocalTime;

public class WeatherstackCurrentResponse implements WeatherReadable {

    Current current;
    Location locationObject;

    public class Current {

        private String observation_time;
        private int temperature;
        private int wind_speed;
        private int wind_degree;
        private int pressure;
        private int humidity;
        private int feelslike;

        public String getObservationTime() {
            return observation_time;
        }

        public double getTemperature() {
            return temperature;
        }

        public double getWindSpeed() {
            return wind_speed;
        }

        public double getWindDegree() {
            return wind_degree;
        }

        public double getPressure() {
            return pressure;
        }

        public double getHumidity() {
            return humidity;
        }

        public double getFeelslikeTemperature() {
            return feelslike;
        }

        public void setObservation_time(String observation_time) {
            this.observation_time = observation_time;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public void setWind_speed(int wind_speed) {
            this.wind_speed = wind_speed;
        }

        public void setWind_degree(int wind_degree) {
            this.wind_degree = wind_degree;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public void setFeelslike(int feelslike) {
            this.feelslike = feelslike;
        }
    }

    public class Location {
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;

        // Getter Methods

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        public String getRegion() {
            return region;
        }

        public String getLat() {
            return lat;
        }

        public String getLon() {
            return lon;
        }

        // Setter Methods

        public void setName(String name) {
            this.name = name;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }
    }

    public WeatherstackCurrentResponse() {
    }

    @Override
    public String getCity() {
        return locationObject.getName();
    }

    @Override
    public String getCountry() {
        return locationObject.getCountry();
    }

    @Override
    public LocalTime getObservationTime() {
        return LocalTime.parse(current.getObservationTime().substring(0,5));
    }

    @Override
    public double getTemperature() {
        return current.getTemperature();
    }

    @Override
    public double getFeelsLikeTemperature() {
        return current.getFeelslikeTemperature();
    }

    @Override
    public double getHumidity() {
        return current.getHumidity();
    }

    @Override
    public double getPressure() {
        return current.getPressure();
    }

    @Override
    public double getWindSpeed() {
        return current.getWindSpeed();
    }

    @Override
    public double getWindDegree() {
        return current.getWindDegree();
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
