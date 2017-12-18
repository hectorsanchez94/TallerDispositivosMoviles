package ittepic.edu.mx.calculadorapropinas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText monto;//, total, tip;
    TextView total,tip,textViewSeekBar;
    Button button;
    SeekBar seekBar;

    int m=0;
    double calculo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        monto = (EditText)findViewById(R.id.amountEditText);
        total = (TextView)findViewById(R.id.totalLabelTextView);
        tip = (TextView)findViewById(R.id.tipLabelTextView);
        seekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        textViewSeekBar = (TextView) findViewById(R.id.percentTextView);
        textViewSeekBar.setText("15");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //la Seekbar siempre empieza en cero, si queremos que el valor mínimo sea otro podemos modificarlo
                textViewSeekBar.setText(progress + 15 + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }
        });


        button = ((Button)findViewById(R.id.button));

    }

    public void calcula(View view){
        String valor = monto.getText().toString();
        int seekValue = seekBar.getProgress();
        int valorSeek = seekValue+15;
        Toast.makeText(getApplicationContext(),"Valor: "+valorSeek,Toast.LENGTH_SHORT).show();

        calculo = (Integer.parseInt(valor)*(valorSeek)/100);
        tip.setText("$"+calculo + "");
        double pagototal=calculo+Integer.parseInt(valor);
        total.setText("$"+pagototal);


        //String x = "Value: " + Integer.toString(seekValue);
    }

}
