package com.mygdx.game.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.assets.Assets;

import static com.mygdx.game.AndroidGame.isSoundMuted;


/**
 * Created by Kamil on 2016-07-23.
 */
public class JumpPlayer extends Image {

    public final Float speed = 500.0F;
    private Sound jumpSound;
    public boolean canJump = true;

    public float jumpSpeed;

    private float greatestHeight = 0;

    /**
     * Player constructor, sets texture and size.
     * @param texture passed texture
     */
    public JumpPlayer(Texture texture) {
        super(texture);
        this.setSize(texture.getWidth() / 5, texture.getHeight() / 5);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        jumpSound = Assets.sharedInstance.assetManager.get("sounds/jump.ogg",Sound.class);
    }

    /**
     * when jump is called jumpSpeed is increased, sound is played and roll animation is added
     */
    public void jump(){
        if(canJump){
            jumpSpeed += 875;
            canJump = false;
            if (!isSoundMuted) jumpSound.play();
            roll();
        }
    }

    /**
     * roll animation for a player
     */
    private void roll() {
        Action jumpAnimation = Actions.sequence(
                Actions.sizeBy(20.0f, 20.0f, 0.2f),
                Actions.sizeBy(-20.0f, -20.0f, 0.2f)
        );
        Action roll = Actions.parallel(
                jumpAnimation,
                Actions.rotateBy(360.0f,0.9f)
        );
        this.addAction(roll);
    }

    public float getGreatestHeight() {
        return greatestHeight;
    }

    public void setGreatestHeight(float greatestHeight) {
        if (greatestHeight > this.greatestHeight){
            this.greatestHeight = greatestHeight;
        }
    }
}
