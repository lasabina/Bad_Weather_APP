package badWeatherApp.ui.menu;


public class BadSettings {

    private static boolean PRINT_AVARAGE_TEMP = true;

    public static boolean isPrintAvarageTemp() {
        return PRINT_AVARAGE_TEMP;
    }

    public static void switchPrintAvarageTemp() {

        PRINT_AVARAGE_TEMP = !PRINT_AVARAGE_TEMP;

    }

    private static boolean PRINT_FELT_TEMP = true;

    public static boolean isPrintFeltTemp() {
        return PRINT_FELT_TEMP;
    }

    public static void switchPrintFeltTemp() {

        PRINT_FELT_TEMP = !PRINT_FELT_TEMP;

    }

    private static boolean PRINT_PRESSURE = true;

    public static boolean isPrintPressure() {
        return PRINT_PRESSURE;
    }

    public static void switchPrintPressure() {

        PRINT_PRESSURE = !PRINT_PRESSURE;

    }

    private static boolean PRINT_HUMIDITY = true;

    public static boolean isPrintHumidity() {
        return PRINT_HUMIDITY;
    }

    public static void switchPrintHumidity() {

        PRINT_HUMIDITY = !PRINT_HUMIDITY;

    }

    private static boolean PRINT_WIND_SPEED = true;

    public static boolean isPrintWindSpeed() {
        return PRINT_WIND_SPEED;
    }

    public static void switchPrintWindSpeed() {

        PRINT_WIND_SPEED = !PRINT_WIND_SPEED;

    }

    private static boolean PRINT_WIND_DIR = true;

    public static boolean isPrintWindDir() {
        return PRINT_WIND_DIR;
    }

    public static void switchPrintWindDir() {

        PRINT_WIND_DIR = !PRINT_WIND_DIR;

    }


}
