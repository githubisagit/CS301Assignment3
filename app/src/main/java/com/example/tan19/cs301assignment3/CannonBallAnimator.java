package com.example.tan19.cs301assignment3;

import android.graphics.*;
import android.view.MotionEvent;

/**
 * CannonBallAnimator
 *
 * Class that handles the animation of the Cannon shooting the Cannon Ball object.
 * Also handles the functionality of when a Cannon Ball hits a target.
 *
 *
 * Created by tan19 on 4/1/2017.
 */
public class CannonBallAnimator implements Animator {


    //inst variables
    private CannonBall myBall = new CannonBall();
    private Cannon myCannon = new Cannon();
    private Target myTarget1 = new Target();
    private Target myTarget2 = new Target();
    private boolean launched = false;
    private boolean targetHit = false ;



    @Override
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        return Color.rgb(100, 200, 255);
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }
    public void isLaunched(boolean launchFlag){
        launched = launchFlag;
    }

    /**
     * This tick method handles the functionality of moving the CannonBall object once
     * the launch button has been pressed.
     * The method also checks to see if a CannonBall has hit a target, if so it draws the CannonBall
     * in a green color to indicate success.
     * @param canvas
     */
    @Override
    public void tick(Canvas canvas) {
        //booleans to keep track of overlapping.
        boolean overlapsTarget1 = myTarget1.overlaps(myBall);
        boolean overlapsTarget2 = myTarget2.overlaps(myBall);

        if(launched ) {//if the launch button has been pressed.

            if(!myBall.getOutOfBounds()) {//if the ball hasn't left the screen.
                myBall.moveCannonBall(15, -20, 1800, 1100);//move the ball 15 units in the x direction and -20 in the y

            }
            else {//if the ball is out of bounds, reset it.
                myBall.reset();
                targetHit = false;
            }

        }
        else{//put the ball back on the cannon.
            myBall.reset();
            targetHit = false ;
        }

        if(overlapsTarget1 || overlapsTarget2){//draws the ball in a green color if it has hit a target.
            targetHit = true;
            myBall.drawSuccess(canvas);
        }else{
            myBall.draw(canvas);
        }

        myCannon.draw(canvas);
        myTarget1.draw(canvas);
        myTarget2.draw(canvas);


    }

    @Override
    public void onTouch(MotionEvent event) {
    }

    //getters
    public Cannon getMyCannon() {
        return myCannon;
    }
    public CannonBall getMyBall() {
        return myBall;
    }
    public boolean getTargetHit(){
        return this.targetHit ;
    }

    //setters.
    public void setMyTarget1(Target newTarget){
        this.myTarget1 = newTarget ;
    }
    public void setMyTarget2(Target newTarget){
        this.myTarget2 = newTarget;
    }


}
