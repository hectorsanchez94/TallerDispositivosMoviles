package hectorsanchez.ittepic.edu.mx.hilos;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private Handler handler = new Handler();
    private int prog,prog2,prog3;

    ProgressBar progressBar1, progressBar2, progressBar3;
    Button button1, button2, button3;
    EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);

        button1.setOnClickListener(MainActivity.this);
        button2.setOnClickListener(MainActivity.this);
        button3.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                hilos1();
                break;

            case R.id.button2:
                hilos2();
                break;
            case R.id.button3:
                hilos3();
                break;
        }
    }
    private void UnSegundo() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void hilos3() {
        final int  num3;
        num3 = Integer.parseInt(editText3.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                prog3=0;
                while (prog3 <= num3){
                    UnSegundo();
                    prog3+=1;
                    handler.post(new Runnable() {

                        public void run() {
                            progressBar3.setProgress(prog3);
                            progressBar3.setMax(num3);
                        }
                    });
                }
            }
        }).start();
    }

    public void hilos2() {
        final int  num2;
        num2 = Integer.parseInt(editText2.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                prog2=0;
                while (prog2 <= num2){
                    UnSegundo();
                    prog2+= 1;
                    handler.post(new Runnable() {

                        public void run() {
                            progressBar2.setProgress(prog2);
                            progressBar2.setMax(num2);
                        }




                    });
                }
            }
        }).start();
    }
    public void hilos1() {
        final int  num1;
        num1 = Integer.parseInt(editText1.getText().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                prog=0;
                while (prog <= num1) {
                    UnSegundo();
                    prog+= 1;
                    handler.post(new Runnable() {

                        public void run() {
                            progressBar1.setProgress(prog);
                            progressBar1.setMax(num1);
                        }




                    });
                }
            }

        }).start();
    }

}
