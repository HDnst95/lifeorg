package de.lifeorg.frontend.controller;

import java.util.List;

import de.lifeorg.backend.model.Task;
import de.lifeorg.frontend.service.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ToDoListController {

    @FXML
    private ListView<String> taskListView;

    private ApiService apiService;

    public ToDoListController() {
        this.apiService = new ApiService();
    }

    @FXML
    public void initialize() {
        loadTasks();
    }

    private void loadTasks() {
        List<Task> tasks = apiService.getAllTasks();
        for (Task task : tasks) {
            taskListView.getItems().add(task.getTitle());
        }
    }
}
