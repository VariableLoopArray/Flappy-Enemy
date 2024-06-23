package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Hero;

public class HeroView   {
    private final Hero hero;
    private final ImageView heroImage;

    // Constructeur qui associe l'image du Hero à son modèle
    public HeroView(Hero hero){
        this.hero = hero;
        this.heroImage = new ImageView(new Image(hero.getImagePath()));
        this.heroImage.setX(hero.getX()-hero.getRadius());
        this.heroImage.setY(hero.getY()-hero.getRadius());
        this.heroImage.setFitWidth(hero.getRadius()*2);
        this.heroImage.setFitHeight(hero.getRadius()*2);
    }

    // Getters
    public ImageView getHeroImage() {
        return heroImage;
    }
    public Hero getHero() {
        return hero;
    }

    // Déplace l'image en fonction de l'emplacement de son modèle associé
    public void updatePosition(){
        this.heroImage.setX(hero.getX()-hero.getRadius());
        this.heroImage.setY(hero.getY()-hero.getRadius());
        this.hero.setShape();
    }
}
