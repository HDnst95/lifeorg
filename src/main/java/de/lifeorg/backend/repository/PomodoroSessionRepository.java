package de.lifeorg.backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import de.lifeorg.backend.model.PomodoroSession;
import de.lifeorg.backend.model.User;

public interface PomodoroSessionRepository extends JpaRepository<PomodoroSession, Long> {
    List<PomodoroSession> findByUser(User user);
    Optional<PomodoroSession> findById(Long id);
}
