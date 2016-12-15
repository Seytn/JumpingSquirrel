package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Kamil on 2016-07-23.
 */
public class Platform extends Image {

    private Texture texture;

    public Platform (Texture texture) {
        this.texture = texture;
        this.setHeight(texture.getHeight());
        this.setWidth(texture.getWidth());
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, this.getX(), this.getY());
    }

}
