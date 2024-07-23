package de.lifeorg.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.lifeorg.backend.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
