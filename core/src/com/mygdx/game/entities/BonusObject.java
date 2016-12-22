package com.mygdx.game.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.assets.Assets;

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

    private BonusType type;
    private AndroidGame game;
    private Sound spawnSound;

    public BonusObject(BonusType type, final AndroidGame game, int startingX, int startingY) {
        super(getTexture(type));

        this.game = game;
        this.type = type;


        this.setOrigin(type.width/2, type.height/2);
        this.setSize(type.width, type.height);


        this.setPosition(startingX, startingY);
//        spawnSound = Assets.sharedInstance.assetManager.get("sounds/eat.ogg",Sound.class);
//        playSound();
    }

    private void playSound() {
        spawnSound.play();
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
}
