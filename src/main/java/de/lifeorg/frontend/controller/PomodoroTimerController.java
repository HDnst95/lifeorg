package de.lifeorg.frontend.controller;

import de.lifeorg.backend.model.PomodoroSession;
import de.lifeorg.frontend.service.ApiService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class PomodoroTimerController {
    @FXML
    private Label timerLabel;

    private Timeline timeline;
    private int minutes = 25;
    private int seconds = 0;
    private boolean isRunning = false;
    private PomodoroSession currentSession;
    private ApiService apiService;

    public PomodoroTimerController() {
        this.apiService = new ApiService();
    }

    @FXML
    public void initialize() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML
    public void handleStart() {
        if (!isRunning) {
            isRunning = true;
            timeline.play();
            currentSession = apiService.startPomodoroSession("username"); // Username muss dynamisch sein
            showNotification("Pomodoro gestartet", "Die Pomodoro-Sitzung wurde gestartet.");
        }
    }

    @FXML
    public void handlePause() {
        if (isRunning) {
            isRunning = false;
            timeline.pause();
            showNotification("Pomodoro pausiert", "Die Pomodoro-Sitzung wurde pausiert.");
        }
    }

    @FXML
    public void handleReset() {
        isRunning = false;
        timeline.stop();
        minutes = 25;
        seconds = 0;
        updateTimerLabel();
        if (currentSession != null) {
            apiService.endPomodoroSession(currentSession.getId());
            showNotification("Pomodoro beendet", "Die Pomodoro-Sitzung wurde beendet.");
        }
    }

    private void updateTimer() {
        if (seconds == 0) {
            if (minutes == 0) {
                handleReset();
                return;
            }
            minutes--;
            seconds = 59;
        } else {
            seconds--;
        }
        updateTimerLabel();
    }

    private void updateTimerLabel() {
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void showNotification(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
