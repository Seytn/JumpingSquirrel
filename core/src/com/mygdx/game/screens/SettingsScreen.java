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
        initJumpModeButton();
        initControlModeButton();
    }

    private void initMainMenuButton() {
        Button mainMenuButton = new Button();
        mainMenuButton.setWidth(SCREEN_X);
        mainMenuButton.setHeight(90);
        mainMenuButton.setX(-SCREEN_X / 2);
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

    private void initControlModeButton() {
        Button controlModeButton = new Button();
        controlModeButton.setWidth(SCREEN_X);
        controlModeButton.setHeight(90);
        controlModeButton.setX(-SCREEN_X / 2);
        controlModeButton.setY(630 - CAMERA_Y_DIFFERENCE);
        controlModeButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO control mode icon
                switch(game.controlMode){
                    case ACCELEROMETER:
                        game.controlMode = AndroidGame.ControlMode.MANUAL;
                        break;
                    case MANUAL:
                        game.controlMode = AndroidGame.ControlMode.ACCELEROMETER;
                        break;
                }
                System.out.println("ControlMode " + game.controlMode.toString());
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(controlModeButton);
    }

    private void initJumpModeButton() {
        Button jumpModeButton = new Button();
        jumpModeButton.setWidth(SCREEN_X);
        jumpModeButton.setHeight(90);
        jumpModeButton.setX(-SCREEN_X / 2);
        jumpModeButton.setY(400 - CAMERA_Y_DIFFERENCE);
        jumpModeButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO control mode icon
                switch(game.jumpMode){
                    case AUTO:
                        game.jumpMode = AndroidGame.JumpMode.MANUAL;
                        break;
                    case MANUAL:
                        game.jumpMode = AndroidGame.JumpMode.AUTO;
                        break;
                }
                System.out.println("JumpMode " + game.jumpMode.toString());
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(jumpModeButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(settingsImg, camera.position.x - AndroidGame.SCREEN_X / 2, camera.position.y - AndroidGame.SCREEN_Y / 2, SCREEN_X, SCREEN_Y);
        batch.end();
    }
}
