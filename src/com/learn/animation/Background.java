package com.learn.animation;

import javax.swing.*;

public class Background extends AnimatedObject {
    private long startTime;

    // delay of the moving
    private int delay = 40;


    public Background(ImageIcon imageThatHasSprites, int numberOfSprites) {
        super(imageThatHasSprites, numberOfSprites);

        //Now we initiate the timer, so we can use in the update method
        startTime = System.currentTimeMillis();

    }

    @Override
    // update method
    public void update() {

        // here is the timer of our player millis
        long elapsed = (System.currentTimeMillis() - startTime);

        // Now when our timer gets past xxx millis we want the to do something
        if (elapsed > this.delay) {
            this.y = this.y + 10;
            if (this.y >= GamePanel.HEIGHT ) {
//                this.y = -WIDTH ;
                this.y =  - (this.y + HEIGHT);
            }

            // set back start time
            startTime = System.currentTimeMillis();
        }



    }//end update

}
