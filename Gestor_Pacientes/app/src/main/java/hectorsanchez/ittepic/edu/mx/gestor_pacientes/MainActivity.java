package hectorsanchez.ittepic.edu.mx.gestor_pacientes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre,edtDireccion,edtCelular,edtEmail,edtFecha;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.editText);
        edtDireccion = (EditText) findViewById(R.id.editText2);
        edtCelular = (EditText) findViewById(R.id.editText3);
        edtEmail = (EditText) findViewById(R.id.editText4);
        edtFecha = (EditText) findViewById(R.id.editText5);
        btnGuardar = (Button) findViewById(R.id.btn_guardar);

       // if(getSupportActionBar()!=null){
           // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         //   getSupportActionBar().setDisplayShowHomeEnabled(true);
       // }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(edtNombre.getText().toString(),edtDireccion.getText().toString(),edtCelular.getText().toString(),edtEmail.getText().toString(),edtFecha.getText().toString());
                startActivity(new Intent(MainActivity.this,Listado.class));
                finish();

            }
        });

    }

    private void guardar (String Nombre, String Direccion, String Celular, String Email, String Fecha){
        BaseHelper helper = new BaseHelper(this,"GestorPacientes.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("NOMBRE", Nombre);
            c.put("DIRECCION", Direccion);
            c.put("CELULAR", Celular);
            c.put("EMAIL", Email);
            c.put("FECHA", Fecha);
            db.insert("PACIENTES",null,c);
            db.close();
            Toast.makeText(this,"Registro insertado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
