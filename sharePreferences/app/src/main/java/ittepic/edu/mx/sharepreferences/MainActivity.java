package ittepic.edu.mx.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombreEditText, numeroEditText, emailEditText;
    private Button b1, b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreEditText = (EditText) findViewById(R.id.editText);
        numeroEditText = (EditText) findViewById(R.id.editText2);
        emailEditText = (EditText) findViewById(R.id.editText3);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);



    }

    public void leer(View view){
        // leer
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombre", "Sin nombre");
        String control = preferences.getString("control", "Sin control");
        String email = preferences.getString("email", "Sin email");

        nombreEditText.setText(nombre);
        numeroEditText.setText(control);
        emailEditText.setText(email);

    }
    public void guardar(View view) {

        String nombre = nombreEditText.getText().toString();
        String control = numeroEditText.getText().toString();
        String email = emailEditText.getText().toString();

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", nombre);
        editor.putString("control", control);
        editor.putString("email", email);
        editor.apply();

        nombreEditText.setText("");
        numeroEditText.setText("");
        emailEditText.setText("");

        Toast.makeText(MainActivity.this,"Guardado",Toast.LENGTH_LONG).show();
    }


    public void guardar2(View view){

        String nombre = nombreEditText.getText().toString();
        String control = numeroEditText.getText().toString();
        String email = emailEditText.getText().toString();

        SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("nombre", nombre);
        editor.putString("control", control);
        editor.putString("email", email);
        editor.commit();

        nombreEditText.setText("");
        numeroEditText.setText("");
        emailEditText.setText("");

        Toast.makeText(MainActivity.this,"Guardado",Toast.LENGTH_LONG).show();

    }

    public void leer2(View view){
        // leer
        SharedPreferences preferences = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);

        nombreEditText.setText(preferences.getString("nombre", "Sin nombre"));
        numeroEditText.setText(preferences.getString("control", "Sin control"));
        emailEditText.setText(preferences.getString("email", "Sin email"));

    }

}
