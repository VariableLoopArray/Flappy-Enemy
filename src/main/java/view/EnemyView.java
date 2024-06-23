package view;

import controller.DeadMenu;
import controller.GameController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Enemy;

public class EnemyView   {
    private final Enemy enemy;
    private final ImageView enemyImage;

    // Constructeur qui associe l'image de Enemy à son modèle
    public EnemyView(Enemy enemy){
        this.enemy = enemy;
        this.enemyImage = new ImageView(new Image(enemy.getImagePath()));
        this.enemyImage.setX(enemy.getX()-enemy.getRadius());
        this.enemyImage.setFitWidth(enemy.getRadius()*2);
        this.enemyImage.setFitHeight(enemy.getRadius()*2);
    }

    // Getter
    public ImageView getEnemyImage() {
        return enemyImage;
    }

    // Déplace l'image en fonction de l'emplacement de son modèle associé
    public void updatePosition() {
        enemy.setShape();
        enemyImage.setY(enemy.getY()-enemy.getRadius());
    }

    // Change le libellé lorsque l'ennemi collecte une pièce
    public void updateGold(StackPane container1,Enemy mainCharacter){
        if (!container1.getChildren().isEmpty() && container1.getChildren().get(0) instanceof Label label) {
            label.setText("Pièce : " + mainCharacter.getGold());
        }
    }

    // Change le libellé lorsque l'ennemi subit des dégâts ou affiche l'écran de mort si l'ennemi meurt
    public void updateHP(StackPane container2, Enemy mainCharacter, Stage primaryStage, GameController gameController){
        if (!container2.getChildren().isEmpty() && container2.getChildren().get(0) instanceof Label label) {
            label.setText("Life : " + mainCharacter.getHp() +" %");
        }
        if (mainCharacter.getHp() <= 0){
            DeadMenu deadMenu = new DeadMenu();
            deadMenu.deadScreenLogic(mainCharacter,primaryStage, gameController);
        }
    }
}