package com.example.tan19.cs301assignment3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Target
 *
 * This class holds all information about the targets that are drawn onto the surface view.
 *
 * Includes the functionality to determine if a CannonBall object has hit this object.
 *
 * Created by tan19 on 4/1/2017.
 */

public class Target {
    //inst variables.
    private int x, y;
    private int size;
    private Paint targetPaint = new Paint();


    //ctor
    public Target() {
        targetPaint.setColor(Color.RED);

        //the target x has to be greater than 500, but less than 1800
        //the target y has to be greater than 100, but less than 1100
        x = (int) (Math.random() * 900) + 500;//random location
        y = (int) (Math.random() * 500) + 500;

        size = 70;//fixed size
    }

    /**
     * This method determines if a CannonBall object is overlapping this Target.
     * If it is, it returns a true value that will be used in the CannonBAllAnimaor Class.
     *
     * @param ball
     * @return
     */
    public boolean overlaps(CannonBall ball) {

        float distanceX = Math.abs(ball.x - this.x);
        float distanceY = Math.abs(ball.y - this.y);

        float distance = (int) Math.sqrt(distanceX * distanceX + distanceY * distanceY);//my dood pythagoreas.

        float largeSize = Math.max(this.size, ball.size);


        return distance <= largeSize;
    }


    public void draw(Canvas canvas) {
        //a straightforward draw method.
        canvas.drawCircle(x, y, size, targetPaint);
    }

}//Target
