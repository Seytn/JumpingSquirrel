package com.mygdx.game.services;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.Platform;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ksikorski on 17.12.2016.
 */

public class PlatformService {

    GameScreen gameScreen;
    Stage stage;
    public Array<Platform> platformArray;
    private int platformAdded = 0;

    public PlatformService(GameScreen gameScreen, Stage stage) {
        this.gameScreen = gameScreen;
        this.stage = stage;
        platformArray = new Array<Platform>();
        init();
    }

    private void init() {
        generatePlatforms(0, 20, 300, gameScreen.grassTexture);
    }

    private void generatePlatforms(int firstPlatform, int platformCountToAdd, int spacing, Texture testure) {
        for(int i = 1; i <= platformCountToAdd; i++){
            Platform p = new Platform(testure);
            p.setX(MathUtils.random(620) - 30);
            p.setY(spacing * (i + firstPlatform));
            platformArray.add(p);
            stage.addActor(p);
        }
        platformAdded = platformAdded + platformCountToAdd;
    }

    public void generateMorePlatforms() {
        generatePlatforms(platformAdded + 1, 20, 300, gameScreen.grassTexture);
    }

}
