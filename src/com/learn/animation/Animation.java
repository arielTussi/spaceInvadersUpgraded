package com.learn.animation;

import javax.swing.*;

public class Animation {
    private int currentSprite;
    private ImageIcon[] sprites;
    private long startTime;
    private long delay = 50;
    private int rowLength;
    private int fromSprite;
    private int toSprite;

    private boolean playedOnce = true;

    //update method
    public void update() {
        /**
         * timer determines witch Sprite of the image is going be return every time
         * In general we use Timers in our program to determine what time an object will appear
         * in our screen and in the meantime what actions will do
         * All objects have a timer...
         */
        long elapsed = (System.nanoTime() - startTime) / 1000000;

        /**
         * Now if we set the delay of the animation to xxx millis
         * we want to start the animation after xxx millis when we start the game.
         * beCare, if we set big delay then our animation is going be very slow
         */
        if (elapsed > delay) {
            currentSprite++;
            startTime = System.nanoTime();
        }

        // start again from the first sprite
        if (currentSprite == toSprite) {
            currentSprite = fromSprite;
            playedOnce = true;

        }
    }//end update

    public ImageIcon getImage() {
        return sprites[currentSprite];
    }

    public int getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(int currentSprite) {
        this.currentSprite = currentSprite;
    }

    public ImageIcon[] getSprites() {
        return sprites;
    }

    public void setSprites(ImageIcon[] sprites) {

        this.sprites = sprites;

        fromSprite = 0;
        toSprite = sprites.length;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }

    public boolean isPlayedOnce() {
        return playedOnce;
    }

    public void setPlayedOnce(boolean playedOnce) {
        this.playedOnce = playedOnce;
    }

    public int getFromSprite() {
        return fromSprite;
    }

    public void setFromSprite(int fromSprite) {
        currentSprite = fromSprite;
        this.fromSprite = fromSprite;
    }

    public int getToSprite() {
        return toSprite;
    }

    public void setToSprite(int toSprite) {
        this.toSprite = toSprite;
    }
}
