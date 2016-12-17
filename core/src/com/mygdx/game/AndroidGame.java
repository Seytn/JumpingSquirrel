package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.entities.JumpPlayer;
import com.mygdx.game.screens.SplashScreen;
import com.mygdx.game.services.ScoreService;

public class AndroidGame extends Game {

    public enum ControlMode {
        MANUAL, ACCELEROMETER
    }

    public enum JumpMode {
        MANUAL, AUTO
    }
    public final static float SCREEN_Y = 1024, SCREEN_X = 600;
	public final static float GRAVITY = -20;

	public Assets assets;
	public ScoreService scoreService;

	public ControlMode controlMode = ControlMode.ACCELEROMETER;
	public JumpMode jumpMode = JumpMode.MANUAL;

	@Override
	public void create () {
		assets = new Assets();
		assets.load();
		assets.manager.finishLoading();
        initScoreService();

		if(assets.manager.update()) {
            Texture playerTexture = assets.manager.get("textures/player.png",Texture.class);
            JumpPlayer player = new JumpPlayer(playerTexture, assets);
            this.setScreen(new SplashScreen(this, player));
        }
	}

    private void initScoreService() {
        scoreService = new ScoreService();
    }
}