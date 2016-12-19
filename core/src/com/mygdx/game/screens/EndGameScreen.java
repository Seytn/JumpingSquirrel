package com.mygdx.game.screens;

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

public class EndGameScreen extends AbstractScreen {

    private Texture endGameImg;

    EndGameScreen(AndroidGame game, JumpPlayer player) {
        super(game, player);
        init();
    }

    private void init() {
        endGameImg = Assets.sharedInstance.assetManager.get("textures/endGameScreen.png", Texture.class);
        initRetryButton();
        initMainMenuButton();
        initExitButton();
    }

    private void initRetryButton() {
        Button button = new Button();
        button.setWidth(SCREEN_X);
        button.setHeight(100);
        button.setX(-SCREEN_X / 2);
        button.setY(630 - CAMERA_Y_DIFFERENCE);
        button.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen(game, player));
                    }
                },0.2f);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(button);
    }

    private void initMainMenuButton() {
        Button mainMenuButton = new Button();
        mainMenuButton.setWidth(SCREEN_X);
        mainMenuButton.setHeight(90);
        mainMenuButton.setX(-SCREEN_X / 2);
        mainMenuButton.setY(500 - CAMERA_Y_DIFFERENCE);
        mainMenuButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game, player));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(mainMenuButton);
    }

    private void initExitButton() {
        Button exitButton = new Button();
        exitButton.setWidth(SCREEN_X);
        exitButton.setHeight(90);
        exitButton.setX(-SCREEN_X / 2);
        exitButton.setY(340 - CAMERA_Y_DIFFERENCE);
        exitButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(exitButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(endGameImg, camera.position.x - AndroidGame.SCREEN_X / 2, camera.position.y - AndroidGame.SCREEN_Y / 2, SCREEN_X, SCREEN_Y);
        batch.end();
    }
}
