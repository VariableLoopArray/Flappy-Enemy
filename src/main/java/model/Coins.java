package model;

import controller.GameController;
import javafx.scene.shape.Circle;

public class Coins {
    private double x;
    private double y;
    private double radius;
    private Circle shape;
    private String imagePath;

    // Constructeur de la classe Coins
    public Coins(double x, double y, double radius, String imagePath){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.shape = new Circle(x,y,radius);
        this.imagePath = imagePath;
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
    public Circle getShape() {
        return shape;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setShape() {
        this.shape.setCenterX(x);
        this.shape.setCenterY(y);
    }
    // Méthode qui gère le déplacement en ligne droite vers la gauche
    public void coinMovement(){
        this.x = this.x - GameController.backgroundSpeed;
    }
}

