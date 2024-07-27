package de.lifeorg.backend.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean completed; // Hinzugefügtes Attribut

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
        // Default-Konstruktor
    }

    public Task(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    /** @return the id */
    public Long getId() {
        return id;
    }

    /** @param id the id to set */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return the title */
    public String getTitle() {
        return title;
    }

    /** @param title the title to set */
    public void setTitle(String title) {
        this.title = title;
    }

    /** @return the description */
    public String getDescription() {
        return description;
    }

    /** @param description the description to set */
    public void setDescription(String description) {
        this.description = description;
    }

    /** @return the dueDate */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /** @param dueDate the dueDate to set */
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    /** @return the user */
    public User getUser() {
        return user;
    }

    /** @param user the user to set */
    public void setUser(User user) {
        this.user = user;
    }

    /** @return the completed status */
    public boolean isCompleted() {
        return completed;
    }

    /** @param completed the completed status to set */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return title + (completed ? " (Completed)" : "");
    }
}
