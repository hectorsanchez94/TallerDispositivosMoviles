package hectorsanchez.ittepic.edu.mx.animationevents;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private RelativeLayout canvas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.faceIcon);
        canvas = (RelativeLayout) findViewById(R.id.animationCanvas);



    }

    public void onButtonDesacelerator(View v){
        int screenHeight = canvas.getHeight();
        int targetY = screenHeight - imageView.getHeight();
        ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(imageView, "y",0,targetY);
        buttonAnimator.setDuration(2000);
        buttonAnimator.setInterpolator(new DecelerateInterpolator());
        buttonAnimator.start();

    }

    public void onButtonCycle(View v){
        ValueAnimator bounceAnim = null;
        int screenHeight = canvas.getWidth();
        int targetY = screenHeight - imageView.getHeight();

        if (bounceAnim == null) {
            bounceAnim = ObjectAnimator.ofFloat(imageView,"x",imageView.getX(),targetY).setDuration(1500);
            bounceAnim.setInterpolator(new CycleInterpolator(2));
            bounceAnim.start();
        }
    }

    public void onButtonClick(View v) {

        int screenHeight = canvas.getHeight();
        int targetY = screenHeight - imageView.getHeight();

        ObjectAnimator animator = ObjectAnimator.ofFloat(
                imageView, "y", targetY, 0)
                .setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

}
