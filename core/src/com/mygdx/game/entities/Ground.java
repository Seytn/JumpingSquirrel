package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.mygdx.game.screens.AbstractScreen.CAMERA_Y_DIFFERENCE;

/**
 * Created by Kamil on 2016-12-16.
 */

public class Ground extends Image {
    public Ground(Texture texture) {
        super(texture);
        this.setSize(1500, 570);
        this.setX(-400);
        this.setY(-CAMERA_Y_DIFFERENCE);
    }
}
