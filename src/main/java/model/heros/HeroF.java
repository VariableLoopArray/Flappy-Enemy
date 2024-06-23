package model.heros;

import controller.GameController;
import model.Hero;


public class HeroF extends Hero {
    boolean used = true;
    double initial;
    public HeroF(double x, double y, double radius, int gold, int hp, int attack, String imagePath, boolean collision){
        super(x, y, radius, gold, hp, attack, imagePath, collision);

    }

    // Méthode qui gère le déplacement sinusoïdal vers la gauche avec une amplitude de 50 pixels
    @Override
    public void heroMovement(){
        if (used){
            initial = this.y;
            used = false;
        }
        this.x = this.x - GameController.backgroundSpeed;
        int horizontal = 640;
        double wavelenght = 2 * Math.PI / horizontal;
        double amplitude = 50;
        this.y = (amplitude * Math.sin(this.x * wavelenght)) + initial ;
    }
}
