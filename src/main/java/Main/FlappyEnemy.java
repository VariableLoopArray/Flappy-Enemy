
package Main;

import controller.StartMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class FlappyEnemy extends Application{

    @Override
    public void start(Stage primaryStage) {

        // Quand le jeu démarre, l'écran d'accueil apparaît

        StartMenu startGame = new StartMenu();
        startGame.StartMenuLogic(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }

    }

