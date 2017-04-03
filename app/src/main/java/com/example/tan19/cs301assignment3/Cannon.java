package com.example.tan19.cs301assignment3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.lang.*;
import java.util.ArrayList;

/**
 * Cannon
 *
 * This is the Cannon object that will be displayed onto our custom
 * SurfaceView, and can be rotated by a spesific degree value.
 *
 * Created by tan19 on 4/1/2017.
 *
 */
public class Cannon {

    //inst variables.
    private Paint myPaint = new Paint();
    private float degAngle, radAngle, cannonLength, cannonHeight;
    private Point cannonOrigin;
    private ArrayList<Float> xCors;
    private ArrayList<Float> yCors;
    private Path rectPath;
    private PointF rotatePoint = new PointF();

    //ctor.
    public Cannon() {
        degAngle = 0;

        cannonLength = 200;
        cannonHeight = 100;
        cannonOrigin = new Point(100, 1100);
        myPaint.setColor(Color.GRAY);

    }

    /**
     * This draw method draw a Path in the shape of a rectangle that will be our Cannon
     * The cannon can be rotated about a degree, and this draw method reflects that idea.
     * @param canvas
     */
    public void draw(Canvas canvas) {
        /**
         External Citation
         Date: 02 April 2017
         Problem: Could not get a Rect Object to Rotate appropriately.
         Resource:
         http://stackoverflow.com/questions/6763231/draw-rotated-path-at-particular-point
         AND - https://developer.android.com/reference/android/graphics/Path.html
         Solution: I followed the example from the Stack Overflow comment section and then used the developer page
         to find the appropriate methods that I spesifically needed.
         */

        radAngle = (float) ((degAngle * Math.PI) / 180);// converts the degree to radians.

        //create a new path object.
        rectPath = new Path();
        rectPath.reset();


        //adding x values
        xCors = new ArrayList<Float>();
        xCors.add((float) this.cannonOrigin.x);
        xCors.add((float) (this.cannonOrigin.x - (Math.sin(radAngle) * cannonHeight)));
        xCors.add((float) (this.cannonOrigin.x + (Math.cos(radAngle) * cannonLength) - (Math.sin(radAngle) * cannonHeight)));
        xCors.add((float) (this.cannonOrigin.x + (Math.cos(radAngle) * cannonLength)));

        //adding y values
        yCors = new ArrayList<Float>();
        yCors.add((float) this.cannonOrigin.y);
        yCors.add((float) (this.cannonOrigin.y - Math.cos(radAngle) * cannonHeight));
        yCors.add((float) (this.cannonOrigin.y - (Math.cos(radAngle) * cannonHeight) - (Math.sin(radAngle) * cannonLength)));
        yCors.add((float) (this.cannonOrigin.y - (Math.sin(radAngle) * cannonLength)));

        //moveTo method basically sets the origin of the path object.
        rectPath.moveTo(xCors.get(0), yCors.get(0));

        //draw 3 lines that leave from and connect back to the origin in a rectangular manner.
        for (int i = 1; i < yCors.size(); i++) {
            rectPath.lineTo(xCors.get(i), yCors.get(i));
        }
        rectPath.close();

        //this rotatePoint object is used to rotate the Cannon Ball object along with the Cannon object.
        rotatePoint.x = xCors.get(2);
        rotatePoint.y = yCors.get(2);


        canvas.drawPath(rectPath, myPaint);
        canvas.drawCircle(cannonOrigin.x, cannonOrigin.y, 50, myPaint);//cannon wheel.


    }

    //this method sets this classes' degAngle to the current seekBar progress.
    public void translate(float deg) {
        this.degAngle = deg;
    }

    //getters.
    public float getRotatePointX() {
        return this.rotatePoint.x;
    }

    public float getRotatePointY() {
        return this.rotatePoint.y;
    }


}
