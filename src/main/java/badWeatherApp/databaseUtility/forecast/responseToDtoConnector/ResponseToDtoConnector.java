package badWeatherApp.databaseUtility.forecast.responseToDtoConnector;

import badWeatherApp.dataOperations.dataCalculator.DataCalculator;
import badWeatherApp.databaseUtility.forecast.entity.ForecastDTO;
import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.responseCollector.current.CurrentMeasurement;
import badWeatherApp.serverUtility.responseCollector.current.CurrentResponseCollector;

import java.time.LocalDateTime;

public class ResponseToDtoConnector {

    public static ForecastDTO createForecastDTOFromResponse(CurrentResponseCollector rc) {

        DataCalculator dc = new DataCalculator(1);

        double averageTemp = dc.average(rc.getCurrentMeasurementFunction(CurrentMeasurement.TEMPERATURE));
        double averageFeelsLikeTemp = dc.average(rc.getCurrentMeasurementFunction(CurrentMeasurement.FEELS_LIKE));
        double averageWindSpeed = dc.average(rc.getCurrentMeasurementFunction(CurrentMeasurement.WIND_SPEED));
        double averageWindDir = dc.average(rc.getCurrentMeasurementFunction(CurrentMeasurement.WIND_DIRECTION));
        double averagePressure = dc.average(rc.getCurrentMeasurementFunction(CurrentMeasurement.PRESSURE));
        double averageHumidity = dc.average(rc.getCurrentMeasurementFunction(CurrentMeasurement.HUMIDITY));

        LocalDateTime observationTime = rc.getObservationTime();

        LocationDTO locationDTO;

        if (rc.getCity() != null) {
            locationDTO = new LocationDTO(rc.getCity());
        } else {
            locationDTO = new LocationDTO(rc.getLatitude(), rc.getLongitude());
        }


        return new ForecastDTO(null,
                observationTime,
                observationTime,
                averageTemp,
                averageFeelsLikeTemp,
                averagePressure,
                averageHumidity,
                averageWindSpeed,
                averageWindDir,
                locationDTO);
    }

}
