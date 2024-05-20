package zw.co.els.englishlearningsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import zw.co.els.englishlearningsystem.model.User;
import zw.co.els.englishlearningsystem.utils.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField idNumberField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField ageField;
    @FXML
    private ChoiceBox<String> englishLevelChoiceBox;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    protected void handleRegister() {
        String fullName = fullNameField.getText();
        String idNumber = idNumberField.getText();
        String phoneNumber = phoneNumberField.getText();
        String gender = genderField.getText();
        String age = ageField.getText();
        String englishLevel = englishLevelChoiceBox.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (fullName.isEmpty() ||
                idNumber.isEmpty() ||
                phoneNumber.isEmpty() ||
                gender.isEmpty() ||
                age.isEmpty() ||
                englishLevel==null || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all fields");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Password Error!", "Passwords do not match");
            return;
        }
        User user = new User(fullName, idNumber, phoneNumber, gender, age, englishLevel, username, password);


        if (registerUser(user)) {
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful!", "User registered successfully");
            redirectToLogin();
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Failed!", "Failed to register user. Please try again.");
        }

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean registerUser(User user) {
        String insertQuery = "INSERT INTO users (full_name, id_number, phone_number, gender, age, english_level, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, user.fullName());
            preparedStatement.setString(2, user.idNumber());
            preparedStatement.setString(3, user.phoneNumber());
            preparedStatement.setString(4, user.gender());
            preparedStatement.setString(5, user.age());
            preparedStatement.setString(6, user.englishLevel());
            preparedStatement.setString(7, user.username());
            preparedStatement.setString(8, user.password());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void redirectToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            Pane root = loader.load();
            Stage stage = (Stage) fullNameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginRedirect() {
        redirectToLogin();
    }
}


