
# LifeOrg

LifeOrg is a personal organization application that helps users manage their tasks and improve productivity through various features like Pomodoro timer, task management, and statistics tracking.

## Features

### 1. User Authentication
- Users can register and log in with a username and password.
- Validation to ensure only registered users can access the system.

### 2. Task Management (CRUD)
- Users can create, view, update, and delete tasks.
- Tasks are displayed in an easily manageable list.
- Tasks can be marked as completed.

### 3. Pomodoro Timer
- Integrated Pomodoro timer to help users work in productive intervals.
- Start, pause, and reset functionalities for the Pomodoro timer.

### 4. Statistics and Reports
- Display statistics and reports on completed tasks and Pomodoro sessions.
- Interactive PieCharts with hover effects and tooltips showing the value of each segment.

### 5. Gamification (Future)
- Reward systems, progress bars, or badges to motivate users.

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/HDnst95/lifeorg.git
   ```
2. Navigate to the project directory:
   ```sh
   cd lifeorg
   ```
3. Build the project using Maven:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## Recent Updates

### Updated Pomodoro and Task Functionalities
- Implemented Pomodoro Timer start, pause, and reset functionalities.
- Added task completion feature allowing users to mark tasks as completed.

### Fixed Issues with User Authentication
- Resolved issues related to user registration and login.
- Handled Optional<User> correctly to avoid type mismatch errors.

### Enhanced UI for Statistics and Task Completion
- Added hover effects and tooltips to PieChart segments in the statistics view.
- Improved layout and design for better user experience.

### Other Improvements
- Refactored code for better readability and maintenance.
- Updated FXML files to fix layout and style issues.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
