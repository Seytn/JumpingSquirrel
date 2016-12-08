package com.mygdx.game.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.assets.Assets;


/**
 * Created by Kamil on 2016-07-23.
 */
public class JumpPlayer extends Rectangle {


    private Sound jumpSound;
    private Texture texture;
    public boolean canJump = true;

    public float jumpSpeed;

    public JumpPlayer(Texture texture, Assets assets) {
        this.texture = texture;
        this.height = texture.getHeight();
        this.width = texture.getWidth();
        jumpSound = assets.manager.get("jump.ogg",Sound.class);

    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void jump(){
        if(canJump){
            jumpSpeed += 900;
            canJump = false;
            jumpSound.play();
        }
    }

}
