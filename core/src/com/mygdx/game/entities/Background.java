package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by ksikorski on 16.12.2016.
 */

public class Background extends Image {

    /**
     * Background object constructor. Makes image depending on passed texture and sets size to
     * screen dimensions
     * @param texture
     */
    public Background (Texture texture) {
        super(texture);
        this.setSize(SCREEN_X, SCREEN_Y);
    }

}