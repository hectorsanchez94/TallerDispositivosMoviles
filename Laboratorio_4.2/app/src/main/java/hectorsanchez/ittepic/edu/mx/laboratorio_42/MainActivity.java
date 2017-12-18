package hectorsanchez.ittepic.edu.mx.laboratorio_42;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Handler handler = new Handler();
    private ArrayList<String> telefonos;
    private ArrayAdapter<String> adaptador1;
    Button button1;
    TextView tx1;
    EditText editText1;
    private ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        telefonos=new ArrayList<String>();
        adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,telefonos);
        lv1=(ListView)findViewById(R.id.listview);
        lv1.setAdapter(adaptador1);
        tx1 = (TextView) findViewById(R.id.textView2);
        button1 = (Button) findViewById(R.id.button1);
        editText1 = (EditText) findViewById(R.id.editText1);

        button1.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                hilos1();
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

    public void hilos1() {
        final int  num3;
        num3 = Integer.parseInt(editText1.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                //while (num3 <= 10){

                    //num3+=1;
                    handler.post(new Runnable() {

                        public void run() {
                            String res="";

                            //telefonos.clear();
                            //telefonos.add("Este es el resultafo de la tabla de multiplicar del "+num3+":\n"+res);
                            for(int j=1;j<=10;j++) {
                                UnSegundo();
                                tx1.setText(res+=num3+" x "+j+" = " +(j*num3)+"\n");

                                //telefonos.add(res);
                                //adaptador1.notifyDataSetChanged();

                                tx1.setText(res);
                            }



                        }
                    });
               // }
            }
        }).start();
    }
}
