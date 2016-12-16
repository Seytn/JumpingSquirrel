package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by Kamil on 2016-12-17.
 */

public class Walls {

    Texture texture;
    Stage stage;

    public Image leftWall;
    public Image rightWall;

    public Walls(Texture texture, Stage stage) {
        this.texture = texture;
        this.stage = stage;

        makeWalls();
    }

    private void makeWalls() {
        leftWall = new Image(texture);
        rightWall = new Image(texture);

        leftWall.setSize(260, 2000);
        leftWall.setX(-270);
        leftWall.setY(-SCREEN_Y);

        rightWall.setSize(260, 1900);
        rightWall.setX(630);
        rightWall.setY(-SCREEN_Y);
    }
}
