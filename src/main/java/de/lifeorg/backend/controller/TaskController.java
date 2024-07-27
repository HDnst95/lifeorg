package de.lifeorg.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.lifeorg.backend.model.Task;
import de.lifeorg.backend.model.User;
import de.lifeorg.backend.repository.TaskRepository;
import de.lifeorg.backend.repository.UserRepository;
import de.lifeorg.backend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}")
    public List<Task> getTasksByUser(@PathVariable String username) {
        return taskRepository.findByUser_Username(username);
    }

    @PostMapping("/{username}")
    public Task createTask(@PathVariable String username, @RequestBody Task task) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String username, @PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskRepository.findByIdAndUser_Username(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id + " and username " + username));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setDueDate(taskDetails.getDueDate());
        task.setCompleted(taskDetails.isCompleted());

        final Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String username, @PathVariable Long id) {
        Task task = taskRepository.findByIdAndUser_Username(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id + " and username " + username));

        taskRepository.delete(task);
        return ResponseEntity.noContent().build();
    }
}
