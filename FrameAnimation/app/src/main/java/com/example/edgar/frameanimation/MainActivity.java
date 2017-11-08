package com.example.edgar.frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnStart,btnStop;

    ImageView imageView;

    AnimationDrawable anim;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStart = (Button)findViewById(R.id.btnRun);

        btnStop = (Button)findViewById(R.id.btnStop);



        imageView = (ImageView)findViewById(R.id.imageView);

        if(imageView == null) throw  new AssertionError();

        imageView.setBackgroundResource(R.drawable.boy_animation);

        imageView.setVisibility(View.INVISIBLE);



        anim = (AnimationDrawable)imageView.getBackground();



        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                imageView.setVisibility(View.VISIBLE);

                if(anim.isRunning())

                    anim.stop();

                anim.start();

            }

        });



        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                anim.stop();

            }

        });
    }
}
