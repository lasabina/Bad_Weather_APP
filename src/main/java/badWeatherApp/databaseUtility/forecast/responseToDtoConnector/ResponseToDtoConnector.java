package badWeatherApp.databaseUtility.forecast.responseToDtoConnector;

import badWeatherApp.dataOperations.dataCalculator.DataCalculator;
import badWeatherApp.databaseUtility.forecast.entity.ForecastDTO;
import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.responseCollector.Measurement;
import badWeatherApp.serverUtility.responseCollector.ResponseCollector;

import java.time.LocalDateTime;

public class ResponseToDtoConnector {

    public static ForecastDTO createForecastDTOFromResponse(ResponseCollector rc) {

        DataCalculator dc = new DataCalculator(1);

        double averageTemp = dc.average(rc.getMeasurement(Measurement.TEMPERATURE));
        double averageFeelsLikeTemp = dc.average(rc.getMeasurement(Measurement.FEELS_LIKE));
        double averageWindSpeed = dc.average(rc.getMeasurement(Measurement.WIND_SPEED));
        double averageWindDir = dc.average(rc.getMeasurement(Measurement.WIND_DIRECTION));
        double averagePressure = dc.average(rc.getMeasurement(Measurement.PRESSURE));
        double averageHumidity = dc.average(rc.getMeasurement(Measurement.HUMIDITY));

        String locationCity = rc.getCity();
        LocalDateTime observationTime = rc.getObservationTime();

        //LocationDTO locationDTO = new LocationDTO(null,city,country,region,lat,lon);

        return new ForecastDTO(null,
                observationTime,
                observationTime,
                averageTemp,
                averageFeelsLikeTemp,
                averagePressure,
                averageHumidity,
                averageWindSpeed,
                averageWindDir,
                new LocationDTO(locationCity));
    }

}
