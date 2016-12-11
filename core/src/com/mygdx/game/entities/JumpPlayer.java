package com.mygdx.game.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.assets.Assets;


/**
 * Created by Kamil on 2016-07-23.
 */
public class JumpPlayer extends Image {

    public final Float speed = 500.0F;
    private Sound jumpSound;
    private Texture texture;
    public boolean canJump = true;

    public float jumpSpeed;

    public JumpPlayer(Texture texture, Assets assets) {
        this.texture = texture;
        this.setHeight(texture.getHeight());
        this.setWidth(texture.getWidth());
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        jumpSound = assets.manager.get("jump.ogg",Sound.class);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, this.getX(), this.getY());
    }

    public void jump(){
        if(canJump){
            jumpSpeed += 900;
            canJump = false;
            jumpSound.play();
        }
    }

}
