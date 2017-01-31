package com.mygdx.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.entities.Checkpoint;
import com.mygdx.game.entities.Platform;
import com.mygdx.game.screens.GameScreen;

import java.util.ArrayList;

/**
 * Created by ksikorski on 17.12.2016.
 */

public class PlatformController {

    private int defaultSpacing = 300;
    private GameScreen gameScreen;
    private Stage stage;
    public ArrayList<Platform> platformArray;
    private int platformAdded = 0;
    private Checkpoint checkpoint;

    public PlatformController(GameScreen gameScreen, Stage stage) {
        this.gameScreen = gameScreen;
        this.stage = stage;
        platformArray = new ArrayList<Platform>();
        init();
    }

    private void init() {
        prepareCheckpoint();
        generatePlatforms(0, 20, defaultSpacing, gameScreen.grassTexture);
    }

    private void prepareCheckpoint() {
        checkpoint = new Checkpoint(platformAdded);
    }

    private void generatePlatforms(int firstPlatform, int platformCountToAdd, int spacing, Texture texture) {
        for(int i = 1; i <= platformCountToAdd; i++){
            Platform.PlatformType randomType = Platform.PlatformType.getRandom();
            Platform p = new Platform(texture, randomType);
            p.setX(MathUtils.random(620) - 30);
            p.setY(spacing * (i + firstPlatform));
            platformArray.add(p);
            stage.addActor(p);
        }
        platformAdded = platformAdded + platformCountToAdd;
    }

    private void changeCheckpointValue() {
        checkpoint.setValue(platformAdded);
    }

    public void generateMorePlatforms() {
        if (gameScreen.player.getY() >= defaultSpacing * (checkpoint.getValue() - 3)){
            generatePlatforms(platformAdded, 20, defaultSpacing, gameScreen.grassTexture);
            changeCheckpointValue();
        }
    }
}
