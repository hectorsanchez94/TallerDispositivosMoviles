package hectorsanchez.ittepic.edu.mx.gestor_pacientes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Medicamento extends AppCompatActivity {

    EditText edtNombre,edtPadecimiento,edtInstrucciones,edtFecConsulta,edtFecInicio,edtFecFin;
    CheckBox vigente;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        edtNombre = (EditText) findViewById(R.id.editText6);
        edtPadecimiento = (EditText) findViewById(R.id.editText7);
        edtInstrucciones = (EditText) findViewById(R.id.editText8);
        edtFecConsulta = (EditText) findViewById(R.id.editText9);
        edtFecInicio = (EditText) findViewById(R.id.editText10);
        edtFecFin = (EditText) findViewById(R.id.editText11);
        vigente = (CheckBox) findViewById(R.id.checkBox);
        btnGuardar = (Button) findViewById(R.id.btn_GuardarMedicamento);

        //if(getSupportActionBar()!=null){
          //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowHomeEnabled(true);
       // }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(edtNombre.getText().toString(),edtPadecimiento.getText().toString(),edtInstrucciones.getText().toString(),edtFecConsulta.getText().toString(),edtFecInicio.getText().toString(),edtFecFin.getText().toString(),vigente.isChecked());
                startActivity(new Intent(Medicamento.this,ListadoMedicamentos.class));
                finish();

            }
        });
    }
    private void guardar (String Nombre, String Padecimiento, String Instrucciones, String FecConsulta, String FecInicio, String FecFin, boolean Vigente){
        BaseHelper helper = new BaseHelper(this,"GestorPacientes.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("NOMBRE_M", Nombre);
            c.put("PADECIMIENTO", Padecimiento);
            c.put("INSTRUCCIONES", Instrucciones);
            c.put("FECHA_CONSULTA", FecConsulta);
            c.put("FECHA_INICIO", FecInicio);
            c.put("FECHA_FIN", FecFin);
            c.put("VIGENTE", Vigente);
            db.insert("MEDICAMENTOS",null,c);
            db.close();
            Toast.makeText(this,"Registro insertado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
