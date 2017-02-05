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
import static com.mygdx.game.AndroidGame.isMusicMuted;
import static com.mygdx.game.AndroidGame.isSoundMuted;

/**
 * Created by Kamil on 2016-12-19.
 */

public class SettingsScreen extends AbstractScreen {

    Texture settingsImg;
    Texture onActive;
    Texture onInactive;

    SettingsScreen(AndroidGame game, JumpPlayer player) {
        super(game, player);
        init();
    }

    private void init() {
        settingsImg = Assets.sharedInstance.assetManager.get("textures/settingsScreen.png", Texture.class);
        onActive = Assets.sharedInstance.assetManager.get("textures/on-active.png", Texture.class);
        onInactive = Assets.sharedInstance.assetManager.get("textures/on-inactive.png", Texture.class);
        initMainMenuButton();
        initMusicButton();
        initSoundsButton();
        initControlModeButton();
    }

    private void initControlModeButton() {
        Button controlModeButton = new Button();
        controlModeButton.setWidth(SCREEN_X);
        controlModeButton.setHeight(90);
        controlModeButton.setX(-SCREEN_X / 2);
        controlModeButton.setY(680 - CAMERA_Y_DIFFERENCE);
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

    private void initMusicButton() {
        Button musicButton = new Button();
        musicButton.setWidth(SCREEN_X);
        musicButton.setHeight(90);
        musicButton.setX(-SCREEN_X / 2);
        musicButton.setY(550 - CAMERA_Y_DIFFERENCE);
        musicButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO control mode icon
                if (!isMusicMuted) {
                    MainMenuScreen.music.setVolume(0.0f);
                    isMusicMuted = !isMusicMuted;
                    System.out.println("Music off");
                } else {
                    MainMenuScreen.music.setVolume(0.3f);
                    isMusicMuted = !isMusicMuted;
                    System.out.println("Music on");
                }

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        stage.addActor(musicButton);
    }

    private void initSoundsButton() {
        Button soundsButton = new Button();
        soundsButton.setWidth(SCREEN_X);
        soundsButton.setHeight(90);
        soundsButton.setX(-SCREEN_X / 2);
        soundsButton.setY(390 - CAMERA_Y_DIFFERENCE);
        soundsButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                //TODO control mode icon
                if (!isSoundMuted) {
                    isSoundMuted = !isSoundMuted;
                    System.out.println("Sounds off ");
                } else {
                    isSoundMuted = !isSoundMuted;
                    System.out.println("Sounds on ");
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(soundsButton);
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


    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(settingsImg, camera.position.x - AndroidGame.SCREEN_X / 2, camera.position.y - AndroidGame.SCREEN_Y / 2, SCREEN_X, SCREEN_Y);
        if (game.controlMode == AndroidGame.ControlMode.MANUAL) {
            batch.draw(onActive, 180, 400, 80, 80);
        } else {
            batch.draw(onInactive, 180, 400, 80, 80);
        }

        if (!isMusicMuted) {
            batch.draw(onActive, 180, 260, 80, 80);
        } else {
            batch.draw(onInactive, 180, 260, 80, 80);
        }

        if (!isSoundMuted) {
            batch.draw(onActive, 180, 120, 80, 80);
        } else {
            batch.draw(onInactive, 180, 120, 80, 80);
        }

        batch.end();
    }
}


//    private void initJumpModeButton() {
//        Button jumpModeButton = new Button();
//        jumpModeButton.setWidth(SCREEN_X);
//        jumpModeButton.setHeight(90);
//        jumpModeButton.setX(-SCREEN_X / 2);
//        jumpModeButton.setY(400 - CAMERA_Y_DIFFERENCE);
//        jumpModeButton.addListener(new ClickListener(){
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                //TODO control mode icon
//                switch(game.jumpMode){
//                    case AUTO:
//                        game.jumpMode = AndroidGame.JumpMode.MANUAL;
//                        break;
//                    case MANUAL:
//                        game.jumpMode = AndroidGame.JumpMode.AUTO;
//                        break;
//                }
//                System.out.println("JumpMode " + game.jumpMode.toString());
//                return super.touchDown(event, x, y, pointer, button);
//            }
//        });
//        stage.addActor(jumpModeButton);
//    }