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
    public void showMenu() {
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
                returnToTheMainMenu();
        }

    }

    private void showFavorite() {
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
                showFavorite();
            case 2:
                removeFavorite();
                showFavorite();
            case 3:
                editFavorite();
                showFavorite();
            case 4:
                returnToTheMainMenu();
            default:
        }

    }

    private void addFavorite() {

//        LocationManager.addLocation();

    }

    private void removeFavorite() {
        //todo

    }

    private void editFavorite() {
        //todo

    }

    private void returnToTheMainMenu() {
        showMenu();
    }

    private void showForecastWeather() {
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
                showForecastWeather();
            case 2:
                enterTheCity();
                showForecastWeather();
            case 3:
                enterGeographicCoordinates();
                showForecastWeather();
            case 4:
            default:
                returnToTheMainMenu();
        }
    }

    private void showFavoriteForecastWeather() {
        //todo
    }

    private void enterTheCity() {
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

    private void enterGeographicCoordinates() {
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

    private void showSearchHistory() {
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
                showSearchHistory();
            case 2:
                showMyForecastWeatherHistoryByCity();
                showSearchHistory();
            case 3:
                showMyForecastWeatherHistoryByCountry();
                showSearchHistory();
            case 4:
                removeMyForecastWeatherHistorybyCity();
                showSearchHistory();
            case 5:
                removeMyForecastWeatherHistorybyID();
                showSearchHistory();
            case 6:
            default:
                returnToTheMainMenu();
        }

    }

    private void removeMyForecastWeatherHistorybyID() {
        System.out.println("Podaj id prognozy, którą chcesz usunąć");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        ForecastManager.removeForecastbyID(id);

    }

    private void removeMyForecastWeatherHistorybyCity() {
        System.out.println("Podaj miasto, dla którego historia ma zostać usunięta");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        ForecastManager.removeForecastbyCity(city);
    }

    private void showMyForecastWeatherHistoryByCountry() {
        System.out.println("Podaj państwo");
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();
        for (int i = 0; i < ForecastManager.getForecastByCountry(country).size(); i++) {
            System.out.print(ForecastManager.getForecastByCountry(country).get(i));
        }
    }

    private void showMyForecastWeatherHistoryByCity() {
        System.out.println("Podaj miasto");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        for (int i = 0; i < ForecastManager.getForecastByCity(city).size(); i++) {
            System.out.print(ForecastManager.getForecastByCity(city).get(i));
        }
    }

    private void settings() {
        System.out.println("Aby odznaczyc pokaz / nie pokazuj widocznych parametrow wcisnij liczbę od 1 do 7 i zatwierdz ją klawiszem 'enter'.");

        System.out.println("1. Temperatura");

        System.out.println("2. Temperatura odczuwalna");

        System.out.println("3. Cisnienie atmosferyczne");

        System.out.println("4. Wilgotnosc powietrza");

        System.out.println("5. Predkosc wiatru");

        System.out.println("6. Kierunek wiatru");

        System.out.println("7. Aby powrocic do 'Menu' glownego");


        Scanner scanner = new Scanner(System.in);
        int choiceshowSearchHistory = scanner.nextInt();

        switch (choiceshowSearchHistory) {

            case 1:
                avarageTemperature();
            case 2:
                feltTemperature();
            case 3:
                pressure();
            case 4:
                humidity();
            case 5:
                windSpeed();
            case 6:
                windDir();
            default:
        }

    }

    private void avarageTemperature() {
        //todo

    }

    private void feltTemperature() {
        //todo
    }

    private void pressure() {
        //todo

    }

    private void humidity() {
        //todo
    }

    private void windSpeed() {
        //todo

    }

    private void windDir() {
        //todo
    }
}
