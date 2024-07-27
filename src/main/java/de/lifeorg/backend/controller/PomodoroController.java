package de.lifeorg.backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.lifeorg.backend.model.PomodoroSession;
import de.lifeorg.backend.model.User;
import de.lifeorg.backend.repository.PomodoroSessionRepository;
import de.lifeorg.backend.repository.UserRepository;
import de.lifeorg.backend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/pomodoro")
public class PomodoroController {

    @Autowired
    private PomodoroSessionRepository pomodoroSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/start/{username}")
    public ResponseEntity<PomodoroSession> startSession(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        PomodoroSession session = new PomodoroSession();
        session.setUser(user);
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(null); // Session is active
        PomodoroSession savedSession = pomodoroSessionRepository.save(session);
        return ResponseEntity.ok(savedSession);
    }

    @PostMapping("/end/{sessionId}")
    public ResponseEntity<Void> endSession(@PathVariable Long sessionId) {
        PomodoroSession session = pomodoroSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found"));
        session.setEndTime(LocalDateTime.now());
        pomodoroSessionRepository.save(session);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<PomodoroSession>> getSessions(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<PomodoroSession> sessions = pomodoroSessionRepository.findByUser(user);
        return ResponseEntity.ok(sessions);
    }
}
