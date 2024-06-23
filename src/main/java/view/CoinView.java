package view;

import javafx.scene.image.Image;
import model.Coins;

import javafx.scene.image.ImageView;


public class CoinView {
    private final Coins coin;
    private final ImageView coinImage;

    // Constructeur qui associe l'image de Coin à son modèle
    public CoinView (Coins coin) {
        this.coin = coin;
        this.coinImage = new ImageView(new Image(coin.getImagePath()));
        this.coinImage.setX(coin.getX()-coin.getRadius());
        this.coinImage.setY(coin.getY()-coin.getRadius());
        this.coinImage.setFitWidth(coin.getRadius()*2);
        this.coinImage.setFitHeight(coin.getRadius()*2);
    }

    // Getters
    public Coins getCoin() {
        return coin;
    }
    public ImageView getCoinImage() {
        return coinImage;
    }

    // Déplace l'image en fonction de l'emplacement de son modèle associé
    public void updatePosition(){
        this.coinImage.setX(coin.getX()-coin.getRadius());
        this.coin.setShape();
    }
}
