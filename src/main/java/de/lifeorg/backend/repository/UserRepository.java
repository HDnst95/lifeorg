package de.lifeorg.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.lifeorg.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
