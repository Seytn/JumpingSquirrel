package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.UI.SimpleLabel;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.entities.Background;
import com.mygdx.game.entities.JumpPlayer;

import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by Kamil on 2016-12-19.
 */

public class HighScoresScreen extends AbstractScreen {

    Texture listImg;
    Stage second;
    Background background;

    HighScoresScreen(AndroidGame game, JumpPlayer player) {
        super(game, player);
        init();
    }

    private void init() {
        initBackground();
        initMainMenuButton();
        initScores();
    }

    private void initBackground() {
        second = new Stage(new StretchViewport(SCREEN_X, SCREEN_Y));
        listImg = Assets.sharedInstance.assetManager.get("textures/highScoreScreen.png", Texture.class);

        background = new Background(listImg);
        background.setWidth(SCREEN_X);
        background.setHeight(SCREEN_Y);
        second.addActor(background);
    }

    private void initScores() {
        Array<Integer> scores = game.scoreService.getBestScores();

        int arraySize = scores.size;
        if (arraySize <= 0) return;
        for (int i = 0; i < arraySize; i++) {
            if (i >= 10) break;
            SimpleLabel score = new SimpleLabel((i) + ". " + scores.get(i), Color.GOLD);
            score.setY(790 - (i + 1) * 60);
            score.setX(100);
            second.addActor(score);
        }

    }

    private void initMainMenuButton() {
        Button mainMenuButton = new Button();
        mainMenuButton.setWidth(SCREEN_X);
        mainMenuButton.setHeight(90);
        mainMenuButton.setX(-SCREEN_X / 2);
        mainMenuButton.setY(150 - CAMERA_Y_DIFFERENCE);
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game, player));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(mainMenuButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
//        batch.draw(listImg, camera.position.x - AndroidGame.SCREEN_X / 2, camera.position.y - AndroidGame.SCREEN_Y / 2, SCREEN_X, SCREEN_Y);
        second.draw();
        batch.end();
    }
}
