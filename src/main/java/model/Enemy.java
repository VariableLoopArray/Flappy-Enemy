package model;

import controller.GameController;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.heros.HeroC;
import model.heros.HeroF;
import view.CoinView;
import view.HeroView;
import java.util.ArrayList;
import java.util.Iterator;

public class Enemy {
    private double x;
    private double y;
    private double radius;
    private int gold;
    private int goldCollected;
    private int hp;
    private String imagePath;
    private double ySpeed = 0;
    private static double JUMP_HEIGHT = 300;
    private Circle shape;

    // Constructeur de la classe Enemy
    public Enemy(double x,double y, double radius, int gold,int goldCollected, int hp, String imagePath){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.gold = gold;
        this.goldCollected = goldCollected;
        this.hp = hp;
        this.imagePath = imagePath;
        this.shape = new Circle(x,y,radius);
    }

    // Setter et getters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getySpeed() {
        return ySpeed;
    }
    public double getRadius() {
        return radius;
    }
    public int getGold() {
        return gold;
    }
    public int getGoldCollected() {
        return goldCollected;
    }
    public int getHp() {
        return hp;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setShape() {
        this.shape.setCenterX(this.x);
        this.shape.setCenterY(this.y);
    }

    // L'ennemi est limité à la fenêtre de jeu
    public void setY(double y) {
        if (y >= 30 && y<= 370) {
            this.y = y;
        } else if ( y <= 30) {
            this.y = 30;
        } else{
            this.y = 370;
        }
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setHp(int hp) {
        if (hp >= 0) {
            this.hp = hp;
        } else{
            this.hp = 0;
        }
    }

    // inverser instantanément la vitesse lorsque le joueur appuie sur la touche espace (sauter)
    public void jump () {
        ySpeed = -JUMP_HEIGHT / GameController.fps;
        this.setY(this.getY() + this.getySpeed());
    }

    // La gravité qui est continuellement appliquée sur le personnage
    public void gravity() {
        if (this.ySpeed <= 300.0 / GameController.fps){
            this.ySpeed += GameController.gravity;
        };
        this.setY(this.getY() + this.getySpeed());
    }

    // Méthode qui gère les collisions entre l'ennemi et les différents héros
    public void collisionHero(ArrayList<HeroView> heros,Pane game){
        Iterator<HeroView> iterator = heros.iterator();
        while (iterator.hasNext()){
            HeroView hero = iterator.next();
            if (UtilityClass.checkOverlap(this.shape, hero.getHero().getShape()) &&
                    hero.getHero().collision){
                if (hero.getHero() instanceof HeroC){
                    this.hp -= 100;
                    hero.getHero().collision = false;
                } else if (hero.getHero() instanceof HeroF){
                    if (this.gold < 10){
                        this.gold = 0;
                    } else {
                        this.gold -= 10;
                    }
                    hero.getHero().collision = false;
                } else {
                    this.hp -= 50;
                    hero.getHero().collision = false;
                    game.getChildren().remove(hero.getHeroImage());
                    iterator.remove();
                }

            }
        }

    }

    // Méthode qui gère les collisions avec les bordures de l'écran (haut et bas)
    public void collisionWall(Enemy mainCharacter){
        if (this.y - mainCharacter.getRadius() <= 0){
            ySpeed = JUMP_HEIGHT / GameController.fps;
            this.setY(this.getY() + this.getySpeed());
        } else if (this.y + mainCharacter.getRadius() >= 400){
            ySpeed = -JUMP_HEIGHT / GameController.fps;
            this.setY(this.getY() + this.getySpeed());
        }

    }

    // Méthode qui gère la collecte de pièces
    public void collectCoin(ArrayList<CoinView> CoinImage, Pane game){
        Iterator<CoinView> iterator = CoinImage.iterator();
        while(iterator.hasNext()){
            CoinView coinView = iterator.next();
            if (UtilityClass.checkOverlap(this.shape, coinView.getCoin().getShape())){
                game.getChildren().remove(coinView.getCoinImage());
                iterator.remove();
                this.gold += 1;
                this.goldCollected += 1;
            }
        }
    }
    // Easter Egg, lorsque 10 pièces sont collectées, Merci Robin apparaît!
    public void easterEgg (Stage primaryStage){
        primaryStage.setTitle("Merci Robin !!! (10 pièces obtenues)");
    }

}



