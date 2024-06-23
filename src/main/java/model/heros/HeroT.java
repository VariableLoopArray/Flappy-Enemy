package model.heros;

import controller.GameController;
import model.Hero;

import java.util.Random;
public class HeroT extends Hero {

    private double location = 650;
    boolean used = true;
    double initial;
    public HeroT(double x, double y, double radius, int gold, int hp, int attack, String imagePath, boolean collision){
        super(x, y, radius, gold, hp, attack, imagePath, collision);
    }

    // Méthode qui gère le déplacement en ligne droite vers la gauche.
    @Override
    public void heroMovement(){
        location = location - GameController.backgroundSpeed;
    }

    // Méthode qui gère le déplacement de la téléportation toutes les 0.5 secondes
    public void teleport(){
        if (used){
            initial = this.y;
            used = false;
        }
        Random randomPositionx = new Random(System.currentTimeMillis());
        Random randomPositiony = new Random(System.nanoTime());

        int changex = randomPositionx.nextInt() % 30;
        int changey = randomPositiony.nextInt() % 30;

        this.x = location + changex;
        this.y = GameController.spawnLocationHeroY + initial + changey;
    }


}
