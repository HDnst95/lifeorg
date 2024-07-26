package de.lifeorg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.lifeorg.backend.model.Task;
import de.lifeorg.backend.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
