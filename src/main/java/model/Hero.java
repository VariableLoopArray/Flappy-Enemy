package model;

import javafx.scene.shape.Circle;

public abstract class Hero {
    protected double x;
    protected double y;
    protected double radius;
    protected int gold;
    protected int hp;
    protected int attack;
    protected String imagePath;
    protected boolean collision;
    protected Circle shape;

    // Constructeur de la classe Hero
    public Hero(double x, double y, double radius, int gold, int hp, int attack, String imagePath,boolean collision){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.shape = new Circle(x,y,radius);
        this.gold = gold;
        this.hp = hp;
        this.attack = attack;
        this.imagePath = imagePath;
        this.collision = collision;
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
    public Circle getShape() {
        return shape;
    }
    public void setShape() {
        this.shape.setCenterY(y);
        this.shape.setCenterX(x);
    }

    // Méthode abstraite utilisée pour les trois héros
    public abstract void heroMovement();

}
