package hectorsanchez.ittepic.edu.mx.gestor_pacientes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarMedicamento extends AppCompatActivity {

    EditText edtNombre,edtPadecimiento,edtInstrucciones,edtFecConsulta,edtFecInicio,edtFecFin;
    CheckBox vigente;
    Button btnModificar;
    int id;
    String nombre,padecimiento,instrucciones,fecconsulta,fecinicio,fecfin;
    Boolean vigencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_medicamento);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            id = bundle.getInt("ID");
            nombre = bundle.getString("NOMBRE_M");
            padecimiento = bundle.getString("PADECIMIENTO");
            instrucciones = bundle.getString("INSTRUCCIONES");
            fecconsulta = bundle.getString("FECHA_CONSULTA");
            fecinicio = bundle.getString("FECHA_INICIO");
            fecfin = bundle.getString("FECHA_FIN");
            vigencia = bundle.getBoolean("VIGENTE");
        }

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        edtNombre = (EditText) findViewById(R.id.editText6);
        edtPadecimiento = (EditText) findViewById(R.id.editText7);
        edtInstrucciones = (EditText) findViewById(R.id.editText8);
        edtFecConsulta = (EditText) findViewById(R.id.editText9);
        edtFecInicio = (EditText) findViewById(R.id.editText10);
        edtFecFin = (EditText) findViewById(R.id.editText11);
        vigente = (CheckBox) findViewById(R.id.checkBox);
        btnModificar = (Button) findViewById(R.id.btn_modificarMedicamento);

        edtNombre.setText(nombre);
        edtPadecimiento.setText(padecimiento);
        edtInstrucciones.setText(instrucciones);
        edtFecConsulta.setText(fecconsulta);
        edtFecInicio.setText(fecinicio);
        edtFecFin.setText(fecfin);
        vigente.setChecked(vigencia);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(id,edtNombre.getText().toString(),edtPadecimiento.getText().toString(),edtInstrucciones.getText().toString(),edtFecConsulta.getText().toString(),edtFecInicio.getText().toString(),edtFecFin.getText().toString(),vigente.isChecked());
                startActivity(new Intent(ModificarMedicamento.this,ListadoMedicamentos.class));
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            startActivity(new Intent(ModificarMedicamento.this,ListadoMedicamentos.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void modificar (int Id, String Nombre, String Padecimiento, String Instrucciones, String FecConsulta, String FecInicio, String FecFin, boolean Vigente){
        BaseHelper helper = new BaseHelper(this,"GestorPacientes.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            String sql = "UPDATE MEDICAMENTOS SET NOMBRE_M='"+Nombre+"', PADECIMIENTO='"+Padecimiento+"', INSTRUCCIONES='"+Instrucciones+"', FECHA_CONSULTA='"+FecConsulta+"', FECHA_INICIO='"+FecInicio+"', FECHA_FIN='"+FecFin+"', VIGENTE='"+Vigente+"' WHERE ID="+Id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(this,"Registro modificado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
