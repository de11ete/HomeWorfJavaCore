package lesson6;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson6.project.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/

    private static final String PROTOCOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "UcXX9xTHR6ZeefPImvDAw9hKRwTKf66E";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DatabaseRepository databaseRepository = new DatabaseRepository();


    @Override
    public void getWeather(String selectedCity, Period period) throws IOException {
        switch(period) {
            case FIVE_DAYS:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response fiveDaysForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = fiveDaysForecastResponse.body().string();
                System.out.println(weatherResponse);

                //databaseRepository.saveWeatherToDatabase(....);

                break;
        }
    }

    private String detectCityKey(String selectedCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .get()
                        .addHeader("accept", "application/json")
                        .build();

        Response cityResponse = okHttpClient.newCall(request).execute();
        String weatherResponse = cityResponse.body().string();

        String cityKey = objectMapper.readTree(weatherResponse).get(0).at("/Key").asText();

        return cityKey;
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return databaseRepository.getSavedToDBWeather();
    }

    public static void main(String[] args) throws IOException {
        UserInterfaceView userInterfaceView = new UserInterfaceView();
        userInterfaceView.runInterface();
        ObjectMapper objectMapper = new ObjectMapper();

        AccuweatherModel accuweatherModel = new AccuweatherModel();
        accuweatherModel.getWeather("Petersburg", Period.FIVE_DAYS);
    }
}
