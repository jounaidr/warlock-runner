package com.example.javagame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap bg;
    private int x, y, movX;

    /**
     * sets bg image and mov speed
     *
     * @param image
     */
    public Background(Bitmap image) {
        bg = image; //sets the background image
        movX = GamePanel.bgMoveSpeed; //sets the scroll speed
    }

    /**
     * update method scrolls background towards left
     */
    public void update(){
        x += movX; //move in x direction by movement speed
        if(x<-GamePanel.WIDTH){
            x=0; //when background goes off screen, reset x position
        }
    }

    /**
     * draw method draws two separate bg images, one to follow the first
     * scrolling image, so there is no blank space
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(bg,x,y,null); //draw background
        if(x<0){
            canvas.drawBitmap(bg, x+GamePanel.WIDTH, y, null); //draw copy of background to follow previous one
        }
    }
}
