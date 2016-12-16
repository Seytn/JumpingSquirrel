package com.mygdx.game.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by ksikorski on 16.12.2016.
 */

public class ScoreService {
    public static final String GAME_PREFS = "com.mygdx.game.jumping.prefs";
    public static final String GAME_SCORE = "com.mygdx.game.jumping.score";

    private Preferences prefs;

    private int points;

    public ScoreService() {
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        points = prefs.getInteger(GAME_SCORE);
    }

    public void addPoints(int points) {
        points += points;
    }

    public void addPlatforPoints() {
        points += 10;
    }

    public void resetScore() {
        points = 0;
    }

    public void updateSavedScore() {
        prefs.putInteger(GAME_SCORE, points);
        prefs.flush();
    }

    public int getPoints() {
        return points;
    }
}
