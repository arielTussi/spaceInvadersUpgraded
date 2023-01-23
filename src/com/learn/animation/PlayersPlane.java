package com.learn.animation;

import javax.swing.*;
import java.awt.*;

public class PlayersPlane extends AnimatedObject {
    // start time pointer, for many uses
    private long startTime;

    // delay of the moving
    private int delay = 20;


    public PlayersPlane(ImageIcon imageThatHasSprites, int numberOfSprites) {
        super(imageThatHasSprites, numberOfSprites);

        //Now we initiate the timer, so we can use in the update method
        startTime = System.currentTimeMillis();

    }

    @Override
    // update method
    public void update() {

    }//end update

    public void draw(JPanel panel) {
        if (isShow()) {
            Image image = animation.getImage().getImage();

            setIcon(new ImageIcon(image));

            setBounds(x, y, width, height);
            panel.add(this);
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

    public void setY()
    {
        this.y = 500;
    }


}
