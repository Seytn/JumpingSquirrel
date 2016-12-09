package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by ksikorski on 09.12.2016.
 */

public class ControlModeSelectButton extends Button {
    public ControlModeSelectButton(final ClickCallback callback) {
        super(prepareControlModeButtonStyle());
        init(callback);
    }

    private static Button.ButtonStyle prepareControlModeButtonStyle() {
//        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("game_resume.atlas"));
//        Skin skin = new Skin(atlas);
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
//        buttonStyle.up = skin.getDrawable("button_01");
//        buttonStyle.down = skin.getDrawable("button_02");

        return buttonStyle;
    }

    private void init(final ClickCallback callback) {
        this.setWidth(200);
        this.setHeight(90);
        this.setX(40);
        this.setY(40);
        this.setDebug(true);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                callback.onClick();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
}
