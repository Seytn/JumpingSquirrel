package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.mygdx.game.AndroidGame.SCREEN_X;

/**
 * Created by Kamil on 2016-07-23.
 */
public class Platform extends Image {

    public enum PlatformType {
        STANDARD,
        MOVING,
        DISINTEGRATING;

        public static PlatformType getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public boolean reached = false;
    public PlatformType type;

    public Platform(Texture texture, PlatformType type) {
        super(texture);
        this.setSize(265, 85);
        this.type = type;

        handlePlatformType();
    }

    private void handlePlatformType() {
        switch (type) {
            case STANDARD: {
                break;
            }
            case MOVING: {
                addMoveAnimation();
                break;
            }
            case DISINTEGRATING: {
                addDisintegratingAnimation();
                break;
            }
        }

    }

    private void addMoveAnimation() {
        int randomX = MathUtils.random(-100,50);
        this.setX(randomX);
        Action move = Actions.forever(Actions.sequence(
                Actions.moveBy(SCREEN_X/2,0,1.0f),
                Actions.moveBy(-SCREEN_X/2,0,1.0f)
        ));
        this.addAction(move);
    }

    private void addDisintegratingAnimation() {
        Action shake = Actions.forever(Actions.sequence(
                Actions.moveBy(15,0,0.25f),
                Actions.moveBy(-15,0,0.25f),
                Actions.moveBy(15,0,0.25f),
                Actions.moveBy(-15,0,0.25f),
                Actions.delay(0.5f)
        ));
        this.addAction(shake);
    }

}

   