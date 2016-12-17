package com.mygdx.game.services;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.entities.Platform;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ksikorski on 17.12.2016.
 */

public class PlatformService {

    AndroidGame game;
    GameScreen gameScreen;
    Stage stage;
    public Array<Platform> platformArray;

    public PlatformService() {
        init();
    }

    private void init() {
        generatePlatforms();
    }

    private void generatePlatforms() {
        platformArray = new Array<Platform>();

        for(int i = 1; i<10; i++){
            Platform p = new Platform(gameScreen.grassTexture);
            p.setX(MathUtils.random(580));
            p.setY(300 * i);
            platformArray.add(p);
            stage.addActor(p);
        }

        Platform p = new Platform(gameScreen.platformTexture);
        p.setX(MathUtils.random(600));
        p.setY(300 * 10);
        platformArray.add(p);
        stage.addActor(p);
    }
}
