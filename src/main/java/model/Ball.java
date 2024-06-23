package model;

import controller.GameController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.heros.HeroC;
import model.heros.HeroF;
import view.HeroView;

import java.util.ArrayList;
import java.util.Iterator;

public class Ball {
    private double x;
    private double y;
    private double radius;
    private Circle shape;
    private String imagePath;

    // Constructeur de la classe Ball
    public Ball(double x, double y, double radius, String imagePath){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.shape = new Circle(x,y,radius);
        this.imagePath = imagePath;
        this.shape.setStroke(Color.RED);
    }

    // Setters et getters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getRadius() {
        return radius;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setShape() {
        this.shape.setCenterX(x);
        this.shape.setCenterY(y);
    }

    // Méthode qui gère le déplacement en ligne droite vers la droite.
    public void balleMovement(){
        this.x = this.x + GameController.backgroundSpeed;
    }

    // Méthode qui gère les collisions entre la balle et un potentiel héros
    public void balleCollision(Enemy mainCharacter, ArrayList<HeroView> heros, Pane game){
        Iterator<HeroView> iterator = heros.iterator();
        // Parcourt tous les héros
        while(iterator.hasNext()){
            HeroView hero = iterator.next();
            if (UtilityClass.checkOverlap(this.shape, hero.getHero().getShape())){
                // En cas de collision, l'ennemi gagne un certain nombre de pièces.
                if (hero.getHero() instanceof HeroC){
                    mainCharacter.setGold(mainCharacter.getGold() + 5);
                } else if (hero.getHero() instanceof HeroF){
                    mainCharacter.setGold(mainCharacter.getGold() + 8);
                }else {
                    mainCharacter.setGold(mainCharacter.getGold() + 7);
                }
                // Le héros meurt
                game.getChildren().remove(hero.getHeroImage());
                iterator.remove();
            }
        }

    }

}