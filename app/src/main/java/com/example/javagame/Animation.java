package com.example.javagame;

import android.graphics.Bitmap;

public class Animation {
    private Bitmap[] frames;
    private int currentFrame;

    private long start;
    private long delay;

    /**
     * Takes bitmap spritesheet array into animation class
     * and notes the start time
     *
     * @param frames
     */
    public void setFrames(Bitmap[] frames){
        this.frames = frames; //Sets the frame array
        currentFrame = 0;
        start = System.nanoTime();
    }

    /**
     * Used to change the speed at which the sprite will
     * animate
     *
     * @param delay
     */
    public void setDelay(long delay){
        this.delay = delay; //sets the animation speed
    }

    /**
     * sets the current frame
     *
     * @param currentFrame
     */
    public void setFrame(int currentFrame){
        this.currentFrame = currentFrame; //sets the current frame
    }

    /**
     * Update method will loop through each image in the bitmap array
     * giving the illusion of animation.
     */
    public void update()
    {
        long timeElapsed = (System.nanoTime()-start)/1000000; //note the elapsed time

        if(timeElapsed>delay) //If enough time (denoted by delay) has passed...
        {
            currentFrame++; //change frame
            start = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0; //if at last frame loop back to beginning
        }
    }

    /**
     * returns image of current frame
     *
     * @return
     */
    public Bitmap getImage(){
        return frames[currentFrame]; //returns image of current frame
    }

    /**
     * returns the current frame index
     *
     * @return
     */
    public int getFrame(){
        return currentFrame; //returns the current frame index
    }
}
