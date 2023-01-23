package com.learn.animation;

public class GameLoop extends Thread {

    GamePanel gamePanel;
    /*
     * What is FPS?, FPS=Frames per Second, a good thread runs between 30 and 60 fps
     * more cpu demanding
     * in our game we will need 60 fps because we want to have many objects
     * and our drawing object are going to have sprites.
     */
    // FPS=Frames per Second
    private int FPS = 30;
    // flag for running = true
    private boolean running;

    GameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /*
     * Now we will override the run method. All thread have a run method
     * So to get advantage of the thread we will write our time code inside the run method
     */
    @Override
    public void run() {
        /**
         * Inside the run method we want every second to catch the 60 frames
         * So the vars we will need...
         */
        long startTime;
        long sleepTime;

        /*
        targetTime will give us 1000/ FPS , if FPS = 60, targetTime = 1000/60 = 16.667 millis
        for each new screen to be painted/drawn
         */
        long targetTime = 1000 / FPS;

        // if the game is in running mode
        while (running) {

            /**
             First we set the time
             Careful now our system timer start to count in nanos. 1 milli = 1000000 nanos
             We will need that later...
             */
            startTime = System.nanoTime();

            try {

                /**
                 * this is the game data update as for example the x and y position coordinates
                 * for a little character (Sprites positions, Score base in time, ...)
                 */
                this.gamePanel.update();

                /*
                 * this is about drawing the picture you see in the screen.
                 * When this method is called repeatedly it gives you
                 * the perception of a MOVIE or of an animation.
                 **/
                this.gamePanel.draw();

            }
            catch (Exception e) {
                e.printStackTrace();
            }//end try

            /* we will do some time calculation... The sleepTime var
             *The time we will wait till the next frame enter the loop
             */
            sleepTime = targetTime - ((System.nanoTime() - startTime) / 1000000);

            /*
             * while we wait WE pause (sleep) the thread for
             * the sleepTime between the frames
             * is so little that you cant notice it....
             */
            try {
                this.sleep(sleepTime);
            } catch (Exception e) {
            }
        } // end while
    } // end of run method

    // setter for running flag
    public void setRunning(boolean running) {
        this.running = running;
    }

}
