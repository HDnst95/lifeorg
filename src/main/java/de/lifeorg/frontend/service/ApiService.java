package de.lifeorg.frontend.service;

import de.lifeorg.backend.model.Task;
import de.lifeorg.backend.model.User;
import de.lifeorg.backend.model.PomodoroSession;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8080/api";
    private RestTemplate restTemplate;

    public ApiService() {
        this.restTemplate = new RestTemplate();
    }

    // User methods
    public void registerUser(User user) throws RuntimeException {
        try {
            restTemplate.postForObject(BASE_URL + "/users/register", user, User.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 409) {
                throw new RuntimeException("Username already exists. Please choose a different username.");
            }
            throw new RuntimeException("An error occurred during registration. Please try again.");
        }
    }

    public User loginUser(User user) {
        return restTemplate.postForObject(BASE_URL + "/users/login", user, User.class);
    }

    // Task methods
    public List<Task> getTasksByUser(String username) {
        ResponseEntity<List<Task>> response = restTemplate.exchange(BASE_URL + "/tasks/" + username, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Task>>() {
                });
        return response.getBody();
    }

    public Task createTask(String username, Task task) {
        return restTemplate.postForObject(BASE_URL + "/tasks/" + username, task, Task.class);
    }

    public Task updateTask(String username, Long id, Task task) {
        ResponseEntity<Task> response = restTemplate.exchange(
                BASE_URL + "/tasks/" + username + "/" + id, HttpMethod.PUT, 
                new org.springframework.http.HttpEntity<>(task), Task.class);
        return response.getBody();
    }

    public void deleteTask(String username, Long id) {
        restTemplate.delete(BASE_URL + "/tasks/" + username + "/" + id);
    }

    // PomodoroSession methods
    public PomodoroSession startPomodoroSession(String username) {
        return restTemplate.postForObject(BASE_URL + "/pomodoro/start/" + username, null, PomodoroSession.class);
    }

    public void endPomodoroSession(Long sessionId) {
        restTemplate.postForObject(BASE_URL + "/pomodoro/end/" + sessionId, null, Void.class);
    }

    public List<PomodoroSession> getPomodoroSessions(String username) {
        ResponseEntity<List<PomodoroSession>> response = restTemplate.exchange(BASE_URL + "/pomodoro/" + username, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<PomodoroSession>>() {
                });
        return response.getBody();
    }
}
