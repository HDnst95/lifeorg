package de.lifeorg.frontend.controller;

import de.lifeorg.frontend.service.ApiService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StatisticsController {

    @FXML
    private PieChart tasksChart;

    @FXML
    private PieChart pomodoroChart;

    @FXML
    private Label completedTasksLabel;

    @FXML
    private Label pendingTasksLabel;

    @FXML
    private Label completedPomodorosLabel;

    @FXML
    private Label pendingPomodorosLabel;

    private ApiService apiService;
    private int completedTasks;
    private int pendingTasks;
    private int completedPomodoros;
    private int pendingPomodoros;

    public StatisticsController() {
        this.apiService = new ApiService();
    }

    @FXML
    private void initialize() {
        loadTaskStatistics();
        loadPomodoroStatistics();
    }

    private void loadTaskStatistics() {
        // Dummy data for task statistics
        completedTasks = 30;
        pendingTasks = 2;
        
        PieChart.Data completedTasksData = new PieChart.Data("Erledigte Aufgaben", completedTasks);
        PieChart.Data pendingTasksData = new PieChart.Data("Offene Aufgaben", pendingTasks);

        tasksChart.getData().addAll(completedTasksData, pendingTasksData);

        completedTasksLabel.setText("Erledigte Aufgaben: " + completedTasks);
        pendingTasksLabel.setText("Offene Aufgaben: " + pendingTasks);

        addHoverEffect(tasksChart);
    }

    private void loadPomodoroStatistics() {
        // Dummy data for pomodoro statistics
        completedPomodoros = 10;
        pendingPomodoros = 5;
        
        PieChart.Data completedPomodorosData = new PieChart.Data("Erledigte Pomodoros", completedPomodoros);
        PieChart.Data pendingPomodorosData = new PieChart.Data("Offene Pomodoros", pendingPomodoros);

        pomodoroChart.getData().addAll(completedPomodorosData, pendingPomodorosData);

        completedPomodorosLabel.setText("Erledigte Pomodoros: " + completedPomodoros);
        pendingPomodorosLabel.setText("Offene Pomodoros: " + pendingPomodoros);

        addHoverEffect(pomodoroChart);
    }

    private void addHoverEffect(PieChart chart) {
        for (PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                data.getNode().setEffect(new javafx.scene.effect.Glow(0.5));
                Text dataText = new Text(data.getName() + ": " + (int) data.getPieValue());
                dataText.setFill(Color.BLACK);
                chart.setTitle(dataText.getText());
            });

            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                data.getNode().setEffect(null);
                chart.setTitle(chart.getTitle().split(":")[0]); // Zurücksetzen des Titels
            });
        }
    }

    public void updateTaskStatistics(int completed, int pending) {
        completedTasks = completed;
        pendingTasks = pending;

        completedTasksLabel.setText("Erledigte Aufgaben: " + completedTasks);
        pendingTasksLabel.setText("Offene Aufgaben: " + pendingTasks);

        tasksChart.getData().clear();
        tasksChart.getData().addAll(
            new PieChart.Data("Erledigte Aufgaben", completedTasks),
            new PieChart.Data("Offene Aufgaben", pendingTasks)
        );

        addHoverEffect(tasksChart);
    }

    public void updatePomodoroStatistics(int completed, int pending) {
        completedPomodoros = completed;
        pendingPomodoros = pending;

        completedPomodorosLabel.setText("Erledigte Pomodoros: " + completedPomodoros);
        pendingPomodorosLabel.setText("Offene Pomodoros: " + pendingPomodoros);

        pomodoroChart.getData().clear();
        pomodoroChart.getData().addAll(
            new PieChart.Data("Erledigte Pomodoros", completedPomodoros),
            new PieChart.Data("Offene Pomodoros", pendingPomodoros)
        );

        addHoverEffect(pomodoroChart);
    }
}
