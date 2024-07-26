package de.lifeorg.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.lifeorg.backend.model.PomodoroSession;
import de.lifeorg.backend.model.User;
import de.lifeorg.backend.repository.PomodoroSessionRepository;
import de.lifeorg.backend.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pomodoro")
public class PomodoroController {
    @Autowired
    private PomodoroSessionRepository pomodoroSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/start/{username}")
    public PomodoroSession startSession(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        PomodoroSession session = new PomodoroSession();
        session.setUser(user);
        session.setStartTime(LocalDateTime.now());
        session.setComplete(false);
        return pomodoroSessionRepository.save(session);
    }

    @PostMapping("/end/{sessionId}")
    public PomodoroSession endSession(@PathVariable Long sessionId) {
        PomodoroSession session = pomodoroSessionRepository.findById(sessionId).orElseThrow();
        session.setEndTime(LocalDateTime.now());
        session.setComplete(true);
        return pomodoroSessionRepository.save(session);
    }

    @GetMapping("/{username}")
    public List<PomodoroSession> getSessions(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return pomodoroSessionRepository.findByUser(user);
    }
}
