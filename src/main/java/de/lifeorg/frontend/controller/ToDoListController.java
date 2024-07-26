package de.lifeorg.frontend.controller;

import java.util.List;

import de.lifeorg.backend.model.Task;
import de.lifeorg.frontend.service.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ToDoListController {

    @FXML
    private ListView<String> taskListView;

    @FXML
    private TextField taskTitleField;

    private final ApiService apiService;
    private String currentUsername;

    public ToDoListController() {
        this.apiService = new ApiService();
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
        loadTasks();
    }

    @FXML
    public void initialize() {
        // Tasks are loaded when username is set
    }

    private void loadTasks() {
        List<Task> tasks = apiService.getTasksByUser(currentUsername);
        taskListView.getItems().clear();
        for (Task task : tasks) {
            taskListView.getItems().add(task.getTitle());
        }
    }

    @FXML
    private void handleAddTask() {
        String title = taskTitleField.getText();
        if (title != null && !title.trim().isEmpty()) {
            Task newTask = new Task();
            newTask.setTitle(title);
            apiService.createTask(currentUsername, newTask);
            taskListView.getItems().add(title);
            taskTitleField.clear();
        }
    }

    @FXML
    private void handleEditTask() {
        String selectedTitle = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTitle != null) {
            Task selectedTask = apiService.getTasksByUser(currentUsername).stream()
                    .filter(task -> task.getTitle().equals(selectedTitle)).findFirst().orElse(null);
            if (selectedTask != null) {
                selectedTask.setTitle(taskTitleField.getText());
                apiService.updateTask(currentUsername, selectedTask.getId(), selectedTask);
                taskListView.getItems().set(taskListView.getSelectionModel().getSelectedIndex(),
                        taskTitleField.getText());
                taskTitleField.clear();
            }
        }
    }

    @FXML
    private void handleDeleteTask() {
        String selectedTitle = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTitle != null) {
            Task selectedTask = apiService.getTasksByUser(currentUsername).stream()
                    .filter(task -> task.getTitle().equals(selectedTitle)).findFirst().orElse(null);
            if (selectedTask != null) {
                apiService.deleteTask(currentUsername, selectedTask.getId());
                taskListView.getItems().remove(selectedTitle);
                taskTitleField.clear();
            }
        }
    }

    @FXML
    private void handleTaskSelection() {
        String selectedTitle = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTitle != null) {
            taskTitleField.setText(selectedTitle);
        }
    }
}
