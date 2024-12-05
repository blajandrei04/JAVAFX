package com.example.nba.GUI;
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

import static javafx.application.Application.launch;

public class PlayerMenuGUI {
    private PlayerController playerController;

    public PlayerMenuGUI(PlayerController pc) {
        playerController = pc;
    }

    public void start(Stage stage) {
        Repo<NBAPlayer> playerRepo = new RepoMem<>();
        Repo<NBATeam> teamRepo = new RepoMem<>();
        playerController = new PlayerController(new PlayerService(playerRepo, teamRepo));
        BorderPane root = new BorderPane();
        VBox sideBar = new VBox(20);
        sideBar.setAlignment(Pos.TOP_CENTER);
        sideBar.setStyle("-fx-background-color: #2f4f4f; -fx-padding: 20px;");
        Button addPlayerButton = new Button("Add Player");
        Button viewPlayerButton = new Button("View Player by ID");
        Button viewPlayerByNameButton = new Button("View Player by Name");
        Button deletePlayerButton = new Button("Delete Player");
        Button sortByAgeButton = new Button("Sort Players by Age");
        Button backButton = new Button("Back to Main Menu");
        addPlayerButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: white;");
        viewPlayerButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: white;");
        viewPlayerByNameButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: white;");
        deletePlayerButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: white;");
        sortByAgeButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: white;");
        backButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #d9534f; -fx-text-fill: white;");
        sideBar.getChildren().addAll(addPlayerButton, viewPlayerButton, viewPlayerByNameButton, deletePlayerButton, sortByAgeButton, backButton);
        StackPane centerArea = new StackPane();
        Text welcomeText = new Text("Player Menu");
        welcomeText.setFont(Font.font("Arial", 24));
        centerArea.getChildren().add(welcomeText);
        root.setCenter(centerArea);
        root.setLeft(sideBar);
        addPlayerButton.setOnAction(e -> openAddPlayer(centerArea));
        viewPlayerButton.setOnAction(e -> openViewPlayerById(centerArea));
        viewPlayerByNameButton.setOnAction(e -> openViewPlayerByName(centerArea));
        deletePlayerButton.setOnAction(e -> openDeletePlayer(centerArea));
        sortByAgeButton.setOnAction(e -> openSortByAge(centerArea));
        backButton.setOnAction(e -> stage.close());

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Player Menu - NBA Management System");
        stage.setScene(scene);
        stage.show();
    }
    private void openAddPlayer(StackPane centerArea) {
        VBox addPlayerLayout = new VBox(10);
        addPlayerLayout.setAlignment(Pos.CENTER);
        addPlayerLayout.setStyle("-fx-padding: 20px;");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Player Name");
        TextField ageField = new TextField();
        ageField.setPromptText("Enter Player Age");
        TextField salaryField = new TextField();
        salaryField.setPromptText("Enter Player Salary");
        TextField positionField = new TextField();
        positionField.setPromptText("Enter Player Position");
        TextField pointsField = new TextField();
        pointsField.setPromptText("Enter Player Points");
        TextField reboundsField = new TextField();
        reboundsField.setPromptText("Enter Player Rebounds");
        TextField assistsField = new TextField();
        assistsField.setPromptText("Enter Player Assists");
        TextField teamIdField = new TextField();
        teamIdField.setPromptText("Enter Team ID");
        Button submitButton = new Button("Add Player");
        submitButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double salary = Double.parseDouble(salaryField.getText());
                String position = positionField.getText();
                int points = Integer.parseInt(pointsField.getText());
                int rebounds = Integer.parseInt(reboundsField.getText());
                int assists = Integer.parseInt(assistsField.getText());
                int teamId = Integer.parseInt(teamIdField.getText());

                playerController.add(name, age, salary, position, points, rebounds, assists, teamId);
            } catch (Exception ex) {
            }
        });
        addPlayerLayout.getChildren().addAll(nameField, ageField, salaryField, positionField, pointsField, reboundsField, assistsField, teamIdField, submitButton);
        centerArea.getChildren().setAll(addPlayerLayout);
    }
    private void openViewPlayerById(StackPane centerArea) {
        VBox viewPlayerLayout = new VBox(10);
        viewPlayerLayout.setAlignment(Pos.CENTER);
        viewPlayerLayout.setStyle("-fx-padding: 20px;");
        TextField idField = new TextField();
        idField.setPromptText("Enter Player ID");
        Button searchButton = new Button("Search");
        Label resultLabel = new Label();
        searchButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                NBAPlayer player = playerController.getById(id);
                if (player != null) {
                    resultLabel.setText(player.toString());
                } else {
                    resultLabel.setText("No player found with ID " + id);
                }
            } catch (Exception ex) {
            }
        });
        viewPlayerLayout.getChildren().addAll(idField, searchButton, resultLabel);
        centerArea.getChildren().setAll(viewPlayerLayout);
    }
    private void openViewPlayerByName(StackPane centerArea) {
        VBox viewPlayerLayout = new VBox(10);
        viewPlayerLayout.setAlignment(Pos.CENTER);
        viewPlayerLayout.setStyle("-fx-padding: 20px;");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Player Name");
        Button searchButton = new Button("Search");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            List<NBAPlayer> players = playerController.getByName(name);
            if (players != null && !players.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (NBAPlayer player : players) {
                    sb.append(player.toString()).append("\n");
                }
                resultArea.setText(sb.toString());
            } else {
                resultArea.setText("No players found with name " + name);
            }
        });
        viewPlayerLayout.getChildren().addAll(nameField, searchButton, resultArea);
        centerArea.getChildren().setAll(viewPlayerLayout);
    }
    private void openDeletePlayer(StackPane centerArea) {
        VBox deletePlayerLayout = new VBox(10);
        deletePlayerLayout.setAlignment(Pos.CENTER);
        deletePlayerLayout.setStyle("-fx-padding: 20px;");
        TextField idField = new TextField();
        idField.setPromptText("Enter Player ID to Delete");
        Button deleteButton = new Button("Delete");
        Label resultLabel = new Label();
        deleteButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                playerController.delete(id);
                resultLabel.setText("Player with ID " + id + " deleted successfully.");
            } catch (Exception ex) {
                resultLabel.setText("Failed to delete player. Ensure the ID is valid.");
            }
        });
        deletePlayerLayout.getChildren().addAll(idField, deleteButton, resultLabel);
        centerArea.getChildren().setAll(deletePlayerLayout);
    }
    private void openSortByAge(StackPane centerArea) {
        VBox sortPlayerLayout = new VBox(10);
        sortPlayerLayout.setAlignment(Pos.CENTER);
        sortPlayerLayout.setStyle("-fx-padding: 20px;");
        TextArea sortedPlayersArea = new TextArea();
        sortedPlayersArea.setEditable(false);
        Button sortButton = new Button("Sort Players by Age");
        sortButton.setOnAction(e -> {
            List<NBAPlayer> sortedPlayers = playerController.sortByAge();
            StringBuilder sb = new StringBuilder();
            for (NBAPlayer player : sortedPlayers) {
                sb.append(player.toString()).append("\n");
            }
            sortedPlayersArea.setText(sb.toString());
        });
        sortPlayerLayout.getChildren().addAll(sortButton, sortedPlayersArea);
        centerArea.getChildren().setAll(sortPlayerLayout);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
