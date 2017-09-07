package hectorsanchez.ittepic.edu.mx.tabla1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtTitulo;
    //EditText edtNum;
    Button btnCalc;
    TextView txtRes;
    SeekBar skbNum;

    private ArrayList<String> telefonos;
    private ArrayAdapter<String> adaptador1;
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        telefonos=new ArrayList<String>();
        adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,telefonos);
        lv1=(ListView)findViewById(R.id.listview);
        lv1.setAdapter(adaptador1);

        //1. Construir referencias a componentes XML
        txtTitulo = (TextView)findViewById(R.id.txt_titulo);
        //edtNum = (EditText)findViewById(R.id.edt_num);
        //btnCalc =(Button)findViewById(R.id.btn_calc);
        //txtRes = (TextView)findViewById(R.id.txt_res);
        skbNum = (SeekBar)findViewById(R.id.skb_numero);

        //2. Asociar listener a btnCalc
       // btnCalc.setOnClickListener(this);

        skbNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String res="";
                int numero;
                numero=skbNum.getProgress();
                telefonos.clear();
                telefonos.add("Este es el resultafo de la tabla de multiplicar del "+skbNum.getProgress()+":\n"+res);
                for(int j=1;j<=10;j++) {
                    res=" "+numero+" x "+j+" = " +(j*numero)+"\n";
                    telefonos.add(res);
                    adaptador1.notifyDataSetChanged();
                }

                //txtRes.setText("Este es el resultafo de la tabla de multiplicar del "+skbNum.getProgress()+":\n"+res);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;

                Toast.makeText(getApplicationContext(),"Sleccionó "+skbNum.getProgress()+" x "+posicion+" = "+(i*skbNum.getProgress()),Toast.LENGTH_LONG).show();
            }
        });
        

    }

 //   @Override
   // public void onClick(View view) {
        //3. Obtener el valor numerico de la tabla
  /*      String res="";
        int numero;
*/
        //Sin SpeekBar
        /* numero = Integer.parseInt(edtNum.getText().toString());

        for(int i=1;i<=10;i++){
            res=res+" "+numero+" x "+i+" = " +(i*numero)+"\n";
        }
        txtRes.setText("Este es el resultafo de la tabla de multiplicar del "+numero+":\n"+res);
        */


        //Con SpeekBar
 /*       numero=skbNum.getProgress();
        for(int i=1;i<=10;i++) {
            res=res+" "+numero+" x "+i+" = " +(i*numero)+"\n";
        }
        txtRes.setText("Este es el resultafo de la tabla de multiplicar del "+numero+":\n"+res);*/
 //   }


}
