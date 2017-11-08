package hectorsanchez.ittepic.edu.mx.externalstorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText entrada;
    Button boton_guardar;
    Button boton_leer;

    private String nombre_archivo = "SavingSD.txt";
    private String nombre_car = "MyFileStorage";
    File direccion_externa;
    String datos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada = (EditText) findViewById(R.id.texto_entrada);
        boton_leer = (Button) findViewById(R.id.cargar_sd);
        boton_guardar = (Button) findViewById(R.id.guardar_sd);

        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = new FileOutputStream(direccion_externa);
                    fos.write(entrada.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                entrada.setText("");

                Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        boton_leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = new FileInputStream(direccion_externa);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        datos = datos + strLine;
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                entrada.setText(datos);


                Toast.makeText(getBaseContext(), "File loaded successfully!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            boton_guardar.setEnabled(false);
        } else {
            direccion_externa = new File(getExternalFilesDir(nombre_car), nombre_archivo);
        }
    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
