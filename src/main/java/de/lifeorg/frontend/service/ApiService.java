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
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Task>> response = restTemplate.exchange(BASE_URL + "/tasks", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Task>>() {
                });
        return response.getBody();
    }

    // Weitere Methoden für CRUD-Operationen
}
