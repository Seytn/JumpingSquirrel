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

/**
 * Created by ksikorski on 22.12.2016.
 */

public class BonusObject extends Image {

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

        public static BonusType getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public BonusType type;
    private Sound eatSound;

    public BonusObject(BonusType type, int startingX, int startingY) {
        super(getTexture(type));
        this.type = type;


        this.setOrigin(type.width/2, type.height/2);
        this.setSize(type.width, type.height);


        this.setPosition(startingX, startingY);
        eatSound = Assets.sharedInstance.assetManager.get("sounds/eat.mp3",Sound.class);
    }

    public void playSound() {
        eatSound.play();
    }

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

    public void move() {
        int randomX = MathUtils.random(-20,(int) SCREEN_X/2+30);
        this.setX(randomX);
        Action move = Actions.forever(Actions.sequence(
                Actions.moveBy(SCREEN_X/2,0,2.0f),
                Actions.moveBy(-SCREEN_X/2,0,2.0f)
        ));
        Action tremble = Actions.parallel(
                move,
                Actions.forever(Actions.rotateBy(360.0f,0.5f))
        );
        this.addAction(tremble);
    }


    public void fallDown() {
        Action fall = Actions.forever(Actions.sequence(
                Actions.moveBy(-SCREEN_Y,0,4.0f)
        ));
        Action tremble = Actions.parallel(
                fall,
                Actions.forever(Actions.rotateBy(-360.0f,1.5f))
        );
        this.addAction(tremble);
    }

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
