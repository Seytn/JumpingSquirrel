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
        manager.load("player.png", Texture.class);
        manager.load("toilet_closed.png", Texture.class);
        manager.load("toilet_opened.png", Texture.class);
        manager.load("theme.mp3",Music.class);
        manager.load("jump.ogg",Sound.class);
        //manager.load("endgame.ogg",Sound.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
