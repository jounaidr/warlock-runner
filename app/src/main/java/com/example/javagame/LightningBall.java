package com.example.javagame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class LightningBall extends Object {

    Bitmap image;

    /**
     * Initialises game objects attributes and sets image
     *
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public LightningBall(Bitmap res, float x, float y, int w, int h) {
        this.x = x;
        this.y = y;

        this.width = w;
        this.height = h;

        image = res;
    }
    /**
     * updates bullet
     */
    public void update()
    {
        x+=40;
    }

    /**
     * draws bullet
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
    }
}
