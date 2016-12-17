package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Kamil on 2016-07-23.
 */
public class Platform extends Image {

    public boolean reached = false;

    public Platform (Texture texture) {
        super(texture);
        this.setSize(265, 85);
    }


}
