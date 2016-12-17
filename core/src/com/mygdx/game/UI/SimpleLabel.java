package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Kamil on 2016-08-10.
 */
public class SimpleLabel extends Label{

    private static final Color color = Color.ORANGE;

    public SimpleLabel(CharSequence text) {
        super(text, labelStyle());
    }

    private static LabelStyle labelStyle() {
        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(2.0f);
        return new LabelStyle(bitmapFont, color);
    }
}
