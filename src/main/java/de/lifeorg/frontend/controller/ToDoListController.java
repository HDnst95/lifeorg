package de.lifeorg.frontend.controller;

import de.lifeorg.frontend.MainApp;
import de.lifeorg.frontend.service.ApiService;
import de.lifeorg.backend.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class ToDoListController {

    @FXML
    private TextField taskTitleField;
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private TextField pomodoroTimerField;

    private MainApp mainApp;
    private ApiService apiService;
    private ObservableList<Task> taskList;

    public ToDoListController() {
        this.apiService = new ApiService();
    }

    @FXML
    private void initialize() {
        taskList = FXCollections.observableArrayList();
        taskListView.setItems(taskList);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        loadTasks();
    }

    private void loadTasks() {
        List<Task> tasks = apiService.getTasksByUser(mainApp.getCurrentUsername());
        taskList.setAll(tasks);
    }

    @FXML
    private void handleAddTask() {
        String title = taskTitleField.getText();
        if (title.isEmpty()) {
            showAlert("Error", "Task title cannot be empty.");
            return;
        }
        Task newTask = new Task(title, false); // Verwenden des neuen Konstruktors
        taskList.add(newTask);
        apiService.createTask(mainApp.getCurrentUsername(), newTask);
        taskTitleField.clear();
    }

    @FXML
    private void handleEditTask() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert("Error", "No task selected.");
            return;
        }
        String newTitle = taskTitleField.getText();
        if (newTitle.isEmpty()) {
            showAlert("Error", "Task title cannot be empty.");
            return;
        }
        selectedTask.setTitle(newTitle);
        apiService.updateTask(mainApp.getCurrentUsername(), selectedTask.getId(), selectedTask);
        taskListView.refresh();
        taskTitleField.clear();
    }

    @FXML
    private void handleDeleteTask() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert("Error", "No task selected.");
            return;
        }
        taskList.remove(selectedTask);
        apiService.deleteTask(mainApp.getCurrentUsername(), selectedTask.getId());
    }

    @FXML
    private void handleCompleteTask() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert("Error", "No task selected.");
            return;
        }
        selectedTask.setCompleted(true);
        apiService.updateTask(mainApp.getCurrentUsername(), selectedTask.getId(), selectedTask);
        taskListView.refresh();
    }

    @FXML
    private void handleShowStatistics() {
        mainApp.showStatisticsView();
    }

    // Pomodoro Timer Handlers
    @FXML
    private void handleStartPomodoro() {
        // Logik zum Starten des Pomodoro-Timers
    }

    @FXML
    private void handlePausePomodoro() {
        // Logik zum Pausieren des Pomodoro-Timers
    }

    @FXML
    private void handleResetPomodoro() {
        // Logik zum Zurücksetzen des Pomodoro-Timers
        pomodoroTimerField.setText("25:00"); // Beispiel für das Zurücksetzen des Timers auf 25:00
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
