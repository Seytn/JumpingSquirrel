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

public class MainMenuScreen extends AbstractScreen {

    private Texture menuImg;

    MainMenuScreen(AndroidGame game, JumpPlayer player) {
        super(game, player);
        init();
    }

    private void init() {
        menuImg = Assets.sharedInstance.assetManager.get("textures/mainMenuScreen.png", Texture.class);
        initExitButton();
        initNewGameButton();
        initHighScoreButton();
        initSettingsButton();
    }

    private void initSettingsButton() {
    }

    private void initHighScoreButton() {
    }

    private void initNewGameButton() {
        Button button = new Button();
        button.setWidth(SCREEN_X);
        button.setHeight(SCREEN_Y);
        button.setX(0);
        button.setY(SCREEN_Y / 2);
        button.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen(game, player));
                    }
                },0.1f);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(button);
    }

    private void initExitButton() {
        Button button = new Button();
        button.setWidth(SCREEN_X);
        button.setHeight(SCREEN_Y / 2);
        button.setX(0);
        button.setY(0);
        button.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(menuImg, camera.position.x - AndroidGame.SCREEN_X / 2, camera.position.y - AndroidGame.SCREEN_Y / 2, SCREEN_X, SCREEN_Y);
        batch.end();
    }
}
