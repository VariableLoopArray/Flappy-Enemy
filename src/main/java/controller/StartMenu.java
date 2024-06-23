package controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StartMenu {
    public StartMenu(){

    }

    // Créer l'arrière-plan et les boutons pour le Splash Screen
    public void StartMenuLogic(Stage primaryStage){
        Image backgroundImage = new Image("FlappyEnemy.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        Background background = new Background(new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,backgroundSize));
        Pane backgroundPane = new Pane();
        backgroundPane.setBackground(background);
        StackPane layout = new StackPane(backgroundPane);
        Button Start = new Button("Start Game");
        layout.getChildren().add(Start);
        Scene newScene = new Scene(layout,640,440);
        Stage StartMenu = new Stage();
        StartMenu.setScene(newScene);
        StartMenu.setTitle("Start Menu");
        StartMenu.setResizable(false);
        StartMenu.show();
        Start.setOnAction(e ->{
            // Créer une nouvelle instance de GameController avec les arguments
            // nécessaires pour commencer un nouveau jeu.
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

                StartMenu.close();

                GameController newgameController = new GameController();
                newgameController.gameLogic(scene1, game1, menu1, primaryStage,newgameController);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
