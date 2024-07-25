
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
   git clone https://github.com/deinbenutzername/lifeorg.git
   cd lifeorg
   ```

2. **Maven-Abhängigkeiten installieren:**

   ```sh
   mvn clean install
   ```

3. **Backend-Anwendung starten:**

   - Führe die `LifeOrgBackendApplication` Klasse aus:

   ```sh
   mvn spring-boot:run
   ```

4. **Frontend-Anwendung starten:**

   - Führe die `MainApp` Klasse aus:

   ```sh
   mvn javafx:run
   ```

## Nutzung

Nach dem Start der Anwendung wird ein Fenster angezeigt, das eine einfache To-Do-Liste enthält. Die Anwendung kommuniziert über eine RESTful API mit dem Backend, um Aufgaben zu erstellen, zu lesen, zu aktualisieren und zu löschen.

## Technologie-Stack

- **Backend:**
  - Spring Boot
  - Spring Data JPA
  - H2 Datenbank

- **Frontend:**
  - JavaFX

## Weitere Schritte

1. **Design verbessern:** Mach die Benutzeroberfläche optisch ansprechender und intuitiver.
2. **Benutzerverwaltung:** Implementiere eine Benutzeranmeldung und -registrierung.
3. **Kalenderfunktion:** Füge einen Kalender hinzu, um Aufgaben und Termine anzuzeigen.
4. **Erinnerungen:** Implementiere Benachrichtigungen für bevorstehende Aufgaben und Termine.
5. **Synchronisation:** Synchronisiere Daten mit externen Kalendern wie Google Calendar oder Outlook.

## Mitwirkende

- [Hendrik Dienst](https://github.com/HDnst95)

## Lizenz

Dieses Projekt ist lizenziert unter der MIT-Lizenz - siehe die [LICENSE](LICENSE) Datei für Details.
