package model;

import javafx.scene.shape.Circle;

public class UtilityClass {
    // Méthode utilisée pour la détection de collision
    public static boolean checkOverlap(Circle hitbox1, Circle hitbox2){
        double distance = Math.sqrt(Math.pow(hitbox2.getCenterX() - hitbox1.getCenterX(), 2) +
                Math.pow(hitbox2.getCenterY() - hitbox1.getCenterY(), 2));;
        return distance < (hitbox1.getRadius() + hitbox2.getRadius());
    }
}
