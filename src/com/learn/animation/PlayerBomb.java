package com.learn.animation;

import javax.swing.*;
import java.awt.*;

public class PlayerBomb extends AnimatedObject {
    // start time pointer, for many uses
    private long startTime;

    // delay of the moving
    private int delay = 30;

    private Direction direction;
    private int speed = 20;
    private boolean shoot;

    public PlayerBomb(ImageIcon imageThatHasSprites, int numberOfSprites) {
        super(imageThatHasSprites, numberOfSprites);

        //Now we initiate the timer, so we can use in the update method
        startTime = System.currentTimeMillis();
        direction = Direction.RIGHT;

    }


    @Override
    // update method
    public void update() {
        long elapsed = System.currentTimeMillis() - startTime;
        if (elapsed >= delay) {
            if (shoot) {
                this.y = this.y - speed;
                if (this.y <= 0) {
                    this.y = GamePanel.HEIGHT + this.width;
                    this.shoot = false;
                }
            }
            startTime = System.currentTimeMillis();
        }
        animation.update();

    }//end update


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
