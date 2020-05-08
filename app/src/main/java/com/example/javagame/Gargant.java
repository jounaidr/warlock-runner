package com.example.javagame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Gargant extends Object {
    private static final int gargantMovSpeed = 2*(-GamePanel.bgMoveSpeed); //gargant moves at 2x bg speed
    private Animation animation = new Animation(); //initilize animation object
    private Bitmap sprite; //

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
    public Gargant(Bitmap image, int x, int y, int w, int h, int frames){
        this.x = x;
        this.y = y;

        this.width = w;
        this.height = h;

        Bitmap[] walkAnimation = new Bitmap[frames]; //sets the objects spritesheet
        sprite = image;

        for (int i = 0; i < walkAnimation.length; i++){
            walkAnimation[i] = Bitmap.createBitmap(sprite, i*width, 0, width, height); //splits spritesheet into individual frames and stores them in bitmap array
        }

        animation.setFrames(walkAnimation); //Sets the bitmap array to the animation class
        animation.setDelay(-GamePanel.bgMoveSpeed*10); //delay 100 for flying animation
    }

    /**move objects x by speed  and animate the object each update
     *
     */
    public void update()
    {
       // Runnable runnable = new Runnable() { //update method threaded for optimisation
       //     @Override
       //     public void run() {
                x -= gargantMovSpeed; //move objects x by speed each update
                animation.update(); //animate the object
       //     }
      //  };
     //   Thread updateThread = new Thread(runnable);
     //   updateThread.start();
    }

    /**
     * draws object at its x,y pos
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(),x,y,null); //draws object at its x,y pos
    }
}
