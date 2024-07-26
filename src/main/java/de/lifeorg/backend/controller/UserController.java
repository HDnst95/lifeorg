package de.lifeorg.backend.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.lifeorg.backend.model.User;
import de.lifeorg.backend.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user, HttpServletResponse response) throws IOException {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            response.sendError(HttpStatus.CONFLICT.value(),
                    "Username already exists. Please choose a different username.");
            return null;
        }
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return existingUser;
        }
        return null; // oder throw new RuntimeException("Invalid login credentials");
    }
}
