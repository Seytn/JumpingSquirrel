package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.UI.SimpleLabel;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.entities.JumpPlayer;
import com.mygdx.game.entities.Platform;

public class AndroidGame extends ApplicationAdapter implements InputProcessor {

    public enum ControlMode {
        MANUAL, ACCELEROMETER
    }

    public enum JumpMode {
        MANUAL, AUTO
    }
    private float screenY = 800, screenX = 480;

	SpriteBatch batch;
	private Assets assets;

	private JumpPlayer player;
	private Array<Platform> platformArray;

	private Music music;
	private Texture playerTexture, toiletClosedTexture, toiletOpenedTexture;

	private OrthographicCamera camera;
	private float gravity = -20;

    private SimpleLabel accXValueLabel;

    private ControlMode controlMode = ControlMode.ACCELEROMETER;
    private JumpMode jumpMode = JumpMode.MANUAL;

	@Override
	public void create () {
		assets = new Assets();
		assets.load();
		assets.manager.finishLoading();

		if(assets.manager.update()) {
            loadData();
            init();
            initAccXValueLabel();
        }
	}

	private void init() {
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);

		music.setVolume(0.3f);
		music.play();
		camera = new OrthographicCamera(screenX,screenY);

		player = new JumpPlayer(playerTexture,assets);
		player.setX(screenX / 2);
		platformArray = new Array<Platform>();

		for(int i = 1; i<10; i++){
			Platform p = new Platform(toiletClosedTexture);
			p.height = 110;
			p.x = MathUtils.random(380);
			p.y = 250 * i;
			platformArray.add(p);
		}

		Platform p = new Platform(toiletOpenedTexture);
		p.height = 100;
		p.x = MathUtils.random(400);
		p.y = 250 * 10;
		platformArray.add(p);

	}

    private void initAccXValueLabel() {
        accXValueLabel = new SimpleLabel("");
    }

	private void loadData() {
		playerTexture = assets.manager.get("player.png",Texture.class);
		toiletClosedTexture = assets.manager.get("toilet_closed.png",Texture.class);
		toiletOpenedTexture = assets.manager.get("toilet_opened.png",Texture.class);
		music = assets.manager.get("theme.mp3", Music.class);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		for(Platform p : platformArray){
			p.draw(batch);
		}
		player.draw(batch);
        accXValueLabel.draw(batch);

		batch.end();
	}

	private void update() {
		handleInput();


		camera.position.set(player.x + player.width/2, player.y + 300, 0);
		camera.update();
        labelPositionUpdate();
        playerPositionUpdate();
	}

    private void playerPositionUpdate() {
        player.y += player.jumpSpeed * Gdx.graphics.getDeltaTime();

        if(player.y > 0){
            player.jumpSpeed += gravity;
        } else {
            player.y = 0;
            player.canJump = true;
            player.jumpSpeed = 0;
        }

        for (Platform p : platformArray){
            if(isPlayerOnPlatform(p)){
                player.y = p.y + p.height-10;
                player.canJump = true;
                player.jumpSpeed = 0;
                if (player.y >= 250 * 10){
                    endGame();

                }
            }
        }
    }

    private void labelPositionUpdate() {
        accXValueLabel.setPosition(camera.position.x + 100,camera.position.y + 350);
    }

    private void endGame() {
		Sound endGameSound;
		//TODO;
		//endGameSound = assets.manager.get("endgame.ogg",Sound.class);
		//endGameSound.play();
		Gdx.app.exit();
	}

	private boolean isPlayerOnPlatform(Platform p) {
		return player.jumpSpeed <= 0 && player.overlaps(p) && player.y > p.y;
	}

	private void handleInput() {
        if (controlMode == ControlMode.ACCELEROMETER) {
            float accelerometerX = Gdx.input.getAccelerometerX();
            accXValueLabel.setText(Float.toString(accelerometerX));
            if(accelerometerX > 1.2 && player.x > 0){
                player.x -= 500 * Gdx.graphics.getDeltaTime();
            }
            if(accelerometerX < -1.2 && player.x < screenX){
                player.x += 500 * Gdx.graphics.getDeltaTime();
            }
        }

        switch (jumpMode) {
            case MANUAL: {
                if (Gdx.input.justTouched()) {
                    player.jump();
                }
                break;
            }
            case AUTO: {
                player.jump();
                break;
            }
        }
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
			return false;

	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

	 return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (controlMode == ControlMode.MANUAL) {
            if (screenX < 240) {
                player.x -= 500 * Gdx.graphics.getDeltaTime();

                return true;
            }
            if (screenX > 240) {
                player.x += 500 * Gdx.graphics.getDeltaTime();
                return true;
            }
        }
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
//1280x720