package badWeatherApp.serverUtility.response.forecast;

import java.time.LocalDateTime;

public interface ForecastWeatherReadable {

    String getCity();

    String getCountry();

    //ForecastTime
    LocalDateTime getForecastTime(int daysFromNow);

    //Temperature
    double getMinTemperature(int daysFromNow);

    double getMaxTemperature(int daysFromNow);

    double getDayTimeTemperature(int daysFromNow);

    double getNightTimeTemperature(int daysFromNow);

    double getEveningTimeTemperature(int daysFromNow);

    double getMorningTimeTemperature(int daysFromNow);

    //FeelsLikeTemperature
    double getDayTimeFeelsLikeTemperature(int daysFromNow);

    double getNightTimeFeelsLikeTemperature(int daysFromNow);

    double getEveningTimeFeelsLikeTemperature(int daysFromNow);

    double getMorningTimeFeelsLikeTemperature(int daysFromNow);

    //Atmosphere
    double getHumidity(int daysFromNow);

    double getPressure(int daysFromNow);

    double getWindSpeed(int daysFromNow);

    double getWindDegree(int daysFromNow);

}
