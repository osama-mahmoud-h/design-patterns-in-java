package behvioral.observer;

public class WeatherStation {
    public static void main(String[] args) {
        // Create WeatherData (subject)
        WeatherData weatherData = new WeatherData();

        // Create observers and register them
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        weatherData.registerObserver(currentDisplay);
        weatherData.registerObserver(statisticsDisplay);
        weatherData.registerObserver(forecastDisplay);

        // Simulate new weather data
        weatherData.setMeasurements(80, 65, 30.4f);
        System.out.println("-----------------------------------------------");

        weatherData.setMeasurements(82, 70, 29.2f);
        System.out.println("-----------------------------------------------");

        weatherData.setMeasurements(78, 90, 29.2f);
        System.out.println("-----------------------------------------------");

    }
}
