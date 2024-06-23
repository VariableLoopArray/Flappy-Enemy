package model.heros;

import controller.GameController;
import model.Hero;

public class HeroC extends Hero {

    public HeroC(double x, double y, double radius, int gold, int hp, int attack, String imagePath, boolean collision){
        super(x, y, radius, gold, hp, attack, imagePath, collision);

    }

    // Méthode qui gère le déplacement en ligne droite vers la gauche
    @Override
    public void heroMovement(){
        this.x = this.x - GameController.backgroundSpeed;
    }
}
