package com.mygdx.game.entities;

/**
 * Created by ksikorski on 17.12.2016.
 */

public class Checkpoint {

    int value;
    Boolean reached = false;

    public Checkpoint(int platformAdded) {
        value = platformAdded;
        reached = false;
    }

    public int getValue() {
        return value;
    }

    public Boolean getReached() {
        return reached;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setReached(Boolean reached) {
        this.reached = reached;
    }
}
