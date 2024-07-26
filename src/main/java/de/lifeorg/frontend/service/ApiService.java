package de.lifeorg.frontend.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.lifeorg.backend.model.Task;
import de.lifeorg.backend.model.User;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8080/api";
    private final RestTemplate restTemplate;

    public ApiService() {
        this.restTemplate = new RestTemplate();
    }

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

    public List<Task> getTasksByUser(String username) {
        ResponseEntity<List<Task>> response = restTemplate.exchange(BASE_URL + "/tasks/" + username, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Task>>() {
                });
        return response.getBody();
    }

    public Task createTask(String username, Task task) {
        return restTemplate.postForObject(BASE_URL + "/tasks/" + username, task, Task.class);
    }

    public void updateTask(String username, Long id, Task task) {
        restTemplate.put(BASE_URL + "/tasks/" + username + "/" + id, task);
    }

    public void deleteTask(String username, Long id) {
        restTemplate.delete(BASE_URL + "/tasks/" + username + "/" + id);
    }

//    public List<Event> getAllEvents() {
//        ResponseEntity<List<Event>> response = restTemplate.exchange(
//            BASE_URL + "/events",
//            HttpMethod.GET,
//            null,
//            new ParameterizedTypeReference<List<Event>>() {}
//        );
//        return response.getBody();
//    }
//
//    public Event createEvent(Event event) {
//        return restTemplate.postForObject(BASE_URL + "/events", event, Event.class);
//    }
}
