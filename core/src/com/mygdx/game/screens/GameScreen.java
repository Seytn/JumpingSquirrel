package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.UI.ControlModeSelectButton;
import com.mygdx.game.UI.SimpleLabel;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.controllers.PlatformController;
import com.mygdx.game.controllers.RandomObjectsController;
import com.mygdx.game.entities.Background;
import com.mygdx.game.entities.BonusObject;
import com.mygdx.game.entities.Ground;
import com.mygdx.game.entities.JumpPlayer;
import com.mygdx.game.entities.Platform;
import com.mygdx.game.entities.Walls;

import java.util.ArrayList;

import static com.mygdx.game.AndroidGame.GRAVITY;
import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by ksikorski on 09.12.2016.
 */

public class GameScreen extends AbstractScreen {

    private PlatformController platformController;

    public Texture grassTexture;
    private Texture backgroundTexture;
    private Texture groundTexture;
    private Texture logTexture;
    private Texture backButtonTexture;
    private Texture arrowLeftTexture;
    private Texture arrowRightTexture;

    private SimpleLabel accXValueLabel, scoreLabel, bestScoreLabel;
    private ControlModeSelectButton controlModeSelectButton;
    private Background background;
    private Ground ground;
    private Walls walls;
    private ImageButton backButton;
    private Image arrowLeft;
    private Image arrowRight;

    private RandomObjectsController randomObjectsController;

    private Float averageAccX = 0.0f;

    /**
     * GameScreen constructor, it initializes graphic objects and adds them to stage.
     * @param game instance of AndroidGame
     * @param player this is a "Jumping Squirrel" instance
     */
    public GameScreen(AndroidGame game, JumpPlayer player) {
        super(game, player);
        game.scoreService.resetScore();

        assignDataToVariables();
        initBackground();
        initGround();
        initWalls();

        initBackToMenuButton();
        initPlatformService();
        initAccXValueLabel();
        initArrows();

        initScoreLabel();
        initBestScoreLabel();

        initRandomObjectsController();
        stage.addActor(player);

    }

    /**
     * initialize arrows if control mode is set to MANUAL
     */
    private void initArrows() {
        arrowLeft = new Image(arrowLeftTexture);
        arrowRight = new Image(arrowRightTexture);
        if (game.controlMode == AndroidGame.ControlMode.MANUAL){

            arrowLeft.setHeight(180);
            arrowLeft.setWidth(100);

            arrowRight.setHeight(180);
            arrowRight.setWidth(100);

            stage.addActor(arrowLeft);
            stage.addActor(arrowRight);
        }
    }

    /**
     * add back button to stage
     */
    private void initBackToMenuButton() {
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(backButtonTexture));
        backButton = new ImageButton(drawable);
        backButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.scoreService.saveScore();
                game.setScreen(new MainMenuScreen(game, new JumpPlayer(game.playerTexture)));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(backButton);
    }

    /**
     * make new instance of RandomObjectController
     */
    private void initRandomObjectsController() {
        randomObjectsController = new RandomObjectsController(game, stage, player);
    }

    /**
     * initialize PlatformService passing self and stage instance to its constructor method.
     */
    private void initPlatformService() {
        platformController = new PlatformController(this, stage);
    }

    /**
     * add walls to stage
     */
    private void initWalls() {
        walls = new Walls(logTexture, stage);
        stage.addActor(walls.leftWall);
        stage.addActor(walls.rightWall);
    }

    /**
     * add ground to stage
     */
    private void initGround() {
        ground = new Ground(groundTexture);
        stage.addActor(ground);
    }

    /**
     * make background and add to stage
     */
    private void initBackground() {
        background = new Background(backgroundTexture);
        stage.addActor(background);
    }

    /**
     * take textures from Asset Manager and assign them to variables
     */
    private void assignDataToVariables() {
        grassTexture = Assets.sharedInstance.assetManager.get("textures/grass.png",Texture.class);
        backgroundTexture = Assets.sharedInstance.assetManager.get("textures/clouds.png",Texture.class);
        groundTexture = Assets.sharedInstance.assetManager.get("textures/grass2.png",Texture.class);
        logTexture = Assets.sharedInstance.assetManager.get("textures/log.png",Texture.class);
        backButtonTexture = Assets.sharedInstance.assetManager.get("textures/backToMenu.png",Texture.class);
        arrowLeftTexture = Assets.sharedInstance.assetManager.get("textures/arrow-left.png",Texture.class);
        arrowRightTexture = Assets.sharedInstance.assetManager.get("textures/arrow-right.png",Texture.class);
    }

    /**
     * show actual accelerometer X value on the screen
     */
    private void initAccXValueLabel() {
//        accXValueLabel = new SimpleLabel("");
//        stage.addActor(accXValueLabel);
    }

    /**
     * add score label to screen
     */
    private void initScoreLabel() {
        scoreLabel = new SimpleLabel("");
        stage.addActor(scoreLabel);
    }

    private void initBestScoreLabel() {
        bestScoreLabel = new SimpleLabel("");
        stage.addActor(bestScoreLabel);
    }

    /**
     * override method that is called every frame
     * @param delta time between last and actual frame
     */
    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        checkDependencies();

        batch.begin();
        stage.draw();
        batch.end();
    }

    /* objects (images, labels, buttons etc.) position update methods */

    /**
     * update objects and their positions on the screen
     */
    private void update() {
        handleInput();
        updateScoreLabel();

        backgroundPositionUpdate();
        wallsPositionUpdate();
        labelsPositionUpdate();
        playerJumpPositionUpdate();
        buttonsPositionUpdate();

        arrowsUpdate();
        backButton.setY(camera.position.y + 330);
        backButton.setX(camera.position.x + 20);
        stage.act();
    }

    /**
     * update position of arrows
     */
    private void arrowsUpdate() {
        arrowLeft.setY(camera.position.y - 300);
        arrowLeft.setX(camera.position.x - 250);

        arrowRight.setY(camera.position.y - 300);
        arrowRight.setX(camera.position.x + 150);
    }

    /**
     * call collision checking methods
     */
    private void checkDependencies(){
        checkPlatformAndPlayerDependencies();
        checkIfPlayerFellTooLow();
        checkBonusObjectsAndPlayerDependencies();
    }

    /**
     * update position of walls
     */
    private void wallsPositionUpdate() {
        walls.leftWall.setY(camera.position.y - SCREEN_Y);
        walls.rightWall.setY(camera.position.y - SCREEN_Y);
    }

    /**
     * update position of scoreLabel
     */
    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + game.scoreService.getPoints());
        bestScoreLabel.setText("Your best: " + game.scoreService.getBestScore());
    }

    /**
     * update position of background texture
     */
    private void backgroundPositionUpdate() {
        background.setX(camera.position.x - SCREEN_X / 2);
        background.setY(camera.position.y - SCREEN_Y / 2);
    }

    /**
     * update position of labels
     */
    private void labelsPositionUpdate() {
//        accXValueLabel.setPosition(camera.position.x + 100, camera.position.y + 400);
        scoreLabel.setPosition(camera.position.x - 280, camera.position.y + 460);
        bestScoreLabel.setPosition(camera.position.x - 280, camera.position.y + 425);
    }

    /**
     * update position of buttons
     */
    private void buttonsPositionUpdate() {
		controlModeSelectButton.setPosition(camera.position.x + 100, camera.position.y + 250);
    }

    /**
     * handle player jumping animation
     */
    private void playerJumpPositionUpdate() {
        player.setY(player.getY() + (player.jumpSpeed * Gdx.graphics.getDeltaTime()));

        if(player.getY() > 0){
            player.jumpSpeed += GRAVITY;
        } else {
            player.setY(0);
            player.canJump = true;
            player.jumpSpeed = 0;
        }

        player.setGreatestHeight(player.getY());
    }

    /**
     * check player and platform dependencies
     */
    private void checkPlatformAndPlayerDependencies() {
        platformController.generateMorePlatforms();

        ArrayList<Platform> platformsToRemove = new ArrayList<Platform>();
        for (Platform platform : platformController.platformArray){
            if(player.getY() - platform.getY() > 600) {
                platform.addAction(Actions.removeActor());
                platformController.platformArray.remove(platform);
                break;
            }

            if(isPlayerOnPlatform(platform)){
                addPlatformPoints(platform);
                player.setY(platform.getY() + platform.getHeight()-10);
                player.canJump = true;
                player.jumpSpeed = 0;
                if (platform.type == Platform.PlatformType.DISINTEGRATING) {
                    platform.disintegrate();
                    platformsToRemove.add(platform);
                }
            }
        }

        platformController.platformArray.removeAll(platformsToRemove);
    }

    /**
     * check player and bonus objects dependencies
     */
    private void checkBonusObjectsAndPlayerDependencies() {
        ArrayList<BonusObject> objectsToRemove = new ArrayList<BonusObject>();
        for (BonusObject bonusObject : randomObjectsController.bonusList){
            if(isPlayerOverlappingBonusObject(bonusObject)){
                checkIfPaprika(bonusObject);
                addBonusPoints(bonusObject);
                bonusObject.playSound();
                bonusObject.addAction(Actions.removeActor());
                objectsToRemove.add(bonusObject);
            }
        }
        randomObjectsController.bonusList.removeAll(objectsToRemove);
    }

    /**
     * check is collected object is paprika type (poison)
     * @param bonusObject
     */
    private void checkIfPaprika(BonusObject bonusObject) {
        if (bonusObject.type == BonusObject.BonusType.POISON){
            game.scoreService.saveScore();
            game.setScreen(new EndGameScreen(game, new JumpPlayer(game.playerTexture)));
        }
    }

    /**
     * check if player is falling too deep
     */
    private void checkIfPlayerFellTooLow() {
        if (player.getGreatestHeight() - player.getY() > 800){
            game.scoreService.saveScore();
            game.setScreen(new EndGameScreen(game, new JumpPlayer(game.playerTexture)));
        }
    }

    /**
     * add points when bonus object is taken
     * @param bonusObject bonus object instance
     */
    private void addBonusPoints(BonusObject bonusObject) {
        game.scoreService.addPoints(20);
    }

    /**
     * add points when new platform is reached
     * @param p
     */
    private void addPlatformPoints(Platform p) {
        if (!p.reached) {
            game.scoreService.addPlatformPoints();
            p.reached = true;
        }
    }

    /**
     * Stop player from falling down if on a platform
     * @param platform instance of platform
     * @return true if on platform
     */
    private boolean isPlayerOnPlatform(Platform platform) {
        int collisionFix = 30;
        Rectangle rectPlayer = new Rectangle(player.getX() + collisionFix, player.getY() + collisionFix, player.getWidth() - collisionFix, player.getHeight() - collisionFix);
        Rectangle rectPlatform = new Rectangle(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
        return player.jumpSpeed <= 0 && rectPlayer.overlaps(rectPlatform) && rectPlayer.getY() > rectPlatform.getY() + rectPlatform.getHeight() - 20;
    }

    /**
     * Check player and bonus object collision
     * @param bonusObject object instance
     * @return true if collision detected
     */
    private boolean isPlayerOverlappingBonusObject(BonusObject bonusObject) {
        int collisionFix = 20;
        Rectangle rectPlayer = new Rectangle(player.getX() + collisionFix, player.getY() + collisionFix, player.getWidth() - collisionFix, player.getHeight() - collisionFix);
        Rectangle rectBonusObject = new Rectangle(bonusObject.getX(), bonusObject.getY(), bonusObject.getWidth(), bonusObject.getHeight());
        return rectBonusObject.overlaps(rectPlayer);
    }

    /**
     * Handle input from accelerometer or touchscreen and change player position
     */
    private void handleInput() {

        /* Accelerometer handler */
        if (game.controlMode == AndroidGame.ControlMode.ACCELEROMETER) {
            float accelerometerX = Gdx.input.getAccelerometerX();
//            accXValueLabel.setText(Float.toString(accelerometerX));

            /* Weighted arithmetic mean for smooth player animation */
            averageAccX = (accelerometerX + (3 * averageAccX) / 4);

            if(averageAccX > 0.01 && player.getX() > 0){
                player.setX(player.getX() - (averageAccX * 50 * Gdx.graphics.getDeltaTime()));
            }

            if(averageAccX < -0.01 && player.getX() < SCREEN_X) {
                player.setX(player.getX() + (-averageAccX * 50 * Gdx.graphics.getDeltaTime()));
            }

        }

        if (game.controlMode == AndroidGame.ControlMode.MANUAL) {
            if (Gdx.input.isTouched()) {
                if (Gdx.input.getX() < SCREEN_X / 2  && player.getX() > 0) {
                    player.setX(player.getX() - (player.speed * Gdx.graphics.getDeltaTime()));

                }
                if (Gdx.input.getX() > SCREEN_X / 2 && player.getX() < SCREEN_X) {
                    player.setX(player.getX() + (player.speed * Gdx.graphics.getDeltaTime()));
                }
            }
        }

        /* Auto jumping or manual - depends on selected jump mode */
        switch (game.jumpMode) {
            case MANUAL: {
                if (Gdx.input.justTouched()) {
                    player.jump();
                }
                break;
            }
            case AUTO: {
                player.jump();
                break;
            }
        }
    }
}
