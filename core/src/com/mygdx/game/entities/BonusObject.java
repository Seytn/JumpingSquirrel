package com.mygdx.game.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.assets.Assets;

import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;
import static com.mygdx.game.AndroidGame.isSoundMuted;

/**
 * Created by ksikorski on 22.12.2016.
 */

public class BonusObject extends Image {

    /**
     * BonusType enum. All types has their own dimensions.
     */
    public enum BonusType{
        SIMPLE_NUT(90,70),
        FALLING_NUT(60,110),
//        ROTTING_NUT(70,110),
//        BIG_NUT(110,85),
        POISON(70,90);

        int width, height;

        BonusType(int width, int height){
            this.width = width;
            this.height = height;

        }

        /**
         * @return enum type as random
         */
        public static BonusType getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public BonusType type;
    private Sound eatSound;

    /**
     * BonusObject constructor. Sets object dimensions and tarting position on the screen
     * @param type enum of BonusType
     * @param startingX starting x position
     * @param startingY starting y position
     */
    public BonusObject(BonusType type, int startingX, int startingY) {
        super(getTexture(type));
        this.type = type;


        this.setOrigin(type.width/2, type.height/2);
        this.setSize(type.width, type.height);


        this.setPosition(startingX, startingY);
        eatSound = Assets.sharedInstance.assetManager.get("sounds/eat.mp3",Sound.class);
    }

    /**
     * play sound when object is "eaten"
     */
    public void playSound() {
        if (!isSoundMuted) eatSound.play();
    }

    /**
     * @param type type of object
     * @return texture depends on object type
     */
    private static Texture getTexture(BonusType type) {
        switch(type){
            case POISON: {
                return Assets.sharedInstance.assetManager.get("textures/pepper.png", Texture.class);
            }
            case SIMPLE_NUT: {
                return Assets.sharedInstance.assetManager.get("textures/simple_nut.png", Texture.class);
            }
            case FALLING_NUT: {
                return Assets.sharedInstance.assetManager.get("textures/falling_nut.png", Texture.class);
            }
//            case ROTTING_NUT: {
//                //TODO
//            }
//            case BIG_NUT: {
//                //TODO
//            }
            default: return Assets.sharedInstance.assetManager.get("textures/simple_nut.png", Texture.class);
        }
    }

    /**
     * Animation of moving left and right forever, and fall a little
     */
    public void move() {
        int randomX = MathUtils.random(-20,(int) SCREEN_X/2+30);
        this.setX(randomX);
        Action move = Actions.forever(Actions.sequence(
                Actions.moveBy(SCREEN_X/1.4f,0,1.5f),
                Actions.moveBy(-SCREEN_X/1.4f,0,1.5f)
        ));
        Action tremble = Actions.parallel(
                move,
                Actions.forever(Actions.rotateBy(360.0f,0.5f))
        );
        Action fall = Actions.moveBy(0,-SCREEN_Y,8.0f);
        this.addAction(tremble);
        this.addAction(fall);
    }

    /**
     * falling down animation
     */
    public void fallDown() {
        Action fall = Actions.forever(Actions.sequence(
                Actions.moveBy(0,-SCREEN_Y,5.0f)
        ));
        Action tremble = Actions.forever(Actions.rotateBy(-360.0f,1.5f));
        this.addAction(tremble);
        this.addAction(fall);
    }

    /**
     * Animation for "pulse"
     */
    public void tremble() {
        Action tremble = Actions.forever(Actions.sequence(
                Actions.sizeBy(20.0f, 20.0f, 0.1f),
                Actions.sizeBy(-20.0f, -20.0f, 0.1f),
                Actions.sizeBy(20.0f, 20.0f, 0.1f),
                Actions.sizeBy(-20.0f, -20.0f, 0.1f),
                Actions.delay(1.0f)
        ));
        this.addAction(tremble);
    }
}
