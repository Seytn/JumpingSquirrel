package com.mygdx.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Kamil on 2016-07-24.
 */
public class Assets implements Disposable {

    public final AssetManager manager = new AssetManager();

    public void load(){
        manager.load("textures/player.png", Texture.class);
        manager.load("textures/platform.png", Texture.class);
        manager.load("textures/grass.png", Texture.class);
        manager.load("textures/clouds.png", Texture.class);

        manager.load("sounds/theme.mp3",Music.class);
        manager.load("sounds/jump.ogg",Sound.class);
        manager.load("sounds/gameover.mp3",Sound.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
