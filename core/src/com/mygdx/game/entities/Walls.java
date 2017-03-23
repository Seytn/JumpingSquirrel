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

    /**
     * Walls constructor. Makes two walls.
     * @param texture texture for walls
     * @param stage stage which to walls will be added
     */
    public Walls(Texture texture, Stage stage) {
        this.texture = texture;
        this.stage = stage;

        makeWalls();
    }

    /**
     * set texture, dimensions and position of walls
     */
    private void makeWalls() {
        leftWall = new Image(texture);
        rightWall = new Image(texture);

        leftWall.setSize(300, 2000);
        leftWall.setX(-250);
        leftWall.setY(-SCREEN_Y);

        rightWall.setSize(300, 1900);
        rightWall.setX(700);
        rightWall.setY(-SCREEN_Y);
    }
}
