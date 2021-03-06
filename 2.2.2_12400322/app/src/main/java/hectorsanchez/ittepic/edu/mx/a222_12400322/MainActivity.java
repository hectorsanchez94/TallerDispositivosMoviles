package hectorsanchez.ittepic.edu.mx.a222_12400322;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre, edtTelefono,edtEmail;
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edt_nombre);
        edtTelefono = (EditText) findViewById(R.id.edt_tel);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnGuardar = (Button) findViewById(R.id.btn_guardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(edtNombre.getText().toString(),edtTelefono.getText().toString(),edtEmail.getText().toString());
                startActivity(new Intent(MainActivity.this,Listado.class));
                finish();

            }
        });
    }

    private void guardar (String Nombre, String Telefono, String Email){
        BaseHelper helper = new BaseHelper(this,"BD1.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("nombre", Nombre);
            c.put("telefono", Telefono);
            c.put("email", Email);
            db.insert("CONTACTOS",null,c);
            db.close();
            Toast.makeText(this,"Registro insertado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
