package de.lifeorg.backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import de.lifeorg.backend.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser_Username(String username);
    Optional<Task> findByIdAndUser_Username(Long id, String username);
}
