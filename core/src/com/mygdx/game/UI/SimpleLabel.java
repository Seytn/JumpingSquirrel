package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Kamil on 2016-08-10.
 */
public class SimpleLabel extends Label{
    public SimpleLabel(CharSequence text) {
        super(text, new Label.LabelStyle(new BitmapFont(), Color.RED));

        init();


    }

    private void init() {
        this.setX(370);
        this.setY(650);
    }
}
