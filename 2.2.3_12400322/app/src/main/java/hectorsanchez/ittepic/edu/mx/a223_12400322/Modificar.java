package hectorsanchez.ittepic.edu.mx.a223_12400322;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {

    EditText edtNombre, edtTelefono,edtEmail;
    Button btnModificar;
    int id;
    String nombre,telefono,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            id = bundle.getInt("ID");
            nombre = bundle.getString("NOMBRE");
            telefono = bundle.getString("TELEFONO");
            email = bundle.getString("EMAIL");
        }

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        edtNombre = (EditText) findViewById(R.id.edt_nombre);
        edtTelefono = (EditText) findViewById(R.id.edt_tel);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnModificar = (Button) findViewById(R.id.btn_modificar);

        edtNombre.setText(nombre);
        edtTelefono.setText(telefono);
        edtEmail.setText(email);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(id,edtNombre.getText().toString(),edtTelefono.getText().toString(),edtEmail.getText().toString());
                startActivity(new Intent(Modificar.this,Listado.class));
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            startActivity(new Intent(Modificar.this,Listado.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void modificar (int Id, String Nombre, String Telefono, String Email){
        BaseHelper helper = new BaseHelper(this,"BD2.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            String sql = "UPDATE CONTACTOS SET NOMBRE='"+Nombre+"', TELEFONO='"+Telefono+"', EMAIL='"+Email+"' WHERE ID="+Id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(this,"Registro modificado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
