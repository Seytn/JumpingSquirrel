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

    public static final AssetManager assetManager = new AssetManager();

    public static void loadData(){
        assetManager.load("textures/player.png", Texture.class);
        assetManager.load("textures/platform.png", Texture.class);
        assetManager.load("textures/grass.png", Texture.class);
        assetManager.load("textures/grass2.png", Texture.class);
        assetManager.load("textures/clouds.png", Texture.class);
        assetManager.load("textures/log.png", Texture.class);
        assetManager.load("textures/background_splash.png", Texture.class);

        assetManager.load("sounds/theme.mp3",Music.class);
        assetManager.load("sounds/jump.ogg",Sound.class);
        assetManager.load("sounds/gameover.mp3",Sound.class);
    }



    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
