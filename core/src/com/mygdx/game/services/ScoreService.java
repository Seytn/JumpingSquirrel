package com.mygdx.game.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by ksikorski on 16.12.2016.
 */

public class ScoreService {
    private static final String GAME_PREFS = "com.mygdx.game.jumping.prefs";
    private static final String GAME_SCORE = "com.mygdx.game.jumping.score";
    private static final String GAME_BEST = "com.mygdx.game.jumping.best";

    private Preferences prefs;

    private int points = 0;
    private int bestScore = 0;

    public ScoreService() {
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        points = prefs.getInteger(GAME_SCORE);
        bestScore = prefs.getInteger(GAME_BEST);
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
        prefs.putInteger(GAME_BEST, bestScore);
        prefs.flush();
    }

    public int getPoints() {
        return points;
    }

    public int getBestScore() {
        return bestScore;
    }
}
