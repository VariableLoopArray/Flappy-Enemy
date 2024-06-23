package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Ball;

public class BallView {
    private final Ball ball;
    private final ImageView ballImage;

    // Constructeur qui associe l'image de la balle à son modèle
    public BallView(Ball ball) {
        this.ball = ball;
        this.ballImage = new ImageView(new Image(ball.getImagePath()));
        this.ballImage.setX(ball.getX()-ball.getRadius());
        this.ballImage.setY(ball.getY()-ball.getRadius());
        this.ballImage.setFitWidth(ball.getRadius()*2);
        this.ballImage.setFitHeight(ball.getRadius()*2);
    }

    // Getters
    public Ball getBall() {
        return ball;
    }
    public ImageView getBallImage() {
        return ballImage;
    }

    // Déplace l'image en fonction de l'emplacement de son modèle associé
    public void updatePosition(){
        this.ballImage.setX(ball.getX()-ball.getRadius());
        this.ball.setShape();
    }
}
