package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.entities.JumpPlayer;

import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by ksikorski on 09.12.2016.
 */

public class AbstractScreen implements Screen {

    public static final float CAMERA_Y_DIFFERENCE = 300;
    protected final AndroidGame game;

    Stage stage;
    OrthographicCamera camera;
    SpriteBatch batch;
    public JumpPlayer player;

    /**
     * AbstractScreen constructor, sets fields game and player for future use.
     * Initializes camera, spriteBatch and stage.
     * @param game
     * @param player
     */
    AbstractScreen(AndroidGame game, JumpPlayer player) {
        this.game = game;
        this.player = player;

        createCamera();
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(SCREEN_X, SCREEN_Y, camera));
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * override method which is called every frame.
     * calls clearScreen and cameraUpdate methods
     * @param delta
     */
    @Override
    public void render(float delta) {
        clearScreen();
        cameraUpdate();
    }

    /**
     * set screen to one color
     */
    private void clearScreen(){
        Gdx.gl.glClearColor(0.8f, 0.8f, 0f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * update camera position depending on player position
     */
    private void cameraUpdate() {
        camera.position.set(player.getX() + player.getWidth()/2, player.getY() + CAMERA_Y_DIFFERENCE, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    /**
     * initialize camera as OrtographicCamera
     */
    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_X, SCREEN_Y);
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        game.scoreService.saveScore();
        game.scoreService.updateSavedScore();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        game.dispose();
    }
}
