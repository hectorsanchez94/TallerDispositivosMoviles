package hectorsanchez.ittepic.edu.mx.hilos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Handler handler = new Handler();
    ProgressBar progressBar1, progressBar2, progressBar3;
    Button button1, button2, button3;
    EditText editText1, editText2, editText3;
    int val1,val2,val3;
    private AsyncTarea1 tarea1;
    private AsyncTarea2 tarea2;
    private AsyncTarea3 tarea3;

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

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hilos1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hilos2();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hilos3();
            }
        });
    }



    private void UnSegundo1() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void hilos3() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                    handler.post(new Runnable() {

                        public void run() {
                            tarea3 = new AsyncTarea3();
                            tarea3.execute();
                        }
                    });
                }
        }).start();
    }

    public void hilos2() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {

                    public void run() {
                        tarea2 = new AsyncTarea2();
                        tarea2.execute();
                    }
                });
            }
        }).start();
    }
    public void hilos1() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {

                    public void run() {
                        tarea1 = new AsyncTarea1();
                        tarea1.execute();
                    }
                });
            }
        }).start();
    }

    private class  AsyncTarea1 extends AsyncTask<Void, Integer,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            val1= Integer.parseInt(editText1.getText().toString());
            progressBar1.setMax(val1);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            for (int i=1; i<=val1; i++){
                UnSegundo1();
                publishProgress(i*1);

                if (isCancelled()){
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //Actualizar la barra de progreso
            progressBar1.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            //super.onPostExecute(aVoid);
            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask1",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplicationContext(),"Tarea NO finaliza AsyncTask1",Toast.LENGTH_SHORT).show();
        }
    }

    private class  AsyncTarea2 extends AsyncTask<Void, Integer,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            val2= Integer.parseInt(editText2.getText().toString());
            progressBar2.setMax(val2);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            for (int i=1; i<=val2; i++){
                UnSegundo1();
                publishProgress(i*1);

                if (isCancelled()){
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //Actualizar la barra de progreso
            progressBar2.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);
            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask2",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplicationContext(),"Tarea NO finaliza AsyncTask2",Toast.LENGTH_SHORT).show();
        }
    }

    private class  AsyncTarea3 extends AsyncTask<Void, Integer,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            val3= Integer.parseInt(editText3.getText().toString());
            progressBar3.setMax(val3);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            for (int i=1; i<=val3; i++){
                UnSegundo1();
                publishProgress(i*1);

                if (isCancelled()){
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //Actualizar la barra de progreso
            progressBar3.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            //super.onPostExecute(aVoid);
            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask3",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplicationContext(),"Tarea NO finaliza AsyncTask3",Toast.LENGTH_SHORT).show();
        }
    }
}
