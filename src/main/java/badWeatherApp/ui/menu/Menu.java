package badWeatherApp.ui.menu;

import java.util.Scanner;

public class Menu {
    public void showMenu() {
        System.out.println("Aby skorzystac z 'Menu' wcisnij liczbę od 1 do 4 i zatwierdz ją klawiszem 'enter'.");
        System.out.println("1. Ulubione");
        System.out.println("2. Sprawdz prognoze");
        System.out.println("3. Historia wyszukiwania");
        System.out.println("4. Ustawienia");

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
            case 2:
                removeFavorite();
            case 3:
                editFavorite();
            case 4:
                returnToTheMainMenu();
            default:
        }

    }

    private void addFavorite() {
        //todo

    }

    private void removeFavorite() {
        //todo

    }

    private void editFavorite() {
        //todo

    }

    private void returnToTheMainMenu() {
        //todo

    }

    private void showForecastWeather() {
        System.out.println("Aby wyszukać prognoze pogody wcisnij liczbę od 1 do 4 i zatwierdz ją klawiszem 'enter'.");
        System.out.println("1. Wyświetl prognoze dla wszystkich ulubionych");
        System.out.println("2. Podaj miasto");
        System.out.println("3. Podaj panstwo");
        System.out.println("4. Podaj wspolrzedne geograficzne");
        System.out.println("5. Aby powrocic do 'Menu' glownego");

        Scanner scanner = new Scanner(System.in);
        int choiceShowFavorite = scanner.nextInt();

        switch (choiceShowFavorite) {
            case 1:
                showFavoriteForecastWeather();
            case 2:
                enterTheCity();
            case 3:
                enterTheCountry();
            case 4:
                enterGeographicCoordinates();
            case 5:
                returnToTheMainMenu();
            default:
        }
    }

    private void showFavoriteForecastWeather() {
        //todo
    }

    private void enterTheCity() {
        //todo

    }

    private void enterTheCountry() {
        //todo

    }

    private void enterGeographicCoordinates() {
        //todo

    }

    private void showSearchHistory() {
        System.out.println("Aby skorzystac z 'Menu' wcisnij liczbę od 1 do 3 i zatwierdz ją klawiszem 'enter'.");
        System.out.println("1. Pokaz historie");
        System.out.println("2. Wyczysc historie");
        System.out.println("3. Aby powrocic do 'Menu' glownego");

        Scanner scanner = new Scanner(System.in);
        int choiceshowSearchHistory = scanner.nextInt();

        switch (choiceshowSearchHistory) {

            case 1:
                showMyForecastWeatherHistory();
            case 2:
                removeMyForecastWeatherHistory();
            case 3:
                returnToTheMainMenu();
            default:
        }

    }

    private void showMyForecastWeatherHistory() {
        //todo

    }

    private void removeMyForecastWeatherHistory() {
        //todo
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
