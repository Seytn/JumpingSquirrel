package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.entities.JumpPlayer;

import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by Kamil on 2016-12-19.
 */

public class SettingsScreen extends AbstractScreen {

    Texture settingsImg;

    SettingsScreen(AndroidGame game, JumpPlayer player) {
        super(game, player);
        init();
    }

    private void init() {
        settingsImg = Assets.sharedInstance.assetManager.get("textures/settingsScreen.png", Texture.class);
        initMainMenuButton();
    }

    private void initMainMenuButton() {
        Button mainMenuButton = new Button();
        mainMenuButton.setWidth(SCREEN_X);
        mainMenuButton.setHeight(90);
        mainMenuButton.setX(0);
        mainMenuButton.setY(150 - CAMERA_Y_DIFFERENCE);
        mainMenuButton.addListener(new ClickListener(){
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
        batch.draw(settingsImg, camera.position.x - AndroidGame.SCREEN_X / 2, camera.position.y - AndroidGame.SCREEN_Y / 2, SCREEN_X, SCREEN_Y);
        batch.end();
    }
}
