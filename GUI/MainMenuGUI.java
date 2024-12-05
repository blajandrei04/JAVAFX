package com.example.nba.GUI;

import com.example.nba.controller.*;
import com.example.nba.model.*;
import com.example.nba.service.*;
import com.example.nba.repo.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenuGUI extends Application {
    private final PlayerMenuGUI pm;
    private final TeamMenuGUI tm;
    private final ManagerMenuGUI mm;

    private final Repo<Manager> rm;
    private final Repo<NBAPlayer> rp;
    private final Repo<NBATeam> rt;
    private final Repo<Game> rg;
    private final Repo<Conference> rc;

    private final ManagerController mc;
    private final TeamController tc;
    private final PlayerController pc;

    private final PlayerService ps;
    private final TeamService ts;
    private final ManagerService ms;

    public MainMenuGUI() {
        rm = new RepoFile<>("src/resource/db/manager.txt", Manager.class);
        rp = new RepoFile<>("src/resource/db/player.txt", NBAPlayer.class);
        rt = new RepoFile<>("src/resource/db/team.txt", NBATeam.class);
        rg = new RepoFile<>("src/resource/db/game.txt", Game.class);
        rc = new RepoFile<>("src/resource/db/conference.txt", Conference.class);

        ms = new ManagerService(rm, rt);
        ps = new PlayerService(rp, rt);
        ts = new TeamService(rt, rc);

        mc = new ManagerController(ms);
        pc = new PlayerController(ps);
        tc = new TeamController(ts);

        pm = new PlayerMenuGUI(pc);
        tm = new TeamMenuGUI(tc);
        mm = new ManagerMenuGUI(mc);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:/C:/Users/blaja/Downloads/MainMenuBack.jpg", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        menuLayout.setBackground(new Background(backgroundImage));

        ImageView logo = new ImageView("file:/C:/Users/blaja/Downloads/logo.png");
        logo.setFitWidth(200);
        logo.setPreserveRatio(true);

        Button playerButton = createMenuButton("Player Menu");
        Button teamButton = createMenuButton("Team Menu");
        Button managerButton = createMenuButton("Manager Menu");
        Button quitButton = createQuitButton();

        playerButton.setOnAction(e -> openPlayerMenu(primaryStage));
        teamButton.setOnAction(e -> openTeamMenu(primaryStage));
        managerButton.setOnAction(e -> openManagerMenu(primaryStage));
        quitButton.setOnAction(e -> primaryStage.close());

        menuLayout.getChildren().addAll(logo, playerButton, teamButton, managerButton, quitButton);

        Scene scene = new Scene(menuLayout, 800, 600);
        primaryStage.setTitle("NBA Management System - Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-color: #ffffff; " +
                "-fx-border-width: 2px; -fx-background-color: transparent; -fx-text-fill: white;");
        return button;
    }

    private Button createQuitButton() {
        Button quitButton = new Button("Quit");
        quitButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-color: #d9534f; " +
                "-fx-text-fill: white;");
        return quitButton;
    }

    private void openPlayerMenu(Stage stage) {
        pm.start(stage);
    }

    private void openTeamMenu(Stage stage) {
        tm.start(stage);
    }

    private void openManagerMenu(Stage stage) {
        mm.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
