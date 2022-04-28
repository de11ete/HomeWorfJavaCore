package lesson6;

import lesson6.project.entity.Weather;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {
    private String insertWeather = "insert into weather (city, localdate, temperature) values (?, ?, ?)";
    private String getWeather = "select * from weather";
    private static final String DB_PATH = "jdbc:sqlite:gb.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Weather> weather;

    public boolean saveWeatherToDatabase(Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getLocalDate());
            saveWeather.setDouble(3, weather.getTemperature());

            return saveWeather.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Сохранение погоды в базу данных не выполнено");
    }

    public void saveWeatherToDatabase(List<Weather> weatherList) throws SQLException{
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            for (Weather weather : weatherList) {
                saveWeather.setString(1, weather.getCity());
                saveWeather.setString(2, weather.getLocalDate());
                saveWeather.setDouble(3, weather.getTemperature());
                saveWeather.addBatch();
            }
            saveWeather.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Weather> getSavedToDBWeather() {

        List<Weather> weatherList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getWeather);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.println(" ");
                System.out.print(resultSet.getString("city"));
                System.out.println(" ");
                System.out.print(resultSet.getString("localdate"));
                System.out.println(" ");
                System.out.print(resultSet.getString("weatherText"));
                System.out.println(" ");
                System.out.print(resultSet.getString("temperature"));
                System.out.println(" ");
                weather.add(new Weather(resultSet.getString("city"),
                        resultSet.getString("localdate"),
                        resultSet.getString("weatherText"),
                        resultSet.getString("temperature")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weather;
    }
}
