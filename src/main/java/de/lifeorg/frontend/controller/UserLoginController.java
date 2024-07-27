package de.lifeorg.frontend.controller;

import de.lifeorg.frontend.MainApp;
import de.lifeorg.frontend.service.ApiService;
import de.lifeorg.backend.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserLoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private MainApp mainApp;
    private ApiService apiService;

    public UserLoginController() {
        this.apiService = new ApiService();
    }

    @FXML
    private void initialize() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            User user = new User(username, password);
            User loggedInUser = apiService.loginUser(user);
            if (loggedInUser != null) {
                mainApp.showToDoListView(username);
                showNotification("Login erfolgreich", "Willkommen, " + username + "!");
            } else {
                showNotification("Login fehlgeschlagen", "Ungültiger Benutzername oder Passwort.");
            }
        } catch (Exception e) {
            showNotification("Login fehlgeschlagen", e.getMessage());
        }
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            User user = new User(username, password);
            apiService.registerUser(user);
            showNotification("Registrierung erfolgreich", "Benutzer wurde erfolgreich registriert. Bitte melden Sie sich an.");
        } catch (Exception e) {
            showNotification("Registrierung fehlgeschlagen", e.getMessage());
        }
    }

    private void showNotification(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
