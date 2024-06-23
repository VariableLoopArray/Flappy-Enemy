package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Enemy;

public class DeadMenu {
    public DeadMenu(){

    }

    public void deadScreenLogic(Enemy mainCharacter, Stage primaryStage, GameController gameController){

        // Réorganiser et chercher le score le plus élevé
        ScoreManager.updateScore(mainCharacter.getGold());
        int highestScore = ScoreManager.getHighestScore();

        // L'image d'arrière plan
        Image backgroundImage = new Image("GameOver.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(640, 440, false, false, false, false);
        Background background = new Background(new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,backgroundSize));
        Pane backgroundPane = new Pane();
        backgroundPane.setBackground(background);

        // VBox pour afficher le score obtenu cette partie et le score le plus élevé
        VBox score = new VBox();
        score.setSpacing(20);
        Label highestScoreLabel = new Label("HighScore : "+highestScore);
        Label scoreLabel = new Label("Score : "+mainCharacter.getGold());
        highestScoreLabel.setFont(Font.font(30));
        scoreLabel.setFont(Font.font(30));
        highestScoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setTextFill(Color.WHITE);
        score.getChildren().addAll(highestScoreLabel,scoreLabel);
        score.setPadding(new Insets(0, 0, 250, 0));
        score.setAlignment(Pos.CENTER);

        // HBox pour afficher un bouton "Restart" et un bouton "Menu"
        HBox buttonBox = new HBox();
        Button Restart = new Button("Restart Game");
        Button Menu = new Button("Menu");
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(Restart, Menu);
        buttonBox.setPadding(new Insets(200,0,0,0));
        buttonBox.setAlignment(Pos.CENTER);

        // Ajouter le tout dans un StackPane et Afficher dans la scene deadMenu
        StackPane layout = new StackPane(backgroundPane, score, buttonBox);
        StackPane.setAlignment(buttonBox, Pos.CENTER);
        Scene newScene = new Scene(layout,640,440);
        Stage deadMenu = new Stage();
        deadMenu.setScene(newScene);
        deadMenu.setTitle("Dead Menu");
        deadMenu.setResizable(false);
        mainCharacter.setHp(100);
        gameController.stopGameLogic();

        // Fermer la fenêtre de jeu et ouvrir l'écran de mort
        primaryStage.close();
        deadMenu.show();

        // Lorsque le bouton "Restart" est cliqué, un nouveau GameController est créé,
        // ce qui réinitialise complètement le jeu
        Restart.setOnAction(e ->{
            try {

                VBox layout1 = new VBox();
                Pane game1 = new Pane();
                game1.setPrefSize(640, 400);
                HBox menu1 = new HBox(20);
                menu1.setPrefSize(640, 40);
                layout1.getChildren().addAll(game1, menu1);
                Scene scene1 = new Scene(layout1, 640, 440);

                primaryStage.setScene(scene1);
                primaryStage.setTitle("Flappy Enemy");
                primaryStage.setResizable(false);

                deadMenu.close();

                GameController newgameController = new GameController();
                newgameController.gameLogic(scene1, game1, menu1, primaryStage,newgameController);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        // Lorsque le bouton "Menu" est cliqué, un nouveau StartMenu est créé
        Menu.setOnAction(e ->{
            try {

                StartMenu startGame = new StartMenu();
                startGame.StartMenuLogic(primaryStage);

                deadMenu.close();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
