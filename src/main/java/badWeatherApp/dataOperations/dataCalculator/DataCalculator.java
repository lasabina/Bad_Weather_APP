package badWeatherApp.dataOperations.dataCalculator;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DataCalculator {
    private int numberOfDecimalPlaces;

    public double average(List<Double> exampleList) {
        Double average = exampleList.stream().mapToDouble(val -> val).average().orElse(0.0);

        return round(average, numberOfDecimalPlaces);
    }

    public static double averageDegreeDirection(List<Double> exampleList) {
        Double averageDegreeDirection = exampleList.stream().mapToDouble(val -> val).average().orElse(0.0);
        return averageDegreeDirection;
    }

    public static int doubleToInt(double value) {

        int intValueDegreeDirection = (int) Math.round(value);
        return intValueDegreeDirection;

    }

    public static String worldDirection(int degree) {

        if (degree >= 23 && degree <= 67) {
            return "Wiatr będzie północno-wschodni";

        } else if (degree >= 68 && degree <= 112) {
            return "Wiatr będzie wiał ze wschodu";

        } else if (degree >= 113 && degree <= 157) {
            return "Wiatr będzie południowo-wschodni";

        } else if (degree >= 158 && degree <= 202) {
            return "Wiatr będzie wiał z południa";

        } else if (degree >= 203 && degree <= 247) {
            return "Wiatr będzie południowo-zachodni";

        } else if (degree >= 248 && degree <= 292) {
            return "Wiatr będzie wiał z zachodu";

        } else if (degree >= 293 && degree <= 337) {
            return "Wiatr będzie pólnocno-zachodni";
        } else
            return "Wiatr będzie wiał z północny";

    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
