package badWeatherApp.ui.menu;

import badWeatherApp.dataOperations.dataFormater.DataFormater;
import badWeatherApp.databaseUtility.forecast.control.ForecastManager;
import badWeatherApp.databaseUtility.forecast.entity.Forecast;
import badWeatherApp.databaseUtility.forecast.entity.ForecastDTO;
import badWeatherApp.databaseUtility.forecast.responseToDtoConnector.ResponseToDtoConnector;
import badWeatherApp.databaseUtility.location.entity.LocationDTO;
import badWeatherApp.serverUtility.responseCollector.ResponseCollector;
import badWeatherApp.serverUtility.serverCommunication.OpenWeatherMapServer;
import badWeatherApp.serverUtility.serverCommunication.Requestable;
import badWeatherApp.serverUtility.serverCommunication.WeatherstackServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static badWeatherApp.ui.menu.BadSettings.*;

public class Menu {
    public static void showMenu() {
        System.out.println("Aby skorzystac z 'Menu' wcisnij liczbę od 1 do 4 i zatwierdz ją klawiszem 'enter'.");
        System.out.println("1. Ulubione lokalizacje");
        System.out.println("2. Sprawdz prognoze");
        System.out.println("3. Historia wyszukiwania");
        System.out.println("4. Ustawienia (W trakcie budowy)");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showFavorite();
            case 2:
                showForecastWeather();
            case 3:
                showSearchHistory();
            case 4:
                settings();
            default:
        }

    }

    private static void showFavorite() {
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
            case 2:
                removeFavorite();
            case 3:
                editFavorite();
            case 4:
                returnToTheMainMenu();
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

    private static void returnToTheMainMenu() {
        showMenu();
    }

    private static void showForecastWeather() {
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
            case 2:
                enterTheCity();
            case 3:
                enterGeographicCoordinates();
            case 4:
                returnToTheMainMenu();
            default:
                returnToTheMainMenu();
        }
    }

    private static void showFavoriteForecastWeather() {
        //todo
    }

    private static void enterTheCity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj miasto: ");
        String city = scanner.nextLine();

        List<Requestable> serverList = new ArrayList<>(List.of(
                new WeatherstackServer(),
                new OpenWeatherMapServer()
        ));

        ResponseCollector rc = new ResponseCollector(serverList, new LocationDTO(city));
        ForecastDTO forecast = ResponseToDtoConnector.createForecastDTOFromResponse(rc);

        System.out.println("-------------------------------");
        System.out.println(forecast.getForecastDate());
        System.out.println(forecast.getLocationDTO().getCity());
        System.out.println(DataFormater.displayTheAverageTemperature(forecast.getTemperature()));
        System.out.println(DataFormater.displayTheAverageFeltTemperature(forecast.getFeelsLike()));
        System.out.println(DataFormater.displayTheAverageWindSpeed(forecast.getWindSpeed()));
        System.out.println(DataFormater.displayWorldDirection(forecast.getWindDegree()));
        System.out.println(DataFormater.displayTheAverageHumidity(forecast.getHumidity()));
        System.out.println(DataFormater.displayTheAveragePressure(forecast.getPreassure()));
        System.out.println("-------------------------------");

        ForecastManager.addForecast(forecast);
    }

    private static void enterGeographicCoordinates() {
        //todo

    }

    private static void showSearchHistory() {
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
            case 2:
                showMyForecastWeatherHistoryByCity();
            case 3:
                showMyForecastWeatherHistoryByCountry();
            case 4:
                removeMyForecastWeatherHistorybyCity();
            case 5:
                removeMyForecastWeatherHistorybyID();
            case 6:
                returnToTheMainMenu();
            default:
                returnToTheMainMenu();
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

    private static void settings() {
        System.out.println("Aby wlaczyc/wylaczyc parametr wcisnij liczbę od 1 do 7 i zatwierdz ją klawiszem 'enter'.");
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
                switchPrintAvarageTemp();
            case 2:
                switchPrintFeltTemp();
            case 3:
                switchPrintPressure();
            case 4:
                switchPrintHumidity();
            case 5:
                switchPrintWindSpeed();
            case 6:
                switchPrintWindDir();
            default:
        }

    }


}
