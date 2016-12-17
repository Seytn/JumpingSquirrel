package com.mygdx.game.services;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.Checkpoint;
import com.mygdx.game.entities.Platform;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ksikorski on 17.12.2016.
 */

public class PlatformService {

    private int defaultSpacing = 300;
    private GameScreen gameScreen;
    private Stage stage;
    public Array<Platform> platformArray;
    private int platformAdded = 0;
    private Checkpoint checkpoint;

    public PlatformService(GameScreen gameScreen, Stage stage) {
        this.gameScreen = gameScreen;
        this.stage = stage;
        platformArray = new Array<Platform>();
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
            Platform p = new Platform(texture);
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
