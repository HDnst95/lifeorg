package de.lifeorg.frontend.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import de.lifeorg.backend.model.Task;

public class ApiService {
    private static final String BASE_URL = "http://localhost:8080/api";

    public List<Task> getAllTasks() {
        var restTemplate = new RestTemplate();
        ResponseEntity<List<Task>> response = restTemplate.exchange(BASE_URL + "/tasks", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Task>>() {
                });
        return response.getBody();
    }

    public Task createTask(Task task) {
        var restTemplate = new RestTemplate();
        return restTemplate.postForObject(BASE_URL + "/tasks", task, Task.class);
    }

    public Task updateTask(Long id, Task task) {
        var restTemplate = new RestTemplate();
        restTemplate.put(BASE_URL + "/tasks/" + id, task);
        return task;
    }

    public void deleteTask(Long id) {
        var restTemplate = new RestTemplate();
        restTemplate.delete(BASE_URL + "/tasks/" + id);
    }

    // Weitere Methoden für CRUD-Operationen
}
