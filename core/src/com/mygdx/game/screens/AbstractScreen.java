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

class AbstractScreen implements Screen {

    protected AndroidGame game;

    Stage stage;
    OrthographicCamera camera;
    SpriteBatch batch;
    JumpPlayer player;

    AbstractScreen(AndroidGame game, JumpPlayer player) {
        this.game = game;
        this.player = player;

        createCamera();
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(SCREEN_X, SCREEN_Y, camera));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        cameraUpdate();
    }

    private void clearScreen(){
        Gdx.gl.glClearColor(0.8f, 0.8f, 0f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void cameraUpdate() {
        camera.position.set(player.getX() + player.getWidth()/2, player.getY() + 300, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

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
