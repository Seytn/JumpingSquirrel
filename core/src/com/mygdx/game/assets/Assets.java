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

    public static final Assets sharedInstance = new Assets();
    public final AssetManager assetManager = new AssetManager();

    public void loadData(){
        //GAMEPLAY SCREEN
        assetManager.load("textures/player.png", Texture.class);
        assetManager.load("textures/grass.png", Texture.class);
        assetManager.load("textures/grass2.png", Texture.class);
        assetManager.load("textures/clouds.png", Texture.class);
        assetManager.load("textures/log.png", Texture.class);

        //BONUS OBJECTS
        assetManager.load("textures/simple_nut.png", Texture.class);
        assetManager.load("textures/falling_nut.png", Texture.class);
        assetManager.load("textures/pepper.png", Texture.class);

        //OTHER SCREENS
        assetManager.load("textures/splashScreen.png", Texture.class);
        assetManager.load("textures/mainMenuScreen.png", Texture.class);
        assetManager.load("textures/endGameScreen.png", Texture.class);
        assetManager.load("textures/highScoreScreen.png", Texture.class);
        assetManager.load("textures/settingsScreen.png", Texture.class);
        assetManager.load("textures/on-active.png", Texture.class);
        assetManager.load("textures/on-inactive.png", Texture.class);
        assetManager.load("textures/backToMenu.png",Texture.class);
        assetManager.load("textures/arrow-left.png",Texture.class);
        assetManager.load("textures/arrow-right.png",Texture.class);

        //SOUNDS
        assetManager.load("sounds/theme.mp3",Music.class);
        assetManager.load("sounds/jump.ogg",Sound.class);
        assetManager.load("sounds/gameover.mp3",Sound.class);
        assetManager.load("sounds/eat.mp3",Sound.class);
    }



    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
