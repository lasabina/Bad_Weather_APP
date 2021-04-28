package badWeatherApp.dataOperations.dataFormater;

import badWeatherApp.dataOperations.dataCalculator.DataCalculator;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DataFormater {


    public static String displayTheAverageTemperature(List<Double> valueList) {
        DataCalculator dataCalculatorTemperature = new DataCalculator(0);
        return "Tego dnia temperatura wyniesie: " + dataCalculatorTemperature.average(valueList) + "°C,";

    }

    public static String displayTheAverageFeltTemperature(List<Double> valueList) {
        DataCalculator dataCalculatorFeltTemperature = new DataCalculator(0);
        return "Temperatura odczuwalna to: " + dataCalculatorFeltTemperature.average(valueList) + "°C,";

    }

    public static String displayTheAveragePressure(List<Double> valueList) {
        DataCalculator dataCalculatorPressure = new DataCalculator(0);
        return "Średnia wysokość ciśnienia atmosferycznego, będzie równa: " + dataCalculatorPressure.average(valueList) + " hPa,";

    }

    public static String displayTheAverageHumidity(List<Double> valueList) {
        DataCalculator dataCalculatorHumidity = new DataCalculator(0);
        return "Wilgotność powietrza wyniesie: " + dataCalculatorHumidity.average(valueList) + " %,";

    }

    public static String displayTheAverageWindSpeed(List<Double> valueList) {
        DataCalculator dataCalculatorWindSpeed = new DataCalculator(0);
        return "Prędkość wiatru to: " + dataCalculatorWindSpeed.average(valueList) + " CHYBA W KM/H,";

    }

    public static String displayTheAverageWindDir(List<Double> valueList) {
        DataCalculator dataCalculatorWindDir = new DataCalculator(0);
        return "Kierunek wiatru to: " + dataCalculatorWindDir.average(valueList) + ",";
    }

    public static String displayWorldDirection(List<Double> valueList) {
        DataCalculator displayWorldDirection = new DataCalculator(0);
        return displayWorldDirection.worldDirection(DataCalculator.doubleToInt(DataCalculator.averageDegreeDirection(valueList))) + ".";
    }


}

