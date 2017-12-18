package com.example.edgar.frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnStart,btnStop;

    ImageView imageView;

    private AnimationDrawable Animation;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStart = (Button)findViewById(R.id.btnRun);

        btnStop = (Button)findViewById(R.id.btnStop);


        imageView = (ImageView) findViewById(R.id.imageView);
        if (imageView == null) throw new AssertionError();

        imageView.setVisibility(View.INVISIBLE);
        imageView.setBackgroundResource(R.drawable.boy_animation);

        Animation = (AnimationDrawable) imageView.getBackground();
        Animation.setOneShot(true);




        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                int numero = (int) (Math.random() * 3);
                imageView.setVisibility(View.VISIBLE);
                if(Animation.isRunning()){
                    Animation.stop();
                }
                imageView.setVisibility(View.VISIBLE);
                Animation.start();
                Log.d("Variable img: ",""+numero);
                if (numero==0){Animation.addFrame(getResources().getDrawable(R.drawable.boy_01),100);}
                if (numero==1){Animation.addFrame(getResources().getDrawable(R.drawable.boy_02),100);}
                if (numero==2){Animation.addFrame(getResources().getDrawable(R.drawable.boy_03),100);}

            }

        });



        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Animation.stop();

            }

        });
    }
}
