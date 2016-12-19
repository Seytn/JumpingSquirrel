package com.mygdx.game.entities;

/**
 * Created by ksikorski on 17.12.2016.
 */

public class Checkpoint {

    private int value;

    public Checkpoint(int platformAdded) {
        value = platformAdded;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

}
