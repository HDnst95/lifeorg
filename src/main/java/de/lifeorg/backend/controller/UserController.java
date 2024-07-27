package de.lifeorg.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.lifeorg.backend.model.User;
import de.lifeorg.backend.repository.UserRepository;
import de.lifeorg.backend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new ResourceNotFoundException("Username already exists");
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));
        if (!existingUser.getPassword().equals(user.getPassword())) {
            throw new ResourceNotFoundException("Invalid username or password");
        }
        return ResponseEntity.ok(existingUser);
    }
}
