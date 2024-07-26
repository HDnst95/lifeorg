package de.lifeorg.frontend;

import de.lifeorg.frontend.controller.ToDoListController;
import de.lifeorg.frontend.controller.UserLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LifeOrganization");

        showLoginView();
    }

    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/de/lifeorg/frontend/UserLogin.fxml"));
            GridPane loginLayout = (GridPane) loader.load(); // Correctly casting to GridPane

            // Set the main app reference into the controller
            UserLoginController controller = loader.getController();
            controller.setMainApp(this);

            Scene scene = new Scene(loginLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToDoListView(String username) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/de/lifeorg/frontend/ToDoList.fxml"));
            BorderPane toDoListLayout = (BorderPane) loader.load();

            // Set the username into the ToDoListController
            ToDoListController controller = loader.getController();
            controller.setCurrentUsername(username);

            Scene scene = new Scene(toDoListLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
