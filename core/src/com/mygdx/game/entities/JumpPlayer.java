package com.mygdx.game.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.assets.Assets;


/**
 * Created by Kamil on 2016-07-23.
 */
public class JumpPlayer extends Image {

    public final Float speed = 500.0F;
    private Sound jumpSound;
    public boolean canJump = true;

    public float jumpSpeed;

    public JumpPlayer(Texture texture, Assets assets) {
        super(texture);
        this.setSize(texture.getWidth() / 5, texture.getHeight() / 5);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        jumpSound = assets.manager.get("sounds/jump.ogg",Sound.class);
    }

    public void jump(){
        if(canJump){
            jumpSpeed += 950;
            canJump = false;
            jumpSound.play();
            roll();
        }
    }

    private void roll() {
        Action jumpAnimation = Actions.sequence(
                Actions.sizeBy(20.0f, 20.0f, 0.2f),
                Actions.sizeBy(-20.0f, -20.0f, 0.2f)
        );
        Action roll = Actions.parallel(
                jumpAnimation,
                Actions.rotateBy(360.0f,0.6f)
        );
        this.addAction(roll);
    }

}
