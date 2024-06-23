package controller;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Ball;
import model.Coins;
import model.Enemy;
import model.heros.HeroC;
import model.heros.HeroF;
import model.heros.HeroT;
import view.BallView;
import view.CoinView;
import view.EnemyView;
import view.HeroView;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class GameController{

    public GameController(){
    }
    public static double backgroundSpeed = 0;            ;
    public static double gravity = 0;

    public static int fps = 144 ;

    AnimationTimer timer;
    public static double spawnLocationHeroY;
    boolean isJumping = false;

    Enemy mainCharacter = new Enemy(320, 200,30, 0, 0,100, "Enemy.png");
    EnemyView mainCharacterImage = new EnemyView(mainCharacter);
    Coins coin = null;
    ArrayList<CoinView> coinImage = new ArrayList<>();
    ArrayList<HeroView> heros = new ArrayList<>();
    ArrayList<BallView> balls = new ArrayList<>();
    Button pauseButton;
    private boolean isGamePaused = false;


    public void gameLogic(Scene scene, Pane game, HBox menu, Stage primaryStage, GameController gameController) throws Exception {

        // Les images en arrière-plan
        ImageView background1 = new ImageView("Background.png");
        background1.setFitHeight(400);
        background1.setFitWidth(660);
        ImageView background2 = new ImageView("Background.png");
        background2.setFitHeight(400);
        background2.setFitWidth(650);
        background2.setLayoutX(640);

        // Ajouter les images dans le panneau
        game.getChildren().addAll(background1, background2);

        // La barre d'informations en bas de l'écran, affichant un bouton de pause, les points de vie et le score
        pauseButton = new Button("Pause");
        pauseButton.setFocusTraversable(false);
        pauseButton.setOnAction(e -> togglePause());
        StackPane container1 = new StackPane();
        StackPane container2 = new StackPane();
        container1.getChildren().add(new Label("life:" + mainCharacter.getHp()));
        container2.getChildren().add(new Label("Pièce:" + mainCharacter.getGold()));
        Rectangle rectangle1 = new Rectangle(1, 40);
        Rectangle rectangle2 = new Rectangle(1, 40);
        menu.getChildren().addAll(pauseButton, rectangle1, container1, rectangle2, container2);
        menu.setAlignment(Pos.CENTER);

        // Ajouter le joueur dans le panneau
        game.getChildren().add(mainCharacterImage.getEnemyImage());

        // Début de l'animation
        timer = new AnimationTimer() {
            int count = 0;
            long last = 0;
            long lastCoin = 0;
            long lastTP = 0;
            long cummulativeTimeFPS = 0;
            long cummulativeTimeCoin = 0;
            long cummulativeTimeTP = 0;
            boolean isFirstFrame = true;
            double spawnLocationCoinY = 0;
            double spawnLocationHeroY = 0;
            double randomHero = 0;
            int randomSize;
            boolean fpsBool = true;
            boolean occured = true;

            // Méthode pour gérer l'animation (appellé chaque nanoseconde)
            @Override
            public void handle(long now) {

                // Calculer le nombre d'images par seconde
                if (isFirstFrame) {
                    last = now;
                    lastCoin = now;
                    lastTP = now;
                    isFirstFrame = false;
                }

                count += 1;
                cummulativeTimeFPS = now - last;
                cummulativeTimeCoin = now - lastCoin;
                cummulativeTimeTP = now - lastTP;

                // Calculer une fois les "fps" afin que le jeu ne fluctue pas en vitesse.
                if (cummulativeTimeFPS >= 1000000000 && fpsBool) {
                    fps = count;
                    fpsBool = false;
                    last = now;
                }

                // Calcul de la gravité qui commence à 500 pixels vers le bas, avec l'ajout de 10 pixels par pièce ramassée
                GameController.gravity = (1000.0 / (fps * (fps + 1))) + (mainCharacter.getGoldCollected() * 10.0 / (fps * (fps + 1)));

                // Calcul de la vitesse de l'image d'arrière-plan
                backgroundSpeed = (120.0 / fps) + (mainCharacter.getGoldCollected() * 15.0 / fps);

                // Chaque deux secondes
                if (cummulativeTimeCoin >= 2000000000L) {

                    //Génération aléatoire de la position Y de la pièce
                    Random random1 = new Random(System.currentTimeMillis());
                    spawnLocationCoinY = Math.abs(random1.nextInt() % 350);

                    // Créer l'objet représentant la pièce et l'inclure dans la liste et dans le panneau du jeu
                    Coins coin = new Coins(650, spawnLocationCoinY, 15, "Coin.png");
                    coinImage.add(new CoinView(coin));
                    game.getChildren().add(coinImage.get(coinImage.size() - 1).getCoinImage());

                    // Génération aléatoire de la position Y du héros, évitant la superposition avec la pièce créée
                    do {
                        spawnLocationHeroY = (Math.random() * 330);
                    } while (Math.abs(spawnLocationHeroY - spawnLocationCoinY) < 120);
                    randomHero = (Math.random() * 3);
                    randomSize = (int) ((Math.random() * 20) + 10);

                    // Créer l'objet représentant le héros et l'inclure dans la liste et dans le panneau du jeu
                    if (randomHero <= 1) {
                        HeroC Hero = new HeroC(650, spawnLocationHeroY, randomSize, 5, 100, 100, "HeroC.png", true);
                        heros.add(new HeroView(Hero));
                    } else if (randomHero <= 2) {
                        HeroF Hero = new HeroF(650, spawnLocationHeroY, randomSize, 8, 100, 0, "HeroF.png", true);
                        heros.add(new HeroView(Hero));
                    } else {
                        HeroT Hero = new HeroT(650, spawnLocationHeroY, randomSize, 7, 100, 50, "HeroT.png", true);
                        heros.add(new HeroView(Hero));
                    }
                    game.getChildren().add(heros.get(heros.size() - 1).getHeroImage());
                    lastCoin = now;
                }


                // Mise à jour des positions de l'arrière-plan
                background1.setLayoutX(background1.getLayoutX() - backgroundSpeed);
                background2.setLayoutX(background2.getLayoutX() - backgroundSpeed);

                // Si l'arrière-plan est hors de l'écran, le réinitialiser
                if (background1.getLayoutX() <= -640) {
                    background1.setLayoutX(640);
                }
                if (background2.getLayoutX() <= -640) {
                    background2.setLayoutX(640);
                }

                // Déplacement distinct pour chaque type de héros
                for (HeroView hero : heros) {
                    if (hero.getHero() instanceof HeroT) {
                        // 0.5 seconde téléportation
                        if (cummulativeTimeTP >= 500000000) {
                            ((HeroT) hero.getHero()).teleport();
                            lastTP = now;
                        }
                    }
                    hero.getHero().heroMovement();
                    hero.updatePosition();
                }

                // Déplacement de la balle et vérification des collisions
                for (BallView ball : balls) {
                    ball.getBall().balleCollision(mainCharacter, heros, game);
                    ball.getBall().balleMovement();
                    ball.updatePosition();

                }

                // Déplacement de la pièce
                for (CoinView coinView : coinImage) {
                    coinView.getCoin().coinMovement();
                    coinView.updatePosition();
                }

                // Déplacement du joueur et vérification de l'état de ses points de vie, du nombre
                // de pièces collectées et des collisions
                mainCharacter.collectCoin(coinImage, game);
                mainCharacter.gravity();
                mainCharacterImage.updatePosition();
                mainCharacterImage.updateHP(container1, mainCharacter, primaryStage, gameController);
                mainCharacterImage.updateGold(container2, mainCharacter);
                mainCharacter.collisionHero(heros, game);
                mainCharacter.collisionWall(mainCharacter);
                if (mainCharacter.getGoldCollected() == 10 && occured){
                    mainCharacter.easterEgg(primaryStage);
                    occured = false;
                }

            }
        };

        // En appuyant sur la barre d'espace, le personnage saute
        // En appuyant sur la touche E, le personnage tire une balle de feu
        AtomicLong lastEKeyPressTime = new AtomicLong();
        scene.setOnKeyPressed((e) -> {
            switch (e.getCode()) {
                case SPACE:
                    if (!isJumping){
                        mainCharacter.jump();
                        isJumping = true;
                    }
                    break;
                case E:
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastEKeyPressTime.get() >= 1000) {
                        Ball balle = new Ball(mainCharacter.getX(), mainCharacter.getY(), 30, "Ball.png");
                        balls.add(new BallView(balle));
                        game.getChildren().add(balls.get(balls.size() - 1).getBallImage());
                        lastEKeyPressTime.set(currentTime);
                    }
                    break;
            }
        });

        // Éviter que le personnage saute continuellement lorsque le joueur maintient la touche enfoncée.
        scene.setOnKeyReleased((e) -> {
            switch (e.getCode()) {
                case SPACE:
                    isJumping = false;
            }
        });

        // Démarrer le timer pour l'animation et afficher le panneau.
        timer.start();
        primaryStage.show();
    }

    // Arrêter le jeu.
    public void  stopGameLogic(){
        timer.stop();
    }

    // Alterner entre la pause et la reprise du jeu.
    private void togglePause() {
        if (isGamePaused) {
            resumeGame();
        } else {
            pauseGame();
        }
    }

    // Mettre le jeu en pause
    private void pauseGame() {
        timer.stop();
        isGamePaused = true;
        pauseButton.setText("Resume");
    }

    // Reprise du jeu
    private void resumeGame() {
        timer.start();
        isGamePaused = false;
        pauseButton.setText("Pause");
    }
}

