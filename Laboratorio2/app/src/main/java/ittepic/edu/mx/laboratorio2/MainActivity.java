package ittepic.edu.mx.laboratorio2;


import android.content.Context;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
        import java.io.FileOutputStream;
        import java.io.InputStream;
        import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private final String FileName="textfielnombre.txt";
    private final String FileNoCo="textfielNoCon.txt";
    EditText sint, re;
    TextView mostrar;
    Button guardar, leer, borrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sint = (EditText)findViewById(R.id.sintomas);
        re = (EditText)findViewById(R.id.recom);
        guardar = (Button)findViewById(R.id.guardar);
        leer = (Button)findViewById(R.id.leer);
        borrar =(Button)findViewById(R.id.borrar);
        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ mguardar(); } });
        leer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { Leer(); } });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sint.setText(""); re.setText("");  mostrar.setText("");} });
    }
    public void mguardar() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FileName, Context.MODE_PRIVATE);
            fileOutputStream.write(sint.getText().append(",").toString().getBytes());
            fileOutputStream.close();
            FileOutputStream fileOutputStream2 = openFileOutput(FileNoCo, Context.MODE_PRIVATE);
            fileOutputStream2.write(re.getText().toString().getBytes());
            fileOutputStream2.close();
            Toast.makeText(MainActivity.this,"Datos guardados con exito!",Toast.LENGTH_LONG).show();
            re.setText("");
            sint.setText("");
        } catch (java.io.IOException e) { e.printStackTrace(); }
    }
    private void Leer(){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = openFileInput(FileName);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String newLine = null;
                while ((newLine = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(newLine+"\n");
                }
                inputStream.close();
            }
            InputStream inputStream2 = openFileInput(FileNoCo);
            if ( inputStream2 != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream2);
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                String newLine = null;
                while ((newLine = bufferedReader2.readLine()) != null ) {
                    stringBuilder.append(newLine+"\n");
                }
                inputStream.close();
            }
        } catch (java.io.IOException e) { e.printStackTrace(); }
        sint.setText(stringBuilder);
    }
}

