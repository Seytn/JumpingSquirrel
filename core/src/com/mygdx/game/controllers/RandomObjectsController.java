package com.mygdx.game.controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.AndroidGame;
import com.mygdx.game.entities.BonusObject;
import com.mygdx.game.entities.JumpPlayer;

import java.util.ArrayList;

import static com.mygdx.game.AndroidGame.SCREEN_X;
import static com.mygdx.game.AndroidGame.SCREEN_Y;

/**
 * Created by ksikorski on 22.12.2016.
 */

public class RandomObjectsController {

    private float spawnTime;
    private AndroidGame game;
    private Stage stage;
    private JumpPlayer player;

    BonusObject bonusObject;
    public ArrayList<BonusObject> bonusList = new ArrayList<BonusObject>();

    private int startingX;
    private int startingY;
    
    public RandomObjectsController(AndroidGame game, Stage stage, JumpPlayer player) {
        this.game = game;
        this.stage = stage;
        this.player = player;
        init();

    }

    private void init() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                randomizeSpawnTime();

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        addObjectToStage();
                    }
                }, spawnTime);
            }
        }, 0, 3.0f);
    }

    private void addObjectToStage() {
        bonusObject = null;
        startingX = (int) MathUtils.random(-10,SCREEN_X - 150);
        startingY = (int) (MathUtils.random(15,300) + SCREEN_Y + player.getY());
        BonusObject.BonusType randomType = BonusObject.BonusType.getRandom();
        switch(randomType){
            case FALLING_NUT: {
                bonusObject = new BonusObject(BonusObject.BonusType.FALLING_NUT, startingX, startingY);
                bonusObject.fallDown();
                break;
            }
            case SIMPLE_NUT: {
                bonusObject = new BonusObject(BonusObject.BonusType.SIMPLE_NUT, startingX, startingY);
                bonusObject.tremble();
                break;
            }
//            case ROTTING_NUT: {
            //TODO
//                bonusObject = new BonusObject(BonusObject.BonusType.ROTTING_NUT, game, startingX, startingY);
//                break;
//            }
//            case BIG_NUT: {
            //TODO
//                bonusObject = new BonusObject(BonusObject.BonusType.BIG_NUT, game, startingX, startingY);
//                break;
//            }
            case POISON: {
                bonusObject = new BonusObject(BonusObject.BonusType.POISON, startingX, startingY);
                bonusObject.move();
                break;
            }
            default: {
                bonusObject = new BonusObject(BonusObject.BonusType.SIMPLE_NUT, startingX, startingY);
            }
        }
        bonusList.add(bonusObject);
        stage.addActor(bonusObject);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5.0f,12.0f);
    }

    public void eraseObjects(){
        for (BonusObject o : bonusList){
            o.remove();
        }
        bonusList.clear();
    }

}
