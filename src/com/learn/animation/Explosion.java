package com.learn.animation;

import javax.swing.*;
import java.awt.*;

public class Explosion extends AnimatedObject {
    // start time pointer, for many uses
    private long startTime;

    // delay of the moving
    private int delay = 100;


    public Explosion(ImageIcon imageThatHasSprites, int numberOfSprites, int rowLength) {
        super(imageThatHasSprites, numberOfSprites, rowLength);

        //Now we initiate the timer, so we can use in the update method
        startTime = System.currentTimeMillis();

    }

    @Override
    // update method
    public void update() {

        animation.update();

    }//end update

    public void draw(JPanel panel) {
        if (!animation.isPlayedOnce()) {
            if (isShow()) {
                Image image = animation.getImage().getImage();
                setIcon(new ImageIcon(image));
                setBounds(x, y, width, height);
                panel.add(this);
            }
        }
    }//end draw


    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}

