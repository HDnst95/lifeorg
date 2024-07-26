package de.lifeorg.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.lifeorg.backend.model.Task;
import de.lifeorg.backend.model.User;
import de.lifeorg.backend.repository.TaskRepository;
import de.lifeorg.backend.repository.UserRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}")
    public List<Task> getTasksByUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return taskRepository.findByUser(user);
    }

    @PostMapping("/{username}")
    public Task createTask(@PathVariable String username, @RequestBody Task task) {
        User user = userRepository.findByUsername(username);
        task.setUser(user);
        return taskRepository.save(task);
    }

    @PutMapping("/{username}/{id}")
    public Task updateTask(@PathVariable String username, @PathVariable Long id, @RequestBody Task task) {
        User user = userRepository.findByUsername(username);
        task.setUser(user);
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/{username}/{id}")
    public void deleteTask(@PathVariable String username, @PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
