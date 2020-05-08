package com.example.javagame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Demon extends Object {

    private static final int demonMovSpeed = -GamePanel.bgMoveSpeed + 1 ; //sets the demon movement speed to bg speed + 1
    private Animation animation = new Animation();
    private Bitmap sprite;

    /**
     * Initialises game objects attributes and splits spritesheet into frames.
     * Also sets bitmap array to animation class and sets delay
     *
     * @param image
     * @param x
     * @param y
     * @param w
     * @param h
     * @param frames
     */
    public Demon(Bitmap image, int x, int y, int w, int h, int frames){

        this.x = x;
        this.y = y;

        this.width = w;
        this.height = h;

        Bitmap[] walkAnimation = new Bitmap[frames];
        sprite = image; //sets the objects spritesheet

        for (int i = 0; i < walkAnimation.length; i++){
            walkAnimation[i] = Bitmap.createBitmap(sprite, i*width, 0, width, height); //splits spritesheet into individual frames and stores them in bitmap array
        }

        animation.setFrames(walkAnimation); //Sets the bitmap array to the animation class
        animation.setDelay(100); //delay 100 for slow image, as slow walking
    }

    /**move objects x by speed  and animate the object each update
     *
     */
    public void update()
    {
        Runnable runnable = new Runnable() { //update method threaded for optimisation
            @Override
            public void run() {
                x -= demonMovSpeed; //move objects x by speed each update
                animation.update(); //animate the object
            }
        };
        Thread updateThread = new Thread(runnable);
        updateThread.start();
    }

    /**
     * draws object at its x,y pos
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(),x,y,null); //draws object at its x,y pos
    }
}

