# LifeOrganization

LifeOrganization ist eine Java-Anwendung, die Menschen mit ADHS hilft, ihren Alltag zu organisieren. Diese Anwendung bietet Funktionen wie eine To-Do-Liste, Kalender und Gamification-Elemente, um die Produktivität und Organisation zu verbessern.

## Projektstruktur

### Backend

Das Backend wurde mit Spring Boot erstellt und bietet eine RESTful API für die Verwaltung von Aufgaben und Benutzern.

- **Main Application Class:** `LifeOrgBackendApplication`
- **Modelle:**
  - `User`
  - `Task`
- **Repositories:**
  - `UserRepository`
  - `TaskRepository`
- **Controller:**
  - `TaskController`
- **Exception Handling:**
  - `ResourceNotFoundException`

### Frontend

Das Frontend wurde mit JavaFX erstellt und bietet eine Benutzeroberfläche zur Anzeige und Verwaltung der To-Do-Liste.

- **Main Application Class:** `MainApp`
- **Controller:**
  - `ToDoListController`
- **Service:**
  - `ApiService`
- **FXML Layout:**
  - `ToDoList.fxml`

## Installation

### Voraussetzungen

- Java 11
- Maven
- Git

### Schritte

1. **Repository klonen:**

   ```sh
   git cl
