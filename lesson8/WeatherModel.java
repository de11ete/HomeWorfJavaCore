package lesson6;

import lesson6.project.entity.Weather;

import java.io.IOException;
import java.util.List;

public interface WeatherModel {
    void getWeather(String selectedCity, Period period) throws IOException;

    List<Weather> getSavedToDBWeather();
}
