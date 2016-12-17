package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.entities.JumpPlayer;

import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by ksikorski on 17.12.2016.
 */

public class SplashScreen extends AbstractScreen {

    private Texture splashImg;

    public SplashScreen(final AndroidGame game, final JumpPlayer player) {
        super(game, player);
        init();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GameScreen(game, player));
            }
        },3.5f);
    }

    private void init() {
        //TODO implement assets assetManager
        splashImg = Assets.sharedInstance.assetManager.get("textures/background_splash.png", Texture.class);

        Button button = new Button();
        button.setWidth(SCREEN_X);
        button.setHeight(SCREEN_Y);
        button.setX(0);
        button.setY(0);
        button.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen(game, player));
                    }
                },0.2f);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(splashImg, camera.position.x - AndroidGame.SCREEN_X / 2, camera.position.y - AndroidGame.SCREEN_Y / 2, SCREEN_X, SCREEN_Y);
        batch.end();
    }
}
