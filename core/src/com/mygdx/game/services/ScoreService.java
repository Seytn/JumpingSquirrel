package com.mygdx.game.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.util.Collections;
import java.util.Hashtable;

/**
 * Created by ksikorski on 16.12.2016.
 */

public class ScoreService {
    private static final String GAME_PREFS = "com.mygdx.game.jumping.prefs";
    private static final String GAME_SCORE = "com.mygdx.game.jumping.score";
    private static final String GAME_BEST = "com.mygdx.game.jumping.best";

    private static final String GAME_TOP_SCORES = "com.mygdx.game.jumping.bestScoresTable";

    private Preferences prefs;

    private int points = 0;
    private int bestScore = 0;

    private Json json = new Json();


    public Array<Integer> getBestScores() {
        return bestScores;
    }

    private Array<Integer> bestScores = new Array<Integer>();
    private Hashtable<String, String> hashTable = new Hashtable<String, String>();


    public ScoreService() {
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        points = prefs.getInteger(GAME_SCORE);
        bestScore = prefs.getInteger(GAME_BEST);

        String serializedScores = prefs.getString(GAME_TOP_SCORES);
        bestScores = json.fromJson(Array.class, serializedScores);

        try {
            int arraySize = bestScores.size;
        } catch (NullPointerException e) {
            bestScores = new Array<Integer>();
        }
    }

    public void addPoints(int points) {
        this.points += points;
        checkIfBestScore();
    }

    public void addPlatforPoints() {
        points += 1;
        checkIfBestScore();
    }

    private void checkIfBestScore() {
        if (points > bestScore) {
            bestScore = points;
        }
    }

    public void saveScore(){
        if (points > 0 ) {
            bestScores.add(points);
            bestScores.sort(Collections.reverseOrder());
        }
    }

    public void resetScore() {
        points = 0;
    }

    public void updateSavedScore() {
        prefs.putInteger(GAME_SCORE, points);
        prefs.putInteger(GAME_BEST, bestScore);
        prefs.putString(GAME_TOP_SCORES, json.toJson(bestScores));
//        hashTable.put(GAME_TOP_SCORES, json.toJson(bestScores));
//        prefs.put(hashTable);
        prefs.flush();
    }

    public int getPoints() {
        return points;
    }

    public int getBestScore() {
        return bestScore;
    }
}
