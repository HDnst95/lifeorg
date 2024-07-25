package de.lifeorg.frontend.controller;

import de.lifeorg.backend.model.Task;
import de.lifeorg.frontend.service.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ToDoListController {

    private ApiService apiService;
    private Task selectedTask;

    @FXML
    private ListView<String> taskListView;
    @FXML
    private TextField taskTitleField;

    public ToDoListController() {
        this.apiService = new ApiService();
    }

    @FXML
    public void initialize() {
        loadTasks();
    }

    private void loadTasks() {
        var tasks = apiService.getAllTasks();
        for (Task task : tasks) {
            taskListView.getItems().add(task.getTitle());
        }
    }

    @FXML
    private void handleTaskSelection() {
        var selectedTitle = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTitle != null) {
            // Logik zum Finden der Aufgabe basierend auf dem Titel
            // Annahme: Titel ist einzigartig
            selectedTask = apiService.getAllTasks().stream().filter(task -> task.getTitle().equals(selectedTitle))
                    .findFirst().orElse(null);
            if (selectedTask != null) {
                taskTitleField.setText(selectedTask.getTitle());
            }
        }
    }

    @FXML
    private void handleEditTask() {
        if (selectedTask != null) {
            var updatedTitle = taskTitleField.getText();
            if (updatedTitle != null && !updatedTitle.trim().isEmpty()) {
                selectedTask.setTitle(updatedTitle);
                apiService.updateTask(selectedTask.getId(), selectedTask);
                taskListView.getItems().set(taskListView.getSelectionModel().getSelectedIndex(), updatedTitle);
                taskTitleField.clear();
            }
        }
    }

    @FXML
    private void handleAddTask() {
        var title = taskTitleField.getText();
        if (title != null && !title.trim().isEmpty()) {
            var newTask = new Task();
            newTask.setTitle(title);
            apiService.createTask(newTask);
            taskListView.getItems().add(title);
            taskTitleField.clear();
        }
    }

    @FXML
    private void handleDeleteTask() {
        if (selectedTask != null) {
            apiService.deleteTask(selectedTask.getId());
            taskListView.getItems().remove(taskListView.getSelectionModel().getSelectedIndex());
            taskTitleField.clear();
            selectedTask = null;
        }
    }

}
