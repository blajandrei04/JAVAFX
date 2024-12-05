package com.example.nba.GUI;
import com.example.nba.controller.TeamController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class TeamMenuGUI extends Application {
    private TeamController teamController;
    public TeamMenuGUI(TeamController teamController) {
        this.teamController = teamController;
    }
    @Override
    public void start(Stage stage) {
        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        Label title = new Label("Team Menu");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Button addTeamButton = new Button("Add Team");
        Button getTeamByIdButton = new Button("Get Team by ID");
        Button getTeamByNameButton = new Button("Get Team by Name");
        Button deleteTeamButton = new Button("Delete Team");
        addTeamButton.setOnAction(e -> openAddTeam(stage));
        getTeamByIdButton.setOnAction(e -> openGetTeamById(stage));
        getTeamByNameButton.setOnAction(e -> openGetTeamByName(stage));
        deleteTeamButton.setOnAction(e -> openDeleteTeam(stage));
        menuLayout.getChildren().addAll(title, addTeamButton, getTeamByIdButton, getTeamByNameButton, deleteTeamButton);
        Scene teamMenuScene = new Scene(menuLayout, 400, 300);
        stage.setTitle("Team Menu - NBA Management System");
        stage.setScene(teamMenuScene);
        stage.show();
    }
    private void openAddTeam(Stage stage) {
        VBox formLayout = new VBox(10);
        formLayout.setAlignment(Pos.CENTER);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Team Name");

        TextField conferenceIdField = new TextField();
        conferenceIdField.setPromptText("Enter Conference ID");

        Button addButton = new Button("Add Team");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            Integer conferenceId = Integer.parseInt(conferenceIdField.getText());
            teamController.add(name, conferenceId);
            start(stage);
        });

        formLayout.getChildren().addAll(nameField, conferenceIdField, addButton);

        Scene addTeamScene = new Scene(formLayout, 400, 300);
        stage.setTitle("Add Team - NBA Management System");
        stage.setScene(addTeamScene);
        stage.show();
    }
    private void openGetTeamById(Stage stage) {
        VBox formLayout = new VBox(10);
        formLayout.setAlignment(Pos.CENTER);

        TextField idField = new TextField();
        idField.setPromptText("Enter Team ID");

        Button getButton = new Button("Get Team");
        getButton.setOnAction(e -> {
            Integer id = Integer.parseInt(idField.getText());
            System.out.println(teamController.getById(id));
            start(stage);
        });
        formLayout.getChildren().addAll(idField, getButton);
        Scene getTeamScene = new Scene(formLayout, 400, 300);
        stage.setTitle("Get Team by ID - NBA Management System");
        stage.setScene(getTeamScene);
        stage.show();
    }
    private void openGetTeamByName(Stage stage) {
        VBox formLayout = new VBox(10);
        formLayout.setAlignment(Pos.CENTER);
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Team Name");
        Button getButton = new Button("Get Team");
        getButton.setOnAction(e -> {
            String name = nameField.getText();
            System.out.println(teamController.getByName(name));
            start(stage);
        });
        formLayout.getChildren().addAll(nameField, getButton);
        Scene getTeamByNameScene = new Scene(formLayout, 400, 300);
        stage.setTitle("Get Team by Name - NBA Management System");
        stage.setScene(getTeamByNameScene);
        stage.show();
    }
    private void openDeleteTeam(Stage stage) {
        VBox formLayout = new VBox(10);
        formLayout.setAlignment(Pos.CENTER);
        TextField idField = new TextField();
        idField.setPromptText("Enter Team ID to Delete");
        Button deleteButton = new Button("Delete Team");
        deleteButton.setOnAction(e -> {
            Integer id = Integer.parseInt(idField.getText());
            teamController.delete(id);
            start(stage);
        });
        formLayout.getChildren().addAll(idField, deleteButton);
        Scene deleteTeamScene = new Scene(formLayout, 400, 300);
        stage.setTitle("Delete Team - NBA Management System");
        stage.setScene(deleteTeamScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
