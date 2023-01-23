package com.learn.animation;

import javax.swing.*;
import java.awt.*;

public abstract class GameObject extends JLabel {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    //we must create the set and get methods, for future use ...
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    //same for y cord
    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    /**
     * we need the height and the width of our objects...
     * we already set the values of them when we created them
     **/
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * we need the rectangle method to create a rectangle around our image...
     * we will use for collision checking between objects
     */
    public Rectangle getRectangle() {
        return new Rectangle(x, y , width, height);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
