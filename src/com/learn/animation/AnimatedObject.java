package com.learn.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class AnimatedObject extends GameObject implements GameObjectMethodsInterface{

    // show flag - set default true, so we can see the image
    private boolean show = true;

    // this class uses the Animation class,
    // so we make a reference to a new Animation
    protected Animation animation = new Animation();

    // set an array of ImageIcon for our sprites
    ImageIcon[] sprites;

    // ImageIcon from where we cut our sprites
    private ImageIcon spriteSheet;

    // start time pointer, for many uses
    long startTime;
    int delay = 50;


    // constructor
    public AnimatedObject(ImageIcon imageThatHasSprites, int numberOfSprites) {
        this(imageThatHasSprites, numberOfSprites, numberOfSprites);
    }//end constructor

    public AnimatedObject(ImageIcon imageThatHasSprites, int numberOfSprites, int rowLength) {
        /**
         * first calculate:
         * The dimensions of each sprite in the image - width and height -
         * so we can create an array of JLables for each sprite
         */

        int noOfRows = numberOfSprites / rowLength;


        height = imageThatHasSprites.getIconHeight() / noOfRows;
        width = imageThatHasSprites.getIconWidth() / rowLength;

        // set an object array of ImageIcons, for our sprites
        sprites = new ImageIcon[numberOfSprites];

        for (int j = 0; j < noOfRows; j++) {
            //loop to create an array of sprites
            for (int i = 0; i < rowLength; i++) {
                /**
                 *Our image has XxX sprites so [0] is the first sprite,  [1] the second,  [2] the third ...
                 */
                Image allSprites = imageThatHasSprites.getImage();

                Image image = createImage(new FilteredImageSource(allSprites.getSource(),
                        new CropImageFilter(width * i, j * height, width, height)));

                ImageIcon icon = new ImageIcon();
                icon.setImage(image);
                sprites[j * rowLength + i] = icon;
            }
        }

        // now that we know all the info about the bitmap image
        // we need to set the animation and the delay

        // pass the sprites array
        animation.setSprites(sprites);
        animation.setDelay(50);
        animation.setRowLength(numberOfSprites);

        //Now we initiate the timer, so we can use in the update method
        startTime = System.nanoTime();
    }//end constructor


    // update method
    public void update() {

        // update the animation
        animation.update();

    }//end update


    /**
     * draw our object on the canvas (screen)
     */
    public void draw(JPanel panel) {
        if (show) {
            setIcon(animation.getImage());

            setBounds(x, y, width, height);
            panel.add(this);
        }
        else // show = false
            panel.remove(this);
    }//end draw

    /*
    flip an image horizontally and vertically
     */
    public Image flip(FlipDirection flipDirection) {
        Image image = animation.getImage().getImage();
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bImage;
        BufferedImage flipped = null;

        try {
            // Create a buffered image with transparency
            bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            // Draw the image on to the buffered image
            Graphics2D bGr = bImage.createGraphics();
            bGr.drawImage(image, 0, 0, null);
            bGr.dispose();
            flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    switch (flipDirection.ordinal()) {
                        case HORIZONTAL:
                            flipped.setRGB((width - 1) - x, y, bImage.getRGB(x, y));
                            break;
                        case VERTICAL:
                            flipped.setRGB(x, (height - 1) - y, bImage.getRGB(x, y));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flipped;
    }

    /*
     * Setters and Getters *******************************************************************
     */
    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
