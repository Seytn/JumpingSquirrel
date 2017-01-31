package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;

import static com.mygdx.game.AndroidGame.SCREEN_X;

/**
 * Created by Kamil on 2016-07-23.
 */
public class Platform extends Image {

    public final static String GRASS_BROWN = "textures/grass_brown.png";

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
                Texture newTexture = new Texture(GRASS_BROWN);
                this.setDrawable(new SpriteDrawable(new Sprite(newTexture)));
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


    public void disintegrate() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                removeAction();
            }
        }, 1);

    }

    private void removeAction() {
        this.remove();
    }
}

   