package com.example.nba.GUI;
import com.example.nba.controller.ManagerController;
import com.example.nba.model.Manager;
import com.example.nba.model.NBATeam;
import com.example.nba.repo.Repo;
import com.example.nba.repo.RepoMem;
import com.example.nba.service.PlayerService;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.nba.controller.PlayerController;
import com.example.nba.model.NBAPlayer;
import java.util.List;

public class ManagerMenuGUI extends Application {
    private ManagerController managerController;

    public ManagerMenuGUI(ManagerController managerController) {
        this.managerController = managerController;
    }
    @Override
    public void start(Stage stage) {
        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);

        Label title = new Label("Manager Menu");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button addManagerButton = new Button("Add Manager");
        Button getManagerByIdButton = new Button("Get Manager by ID");
        Button getManagerByNameButton = new Button("Get Manager by Name");
        Button deleteManagerButton = new Button("Delete Manager");
        addManagerButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        getManagerByIdButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        getManagerByNameButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        deleteManagerButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        addManagerButton.setOnAction(e -> openAddManager(stage));
        getManagerByIdButton.setOnAction(e -> openGetManagerById(stage));
        getManagerByNameButton.setOnAction(e -> openGetManagerByName(stage));
        deleteManagerButton.setOnAction(e -> openDeleteManager(stage));
        menuLayout.getChildren().addAll(title, addManagerButton, getManagerByIdButton, getManagerByNameButton, deleteManagerButton);
        Scene managerMenuScene = new Scene(menuLayout, 400, 300);
        stage.setTitle("Manager Menu - NBA Management System");
        stage.setScene(managerMenuScene);
        stage.show();
    }
    private void openAddManager(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Manager Name");
        TextField teamIdField = new TextField();
        teamIdField.setPromptText("Enter Team ID");
        Button submitButton = new Button("Add Manager");
        submitButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int teamId = Integer.parseInt(teamIdField.getText());
                managerController.add(name, teamId);
            } catch (Exception ex) {
            }
        });
        layout.getChildren().addAll(nameField, teamIdField, submitButton);
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openGetManagerById(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        TextField idField = new TextField();
        idField.setPromptText("Enter Manager ID");
        Button searchButton = new Button("Search");
        Label resultLabel = new Label();
        searchButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Manager manager = managerController.getById(id);
                resultLabel.setText(manager != null ? manager.toString() : "No manager found with ID " + id);
            } catch (Exception ex) {
            }
        });
        layout.getChildren().addAll(idField, searchButton, resultLabel);
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openGetManagerByName(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Manager Name");
        Button searchButton = new Button("Search");
        Label resultLabel = new Label();
        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            Manager manager = managerController.getByName(name);
            resultLabel.setText(manager != null ? manager.toString() : "No manager found with name " + name);
        });
        layout.getChildren().addAll(nameField, searchButton, resultLabel);
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openDeleteManager(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        TextField idField = new TextField();
        idField.setPromptText("Enter Manager ID to Delete");
        Button deleteButton = new Button("Delete");
        Label resultLabel = new Label();
        deleteButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                managerController.delete(id);
                resultLabel.setText("Manager with ID " + id + " deleted successfully.");
            } catch (Exception ex) {
                resultLabel.setText("Failed to delete manager. Ensure the ID is valid.");
            }
        });
        layout.getChildren().addAll(idField, deleteButton, resultLabel);
        stage.setScene(new Scene(layout, 400, 300));
    }
    public static void main(String[] args) {
        launch(args);
    }
}
