package com.example.javagame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Warlock extends Object{
    private int score; //player stores score
    private Bitmap sprite;

    private boolean isPlaying = true; //is dead?

    private Animation animation = new Animation();
    private long timeStart;

    /**
     * initilises player position, then sets up the bitmap array and animation
     * @param image
     * @param w
     * @param h
     * @param frameNo
     */
    public Warlock(Bitmap image, int w, int h, int frameNo){
        x = 100; //initialise player position
        y = GamePanel.HEIGHT/2;

        this.height = h;
        this.width = w;

        Bitmap[] walkAnimation = new Bitmap[frameNo];
        sprite = image; //sets the objects spritesheet

        for (int i = 0; i < walkAnimation.length; i++){
            walkAnimation[i] = Bitmap.createBitmap(sprite, i*width, 0, width, height); //splits spritesheet into individual frames and stores them in bitmap array
        }

        animation.setFrames(walkAnimation);//Sets the bitmap array to the animation class
        animation.setDelay(-GamePanel.bgMoveSpeed*10); //animation delay by 10x bg speed
        timeStart = System.nanoTime();

    }

    /**
     * player update method will increase score every second,
     * and will switch level depending on score threshold
     * the animation will then update
     */
    public void update()
    {
        long elapsed = (System.nanoTime()-timeStart)/1000000; //each second increase score by...
        if(elapsed>100)
        {
            score+=50; //...increase score by 5
            System.out.println(score);
            if(score > 5000){
                GamePanel.level = 2; //switch to level 2 when score reaches above 5000

            }
            if(score > 10000){
                GamePanel.level = 3; //switch to level 3 when score reaches above 10000
            }
            timeStart = System.nanoTime(); // reset timer
        }
        animation.update(); //animate player
    }

    /**
     * draws object at its x,y pos
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(),x,y,null); //draws object at its x,y pos
    }

    /**
     * check if player dead
     * @return
     */
    public boolean getPlaying(){
        return isPlaying;
    }

    /**
     * set player death status
     * @param x
     */
    public void setPlaying(boolean x){
        isPlaying = x;
    }

    public int getScore(){
        return score;
    }

    public void addScore(int x){
        score += x; //adds the score
    }

    public void setScore(int x){
        score = x;
    }
}
