package com.example.javagame;

import android.graphics.Rect;

public abstract class Object { //abstract class that all game objects inherit
    //x,y pos
    protected float x;
    protected float y;
    //width and height of hitbox
    protected int width;
    protected int height;

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    /**
     * returns a rectangle of the objects hitbox
     * @return
     */
    public Rect getRect(){
        return new Rect((int)x,(int)y,(int)x+width,(int)y+width);
    }

}
