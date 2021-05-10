package badWeatherApp.ui.menu;

import badWeatherApp.dataOperations.dataFormater.DataFormater;
import badWeatherApp.databaseUtility.forecast.control.ForecastManager;
import badWeatherApp.databaseUtility.forecast.entity.Forecast;
import badWeatherApp.databaseUtility.forecast.entity.ForecastDTO;
import badWeatherApp.databaseUtility.forecast.responseToDtoConnector.ResponseToDtoConnector;
import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.responseCollector.ForecastType;
import badWeatherApp.serverUtility.responseCollector.current.CurrentResponseCollector;
import badWeatherApp.serverUtility.serverCommunication.Connectable;
import badWeatherApp.serverUtility.serverCommunication.OpenWeatherMapServer;
import badWeatherApp.serverUtility.serverCommunication.WeatherstackServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void showMainMenu() {
        System.out.println("Aby skorzystac z 'Menu' wcisnij liczbę od 1 do 4 i zatwierdz ją klawiszem 'enter'.");
        System.out.println("1. Ulubione lokalizacje");
        System.out.println("2. Sprawdz prognoze");
        System.out.println("3. Historia wyszukiwania");
        System.out.println("4. Ustawienia (W trakcie budowy)");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showFavoritesMenu();
            case 2:
                showForecastWeatherMenu();
            case 3:
                showSearchHistoryMenu();
            case 4:
                showSettingsMenu();
            default:
                showMainMenu();
        }

    }

    private static void showFavoritesMenu() {
        System.out.println("Aby skorzystac z 'Menu' wcisnij liczbę od 1 do 4 i zatwierdz ją klawiszem 'enter'.");
        System.out.println("1. Dodaj");
        System.out.println("2. Usun");
        System.out.println("3. Edytuj");
        System.out.println("4. Aby powrocic do 'Menu' glownego");

        Scanner scanner = new Scanner(System.in);
        int choiceShowFavorite = scanner.nextInt();

        switch (choiceShowFavorite) {
            case 1:
                addFavorite();
                showFavoritesMenu();
            case 2:
                removeFavorite();
                showFavoritesMenu();
            case 3:
                editFavorite();
                showFavoritesMenu();
            case 4:
                showMainMenu();
            default:
        }

    }

    private static void addFavorite() {

//        LocationManager.addLocation();

    }

    private static void removeFavorite() {
        //todo

    }

    private static void editFavorite() {
        //todo

    }

    private static void showForecastWeatherMenu() {
        System.out.println("Aby wyszukać prognoze pogody wcisnij liczbę od 1 do 4 i zatwierdz ją klawiszem 'enter'.");
        System.out.println("1. Wyświetl prognoze dla wszystkich ulubionych");
        System.out.println("2. Podaj prognozę dla miasta");
        System.out.println("3. Podaj prognozę dla wspolrzędnych geograficznych (Funkcja w budowie)");
        System.out.println("4. Aby powrocic do 'Menu' glownego");

        Scanner scanner = new Scanner(System.in);
        int choiceShowFavorite = scanner.nextInt();

        switch (choiceShowFavorite) {
            case 1:
                showFavoriteForecastWeather();
                showForecastWeatherMenu();
            case 2:
                showForcastByCityName();
                showForecastWeatherMenu();
            case 3:
                showForecastByCoordinates();
                showForecastWeatherMenu();
            case 4:
            default:
                showMainMenu();
        }
    }

    private static void showFavoriteForecastWeather() {
        //todo
    }

    private static void showForcastByCityName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj miasto: ");
        String city = scanner.nextLine();

        List<Connectable> serverList = new ArrayList<>(List.of(
                new WeatherstackServer(),
                new OpenWeatherMapServer()
        ));

        CurrentResponseCollector rc = new CurrentResponseCollector(serverList, new LocationDTO(city), ForecastType.CURRENT);
        ForecastDTO forecast = ResponseToDtoConnector.createForecastDTOFromResponse(rc);

        System.out.println("-------------------------------");
        System.out.println(forecast.getForecastDate());
        System.out.println(forecast.getLocationDTO().getCity());
        System.out.println(DataFormater.displayTheAverageTemperature(forecast.getTemperature()));
        System.out.println(DataFormater.displayTheAverageFeltTemperature(forecast.getFeelsLike()));
        System.out.println(DataFormater.displayTheAverageWindSpeed(forecast.getWindSpeed()));
        System.out.println(DataFormater.displayWorldDirection(forecast.getWindDegree()));
        System.out.println(DataFormater.displayTheAverageHumidity(forecast.getHumidity()));
        System.out.println(DataFormater.displayTheAveragePressure(forecast.getPressure()));
        System.out.println("-------------------------------");

        ForecastManager.addForecast(forecast);
    }

    private static void showForecastByCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj szerokość geograficzną: ");
        double lat = scanner.nextDouble();
        System.out.println("Podaj długość geograficzną: ");
        double lon = scanner.nextDouble();

        List<Connectable> serverList = new ArrayList<>();

        serverList.add(new WeatherstackServer());
        serverList.add(new OpenWeatherMapServer());

        CurrentResponseCollector rc = new CurrentResponseCollector(serverList, new LocationDTO(lat,lon), ForecastType.FORECAST);
        ForecastDTO forecast = ResponseToDtoConnector.createForecastDTOFromResponse(rc);

        System.out.println("-------------------------------");
        System.out.println(forecast.getForecastDate());
        System.out.println(forecast.getLocationDTO().getLat() + " , " + forecast.getLocationDTO().getLon());
        System.out.println(DataFormater.displayTheAverageTemperature(forecast.getTemperature()));
        System.out.println(DataFormater.displayTheAverageFeltTemperature(forecast.getFeelsLike()));
        System.out.println(DataFormater.displayTheAverageWindSpeed(forecast.getWindSpeed()));
        System.out.println(DataFormater.displayWorldDirection(forecast.getWindDegree()));
        System.out.println(DataFormater.displayTheAverageHumidity(forecast.getHumidity()));
        System.out.println(DataFormater.displayTheAveragePressure(forecast.getPressure()));
        System.out.println("-------------------------------");

        ForecastManager.addForecast(forecast);

    }

    private static void showSearchHistoryMenu() {
        System.out.println("Aby skorzystac z 'Menu' wcisnij liczbę od 1 do 6 i zatwierdz ją klawiszem 'enter'.");
        System.out.println("1. Pokaz wszystko");
        System.out.println("2. Pokaz historie dla miasta");
        System.out.println("3. Pokaz historie dla panstwa");
        System.out.println("4. Wyczysc historie dla danego miasta");
        System.out.println("5. Usuń z historii wybraną prognozę");
        System.out.println("6. Naciśnij, aby powrócić do 'Menu' głównego");

        Scanner scanner = new Scanner(System.in);
        int choiceshowSearchHistory = scanner.nextInt();

        switch (choiceshowSearchHistory) {
            case 1:
                for (Forecast allForecast : ForecastManager.getAllForecasts()) {
                    System.out.println(allForecast);
                }
                showSearchHistoryMenu();
            case 2:
                showMyForecastWeatherHistoryByCity();
                showSearchHistoryMenu();
            case 3:
                showMyForecastWeatherHistoryByCountry();
                showSearchHistoryMenu();
            case 4:
                removeMyForecastWeatherHistorybyCity();
                showSearchHistoryMenu();
            case 5:
                removeMyForecastWeatherHistorybyID();
                showSearchHistoryMenu();
            case 6:
            default:
                showMainMenu();
        }

    }

    private static void removeMyForecastWeatherHistorybyID() {
        System.out.println("Podaj id prognozy, którą chcesz usunąć");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        ForecastManager.removeForecastbyID(id);

    }

    private static void removeMyForecastWeatherHistorybyCity() {
        System.out.println("Podaj miasto, dla którego historia ma zostać usunięta");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        ForecastManager.removeForecastbyCity(city);
    }

    private static void showMyForecastWeatherHistoryByCountry() {
        System.out.println("Podaj państwo");
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();
        for (int i = 0; i < ForecastManager.getForecastByCountry(country).size(); i++) {
            System.out.print(ForecastManager.getForecastByCountry(country).get(i));
        }
    }

    private static void showMyForecastWeatherHistoryByCity() {
        System.out.println("Podaj miasto");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        for (int i = 0; i < ForecastManager.getForecastByCity(city).size(); i++) {
            System.out.print(ForecastManager.getForecastByCity(city).get(i));
        }
    }

    private static void showSettingsMenu() {
        System.out.println("Aby wlaczyc/wylaczyc parametr wcisnij liczbę od 1 do 7 i zatwierdz ją klawiszem 'enter'.");
        //można to upiększyć za pomoca skroconego if'a ( warunek ? co jeśli prawda : co jeśli fałsz)
        //System.out.println("1. Temperatura - " + (BadSettings.isPrintAvarageTemp() ? " WŁ." : " WYŁ."));
        if (BadSettings.isPrintAvarageTemp() == true) {
            System.out.println("1. Temperatura, wlaczona.");
        } else {
            System.out.println("1. Temperatura, wylaczona.");
        }

        if (BadSettings.isPrintFeltTemp() == true) {
            System.out.println("2. Temperatura odczuwalna, wlaczona. ");
        } else {
            System.out.println("2. Temperatura odczuwalna, wylaczona.");
        }
        if (BadSettings.isPrintPressure() == true) {
            System.out.println("3. Cisnienie atmosferyczne, wlaczona. ");
        } else {
            System.out.println("3. Cisnienie atmosferyczne, wylaczona.");
        }
        if (BadSettings.isPrintHumidity() == true) {
            System.out.println("4. Wilgotnosc powietrza, wlaczona. ");
        } else {
            System.out.println("4. Wilgotnosc powietrza, wylaczona.");
        }
        if (BadSettings.isPrintWindSpeed() == true) {
            System.out.println("5. Predkosc wiatru, wlaczona. ");
        } else {
            System.out.println("5. Predkosc wiatru, wylaczona.");
        }
        if (BadSettings.isPrintWindDir() == true) {
            System.out.println("6. Kierunek wiatru, wlaczona. ");
        } else {
            System.out.println("6. Kierunek wiatru, wylaczona.");
        }
        System.out.println("7. Aby powrocic do 'Menu' glownego");


        Scanner scanner = new Scanner(System.in);
        int choiceshowSearchHistory = scanner.nextInt();

        switch (choiceshowSearchHistory) {

            case 1:
                BadSettings.switchPrintAvarageTemp();
                showSettingsMenu();
            case 2:
                BadSettings.switchPrintFeltTemp();
                showSettingsMenu();
            case 3:
                BadSettings.switchPrintPressure();
                showSettingsMenu();
            case 4:
                BadSettings.switchPrintHumidity();
                showSettingsMenu();
            case 5:
                BadSettings.switchPrintWindSpeed();
                showSettingsMenu();
            case 6:
                BadSettings.switchPrintWindDir();
                showSettingsMenu();
            case 7:
            default:
                showMainMenu();
        }

    }


}
