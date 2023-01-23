package com.learn.animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameLogic {

    GamePanel gamePanel;

    // constructor
    GameLogic(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    // check collision between two objects
    public boolean simpleCollision(GameObject a, GameObject b) {
        if (a.getRectangle().intersects(b.getRectangle()))
            return true;
        return false;
    }

        /*
    Collision check methods:
    1. check collision between two objects: --> collision(AnimatedObject objA, AnimatedObject objB)
    1. check collision between two objects, with resized one object :
            --> collision(AnimatedObject objA, AnimatedObject objB, double percentageSizeofImageA)
     */

    // check collision between two objects, with resized one object
    public boolean collision(AnimatedObject objA, AnimatedObject objB, double percentageSizeofImage) {


        if (objA.getRectangle().intersects(objB.getRectangle())) {

            Image resizedImageA = objA.animation.getImage().getImage().getScaledInstance(
                    (int) (objA.animation.getImage().getImage().getWidth(null) * percentageSizeofImage) ,
                    (int)  (objA.animation.getImage().getImage().getHeight(null) * percentageSizeofImage), Image.SCALE_SMOOTH);


            Image resizedImageB = objB.animation.getImage().getImage().getScaledInstance(
                    (int) (objB.animation.getImage().getImage().getWidth(null) * percentageSizeofImage) ,
                    (int)  (objB.animation.getImage().getImage().getHeight(null) * percentageSizeofImage), Image.SCALE_SMOOTH);


            return check_pixels_collision(
                    resizedImageA, objA.getX(), objA.getY(),
                    resizedImageB, objB.getX(), objB.getY());
        }
        return false;
    }


    // check collision between two objects
    public boolean collision(AnimatedObject objA, AnimatedObject objB) {

        if (objA.getRectangle().intersects(objB.getRectangle()))
            return check_pixels_collision(objA.animation.getImage().getImage(), objA.getX(), objA.getY(),
                    objB.animation.getImage().getImage(), objB.getX(), objB.getY());
        return false;
    }

    private Rectangle getCollisionBound(Rectangle rect1, Rectangle rect2) {
        int x = 0, width = 0, y = 0, height = 0;

        // 1
        if (rect1.getX() >= rect2.getX() && rect1.getY() <= rect2.getY()) {
            x = (int) (rect1.getX());
            width = (int) (rect2.getX() + rect2.getWidth() - rect1.getX());
            if (width > rect1.width)
                width = rect1.width;
            y = (int) (rect2.getY());
            height = (int) (rect1.getY() + rect1.getHeight() - rect2.getY());
            if (height > rect2.height)
                height = rect2.height;
        }
        //2
        else if (rect1.getX() >= rect2.getX() && rect1.getY() >= rect2.getY()) {
            x = (int) (rect1.getX());
            width = (int) (rect2.getX() + rect2.getWidth() - rect1.getX());
            if (width > rect1.width)
                width = rect1.width;
            y = (int) (rect1.getY());
            height = (int) (rect2.getY() + rect2.getHeight() - rect1.getY());
            if (height > rect1.height)
                height = rect1.height;
        }
        // 3
        else if (rect1.getX() <= rect2.getX() && rect1.getY() >= rect2.getY()) {
            x = (int) (rect2.getX());
            width = (int) (rect1.getX() + rect1.getWidth() - rect2.getX());
            if (width > rect2.width)
                width = rect2.width;
            y = (int) (rect1.getY());
            height = (int) (rect2.getY() + rect2.getHeight() - rect1.getY());
            if (height > rect1.height)
                height = rect1.height;
            return new Rectangle(x, y, width, height);
        }
        //4 -- if (rect1.getX() <= rect2.getX() && rect1.getY() <= rect2.getY())
        else {
            x = (int) (rect2.getX());
            width = (int) (rect1.getX() + rect1.getWidth() - rect2.getX());
            if (width > rect2.width)
                width = rect2.width;
            y = (int) (rect2.getY());
            height = (int) (rect1.getY() + rect1.getHeight() - rect2.getY());
            if (height > rect2.height)
                height = rect2.height;
        }

        return new Rectangle(x, y, width, height);
    }

    private boolean check_pixels_collision(Image objImageA, int x1, int y1, Image objImageB,
                                           int x2, int y2) {

        // create buffered images form the sprites played current
        BufferedImage imageA = new BufferedImage(objImageA.getWidth(null),
                objImageA.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = imageA.createGraphics();
        bGr.drawImage(objImageA, 0, 0, null);
        bGr.dispose();

        BufferedImage imageB = new BufferedImage(objImageB.getWidth(null),
                objImageB.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        bGr = imageB.createGraphics();
        bGr.drawImage(objImageB, 0, 0, null);
        bGr.dispose();


        Rectangle bounds1 = new Rectangle(x1, y1, imageA.getWidth(), imageA.getHeight());
        Rectangle bounds2 = new Rectangle(x2, y2, imageB.getWidth(), imageB.getHeight());

        Rectangle collisionBounds = getCollisionBound(bounds1, bounds2);

        int left = (int) collisionBounds.getX();
        int right = (int) (collisionBounds.getX() + collisionBounds.getWidth());
        int top = (int) collisionBounds.getY();
        int bottom = (int) (collisionBounds.getY() + collisionBounds.getHeight());

        for (int i = left; i < right; i++) {
            for (int j = top; j < bottom; j++) {
                int pixelA = imageA.getRGB(i - x1, j - y1);
                int pixelB = imageB.getRGB(i - x2, j - y2);

                // check for transparent pixel - alpha = (pixelA >> 24) & 0xFF
                if ((pixelA >> 24) != 0x00 && ((pixelB >> 24) != 0x00)) {
                    return true;
                }
            }
        }
        return false;
    }


}