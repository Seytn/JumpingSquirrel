package com.mygdx.game.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.entities.JumpPlayer;

import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by Kamil on 2016-12-18.
 */

public class MainMenuScreen extends AbstractScreen {

    private Texture menuImg;
    private Music music;

    private Button exitButton;
    private Button startGameButton;
    private Button highScoreButton;
    private Button settingsButton;

    MainMenuScreen(AndroidGame game, JumpPlayer player) {
        super(game, player);
        init();
        game.scoreService.updateSavedScore();
    }

    private void init() {
        menuImg = Assets.sharedInstance.assetManager.get("textures/mainMenuScreen.png", Texture.class);
        initExitButton();
        initNewGameButton();
        initHighScoreButton();
        initSettingsButton();

//        playMusic();
    }

    private void playMusic() {
        music = Assets.sharedInstance.assetManager.get("sounds/theme.mp3", Music.class);

        if (music != null) {
            music.setVolume(0.3f);
            music.play();
            music.setLooping(true);
        }
    }

    private void initSettingsButton() {
        settingsButton = new Button();
        settingsButton.setWidth(SCREEN_X);
        settingsButton.setHeight(90);
        settingsButton.setX(-SCREEN_X / 2);
        settingsButton.setY(510 - CAMERA_Y_DIFFERENCE);
        settingsButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new SettingsScreen(game, player));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(settingsButton);
    }

    private void initNewGameButton() {
        startGameButton = new Button();
        startGameButton.setWidth(SCREEN_X);
        startGameButton.setHeight(100);
        startGameButton.setX(-SCREEN_X / 2);
        startGameButton.setY(630 - CAMERA_Y_DIFFERENCE);
        startGameButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen(game, player));
                    }
                }, 0.1f);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(startGameButton);
    }

    private void initExitButton() {
        exitButton = new Button();
        exitButton.setWidth(SCREEN_X);
        exitButton.setHeight(90);
        exitButton.setX(-SCREEN_X / 2);
        exitButton.setY(340 - CAMERA_Y_DIFFERENCE);
        exitButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.scoreService.updateSavedScore();
                System.exit(0);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(exitButton);
    }

    private void initHighScoreButton() {
        highScoreButton = new Button();
        highScoreButton.setWidth(SCREEN_X);
        highScoreButton.setHeight(90);
        highScoreButton.setX(-SCREEN_X / 2);
        highScoreButton.setY(420 - CAMERA_Y_DIFFERENCE);
        highScoreButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HighScoresScreen(game, player));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(highScoreButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(menuImg, camera.position.x - AndroidGame.SCREEN_X / 2, camera.position.y - AndroidGame.SCREEN_Y / 2, SCREEN_X, SCREEN_Y);
        batch.end();
    }
}
