package com.example.tan19.cs301assignment3;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * CannonMainActivity
 *
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object
 *
 * @author Andrew Nuxoll, Sean Tan
 * @version April 2017
 *
 */
public class CannonMainActivity extends Activity implements SeekBar.OnSeekBarChangeListener, Button.OnClickListener {

    /**
     * creates an AnimationCanvas containing a CannonBallAnimator.
     */
    private SeekBar angleSeekBar ;
    private Button launchButton;
    private AnimationCanvas myCanvas;
    private TextView hitText ;
    CannonBallAnimator ballAnimator = new CannonBallAnimator();

    private boolean launched = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannon_main);

        // Create an animation canvas and place it in the main layout
        myCanvas = new AnimationCanvas(this, ballAnimator);
        LinearLayout mainLayout = (LinearLayout) this
                .findViewById(R.id.screen);
        mainLayout.addView(myCanvas);

        hitText = (TextView)findViewById(R.id.hitText);

        angleSeekBar = (SeekBar)findViewById(R.id.angleSeekBar);
        angleSeekBar.setOnSeekBarChangeListener(this);

        launchButton = (Button)findViewById(R.id.launchButton);
        launchButton.setOnClickListener(this);


        myCanvas.invalidate();//just in case!

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int angle = i ;
        ballAnimator.getMyCannon().translate(i);//sends the progress value of the seekBar as a degreeValue for the Cannon.

        //these new values are used to rotate the CannonBall object along with the Cannon.
        float newBallX = ballAnimator.getMyCannon().getRotatePointX();
        float newBallY = ballAnimator.getMyCannon().getRotatePointY();

        //set the new Cannon Ball Values.
        ballAnimator.getMyBall().setOrigX(newBallX+40);
        ballAnimator.getMyBall().setOrigY(newBallY+20);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View view) {
        //if the launched button has been pressed, trigger the launched flag.
        if(view.getId() == launchButton.getId()) {
            launched = !launched;

            if (launched) {
                ballAnimator.isLaunched(true);
            } else {
                ballAnimator.isLaunched(false);
            }
        }

    }
}

