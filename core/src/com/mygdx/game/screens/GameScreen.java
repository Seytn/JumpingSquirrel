package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.UI.ClickCallback;
import com.mygdx.game.UI.ControlModeSelectButton;
import com.mygdx.game.UI.SimpleLabel;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.entities.Background;
import com.mygdx.game.entities.Ground;
import com.mygdx.game.entities.JumpPlayer;
import com.mygdx.game.entities.Platform;
import com.mygdx.game.entities.Walls;
import com.mygdx.game.services.PlatformService;

import static com.mygdx.game.AndroidGame.GRAVITY;
import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by ksikorski on 09.12.2016.
 */

public class GameScreen extends AbstractScreen implements InputProcessor {

    Boolean generated = false;

    private PlatformService platformService;
    private Music music;
    public Texture grassTexture;
    public Texture platformTexture;
    private Texture backgroundTexture;
    private Texture groundTexture;
    private Texture logTexture;

    private SimpleLabel accXValueLabel, scoreLabel, bestScoreLabel;
    private ControlModeSelectButton controlModeSelectButton;
    private Background background;
    private Ground ground;
    private Walls walls;


    private Float averageAccX = 0.0f;


    public GameScreen(AndroidGame game, JumpPlayer player) {
        super(game, player);
        game.scoreService.resetScore();

        assignDataToVariables();
        playMusic();
        initBackground();
        initGround();
        initWalls();

        initPlatformService();
//        generatePlatforms();
        initControlTypeSelectButton();
        initAccXValueLabel();

        initScoreLabel();
        initBestScoreLabel();


        stage.addActor(player);
    }

    private void initPlatformService() {
        platformService = new PlatformService(this, stage);
    }

    private void initWalls() {
        walls = new Walls(logTexture, stage);
        stage.addActor(walls.leftWall);
        stage.addActor(walls.rightWall);
    }

    private void initGround() {
        ground = new Ground(groundTexture);
        stage.addActor(ground);
    }

    private void initBackground() {
        background = new Background(backgroundTexture);
        stage.addActor(background);
    }

    private void playMusic() {
        if (music != null) {
            music.setVolume(0.3f);
            music.play();
            music.setLooping(true);
        }
    }

    private void initControlTypeSelectButton() {
        controlModeSelectButton = new ControlModeSelectButton(new ClickCallback() {
            @Override
            public void onClick() {
                switch (game.controlMode) {
                    case ACCELEROMETER: {
                        game.controlMode = AndroidGame.ControlMode.MANUAL;
                        break;
                    }
                    case MANUAL: {
                        game.controlMode = AndroidGame.ControlMode.ACCELEROMETER;
                        break;
                    }
                }
            }
        });
    }

    private void assignDataToVariables() {
        grassTexture = Assets.sharedInstance.assetManager.get("textures/grass.png",Texture.class);
        platformTexture = Assets.sharedInstance.assetManager.get("textures/platform.png",Texture.class);
        backgroundTexture = Assets.sharedInstance.assetManager.get("textures/clouds.png",Texture.class);
        groundTexture = Assets.sharedInstance.assetManager.get("textures/grass2.png",Texture.class);
        logTexture = Assets.sharedInstance.assetManager.get("textures/log.png",Texture.class);

        music = Assets.sharedInstance.assetManager.get("sounds/theme.mp3", Music.class);
    }

    private void initAccXValueLabel() {
        accXValueLabel = new SimpleLabel("");
        stage.addActor(accXValueLabel);
    }

    private void initScoreLabel() {
        scoreLabel = new SimpleLabel("");
        stage.addActor(scoreLabel);
    }

    private void initBestScoreLabel() {
        bestScoreLabel = new SimpleLabel("");
        stage.addActor(bestScoreLabel);
    }

    /* Main render method */
    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        batch.begin();
        stage.draw();
        batch.end();
    }

    /* objects (images, labels, buttons etc.) position update methods */
    private void update() {
        handleInput();
        updateScoreLabel();

        backgroundPositionUpdate();
        wallsPositionUpdate();
        labelsPositionUpdate();
        playerYPositionUpdate();
        buttonsPositionUpdate();

        stage.act();
    }

    private void wallsPositionUpdate() {
        walls.leftWall.setY(camera.position.y - SCREEN_Y);
        walls.rightWall.setY(camera.position.y - SCREEN_Y);
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + game.scoreService.getPoints());
        bestScoreLabel.setText("Your best: " + game.scoreService.getBestScore());
    }

    private void backgroundPositionUpdate() {
        background.setX(camera.position.x - SCREEN_X / 2);
        background.setY(camera.position.y - SCREEN_Y / 2);
    }

    private void labelsPositionUpdate() {
        accXValueLabel.setPosition(camera.position.x + 100, camera.position.y + 400);
        scoreLabel.setPosition(camera.position.x - 280, camera.position.y + 460);
        bestScoreLabel.setPosition(camera.position.x - 280, camera.position.y + 425);
    }

    private void buttonsPositionUpdate() {
		controlModeSelectButton.setPosition(camera.position.x + 100, camera.position.y + 250);
    }

    /* Handle player jumping animation */
    private void playerYPositionUpdate() {
        player.setY(player.getY() + (player.jumpSpeed * Gdx.graphics.getDeltaTime()));

        if(player.getY() > 0){
            player.jumpSpeed += GRAVITY;
        } else {
            player.setY(0);
            player.canJump = true;
            player.jumpSpeed = 0;
        }

        for (Platform p : platformService.platformArray){
            if(player.getY() - p.getY() > 1000) {
                platformService.platformArray.removeValue(p, true);
                p.addAction(Actions.removeActor());
                break;
            }

            if(isPlayerOnPlatform(p)){
                addPlatformPoints(p);
                player.setY(p.getY() + p.getHeight()-10);
                player.canJump = true;
                player.jumpSpeed = 0;
            }

            if (player.getY() >= 300 * 19 && !generated){
                platformService.generateMorePlatforms();
                generated = true;
            }
        }
    }

    private void addPlatformPoints(Platform p) {
        if (!p.reached) {
            game.scoreService.addPlatforPoints();
            p.reached = true;
        }
    }

    /* Stops falling down if on a platform */
    private boolean isPlayerOnPlatform(Platform p) {
        Rectangle rectPlayer = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        Rectangle rectPlatform = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        return player.jumpSpeed <= 0 && rectPlayer.overlaps(rectPlatform) && rectPlayer.getY() > rectPlatform.getY() + rectPlatform.getHeight() - 20;
    }

    /* Input handling methods */
    private void handleInput() {
        /* Accelerometer handler */
        if (game.controlMode == AndroidGame.ControlMode.ACCELEROMETER) {
            float accelerometerX = Gdx.input.getAccelerometerX();
            accXValueLabel.setText(Float.toString(accelerometerX));

            /* Weighted arithmetic mean for smooth player animation */
            averageAccX = (accelerometerX + (15 * averageAccX) / 16);

            if(averageAccX > 0.2 && player.getX() > 0){
                if (averageAccX < 0.6){
                    player.setX(player.getX() - (player.speed / 3 * Gdx.graphics.getDeltaTime()));
                } else {
                    player.setX(player.getX() - (player.speed * Gdx.graphics.getDeltaTime()));
                }
            }
            if(averageAccX < -0.2 && player.getX() < SCREEN_X){
                if (averageAccX > -0.6){
                    player.setX(player.getX() + (player.speed / 3 * Gdx.graphics.getDeltaTime()));
                } else {
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int tapX, int tapY, int pointer) {
        if (game.controlMode == AndroidGame.ControlMode.MANUAL) {
            if (tapX < SCREEN_X / 2) {
                player.setX(player.getX() - (player.speed * Gdx.graphics.getDeltaTime()));

                return true;
            }
            if (tapX > SCREEN_X / 2) {
                player.setX(player.getX() + (player.speed * Gdx.graphics.getDeltaTime()));
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
