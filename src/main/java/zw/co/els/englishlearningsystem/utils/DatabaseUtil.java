package zw.co.els.englishlearningsystem.utils;

import org.springframework.stereotype.Service;
import zw.co.els.englishlearningsystem.model.User;

import java.sql.*;

@Service
public class DatabaseUtil {

    private static final Config config = Config.getInstance();
    public static String URL = config.getProperty("db.url");
    public static String USER = config.getProperty("db.user");
    public static String PASSWORD = config.getProperty("db.password");
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        System.out.println("trying to validate user " + username + " " + password);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("failed to validate user " + username + " " + password);
            e.printStackTrace();
            return false;
        }
    }

    public static User getValidatedUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("full_name"),
                        resultSet.getString("id_number"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("gender"),
                        resultSet.getString("age"),
                        resultSet.getString("english_level"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
