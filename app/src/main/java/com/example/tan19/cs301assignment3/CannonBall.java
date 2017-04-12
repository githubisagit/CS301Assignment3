package com.example.tan19.cs301assignment3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * CannonBall
 *
 * This class holds information about the Cannon Ball object that
 * is shot across our custom surface view.
 *
 * Created by tan19 on 4/1/2017.
 */
public class CannonBall {
    //inst variables.
    protected float x; //position x
    protected float y; //position y
    protected float vx, vy; //velocities
    protected int size = 60;
    private Paint myPaint = new Paint();
    protected float origX ;//to be used to reset the CannonBall
    protected float origY ;
    float gravity ;
    float gravityDelta = .5f;
    float wind;
    private boolean outOfBounds = false;



    //ctor
    public CannonBall(){
        x = 350 ;//hardcoded initial position.
        y = 1050 ;
        origX = 350;
        origY = 1050;
        vx = vy = 0 ;
        myPaint.setColor(Color.BLACK);




    }


    /**
     * The moveCannonBall method changes this CannonBall's x and y positions according
     * to a spesific velocity. Also accounts for gravity by adding an always incrementing value
     * to the y velocity.
     * @param aX
     * @param aY
     * @param maxX
     * @param maxY
     */
    public void moveCannonBall(float aX, float aY, int maxX, int maxY) {


        //BOTH ADDED IN HW3B, wind accounts for horizontal wind, gravityDelta is used to let the user change Gravity.
        wind += .05f;
        gravity += gravityDelta;


        //adjust the cannon ball if it goes off the edge
        if(x + size > maxX){
            reset();
            outOfBounds = true;
        }
        else{
            vx = aX;//set the x velocity
            this.x += vx;//change the x position to reflect the velocity.
            x -=wind;
        }

        if( y + size > maxY){

            //if the ball hits the ground, dont continue to change the y position.
            y = maxY;
            x = this.getX();
            gravity = 0;

            //to ensure the ball stops when it hits the ground


        }else{

            vy = aY;
            this.y += vy;
            y+= gravity;//account for gravity.

        }



    }//moveCannonBall

    //this method draws the Cannon Ball in black.
    public void draw(Canvas canvas){
        myPaint.setColor(Color.BLACK);
        canvas.drawCircle(x, y, size, myPaint);
    }

    //this method is called only when a CannonBall is overlapping a target.
    public void drawSuccess(Canvas canvas){
        myPaint.setColor(Color.GREEN);
        canvas.drawCircle(x,y,size,myPaint);
    }


    /*getters */
    public float getX(){
        return x;
    }

    public float getY() {
        return y;
    }

    public Paint getMyPaint() {
        return myPaint;
    }

    public boolean getOutOfBounds(){return outOfBounds;}

    //reset the ball back to the cannon. Accounts for angle changes.
    public void reset(){
        x = origX;
        y = origY;
        vx = vy = 0 ;
        gravity = 0;
        wind = 0;

        outOfBounds = false;
    }

    /* setters */
    public void setOrigX(float initX){
        this.origX = initX;
    }
    public void setOrigY(float initY){
        this.origY = initY;
    }
    public void setGravityDelta(float initGravityDelta){this.gravityDelta = initGravityDelta;}
}
