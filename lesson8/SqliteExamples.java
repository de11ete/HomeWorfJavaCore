package lesson8;

import java.sql.*;

public class SqliteExamples {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:gb.db");
            ResultSet resultSet;
            try (Statement statement = connection.createStatement()) {

                int count = statement.executeUpdate("update faculties set name = 'Test1' where id = 1");
                System.out.println("Строк изменено: " + count);

                resultSet = statement.executeQuery("select * from students");

                while (resultSet.next()) {
                    System.out.print(resultSet.getInt("id") + " ");
                    System.out.print(resultSet.getString("name") + " ");
                    System.out.println(resultSet.getInt("faculty_id") + "\n");
                }
/*
            Long time = System.currentTimeMillis();
            for(int i = 1; i < 1000; i++){
                statement.executeUpdate(String.format("insert into students (name, score. faculty_id ) values ('%s', %d, %d)",
                        "student"+i, i, i));
            }
            System.out.println("Время вставки 1000 строк = " + (System.currentTimeMillis() - time));


            time = System.currentTimeMillis();
            connection.setAutoCommit(false);
            for(int i = 500; i < 1000; i++){
                statement.executeUpdate(String.format("insert into students (name, score. faculty_id ) values ('%s', %d, %d)",
                        "student"+i, i, i));
            }
            connection.commit();
            System.out.println("Время вставки 1000 строк без автокоммита = " + (System.currentTimeMillis() - time));
*/

                PreparedStatement preparedStatement = connection.prepareStatement
                        ("insert into students (name, score, faculty_id) values (?, ?, ?)");

                connection.setAutoCommit(false);
                preparedStatement.setString(1, "Vasya");
                preparedStatement.setInt(2, 50);
                preparedStatement.setInt(3, 3);
                preparedStatement.addBatch();
                preparedStatement.setString(1, "Petr");
                preparedStatement.setInt(2, 85);
                preparedStatement.setInt(3, 4);
                preparedStatement.addBatch();
                preparedStatement.setString(1, "Olya");
                preparedStatement.setInt(2, 32);
                preparedStatement.setInt(3, 5);
                preparedStatement.addBatch();
                preparedStatement.executeBatch();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
