package de.lifeorg.frontend.controller;

import de.lifeorg.backend.model.User;
import de.lifeorg.frontend.MainApp;
import de.lifeorg.frontend.service.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserLoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final ApiService apiService;

    private MainApp mainApp;

    public UserLoginController() {
        this.apiService = new ApiService();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User loggedInUser = apiService.loginUser(user);
        if (loggedInUser != null) {
            mainApp.showToDoListView(username);
        } else {
            // Fehlerbehandlung: Benutzername oder Passwort falsch
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            apiService.registerUser(user);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("User registered successfully. Please login.");
            alert.showAndWait();
        } catch (RuntimeException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Registration Error");
            alert.setHeaderText(null);
            if (e.getMessage().contains("Username already exists")) {
                alert.setContentText("The username is already taken. Please choose a different username.");
            } else {
                alert.setContentText("An error occurred during registration. Please try again.");
            }
            alert.showAndWait();
        }
    }
}
