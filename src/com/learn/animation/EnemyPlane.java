package com.learn.animation;


import javax.swing.*;
import java.awt.*;

public class EnemyPlane extends AnimatedObject {
    // start time pointer, for many uses
    private long startTime;

    // delay of the moving
    private int delay = 30;

    private Direction direction;
    private Boolean alive;



    public EnemyPlane(ImageIcon imageThatHasSprites, int numberOfSprites) {
        super(imageThatHasSprites, numberOfSprites);

        //Now we initiate the timer, so we can use in the update method
        startTime = System.currentTimeMillis();
        direction = Direction.RIGHT;
        this.alive = true;

    }

    @Override
    // update method
    public void update() {

        long elapsed = System.currentTimeMillis() - startTime;

        if (elapsed >= delay) {
            if (direction == Direction.RIGHT) {
                // here is our code
                this.x = this.x + 5;

                if (x > GamePanel.WIDTH - this.width) {
                    direction = Direction.LEFT;
                    this.y = this.y + 20;

                }
            } else if (direction == Direction.LEFT) {

                // here is our code
                this.x = this.x - 5;
                if (x < -width + this.width) {
                    direction = Direction.RIGHT;
                    ;
                }

            }
            startTime = System.currentTimeMillis();
        }
        animation.update();

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }
}
