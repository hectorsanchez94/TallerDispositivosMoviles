package hectorsanchez.ittepic.edu.mx.gestor_pacientes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hectorsanchez.ittepic.edu.mx.gestor_pacientes.entidades.Usuario;
import hectorsanchez.ittepic.edu.mx.gestor_pacientes.entidades.Medicamento;

public class Receta extends AppCompatActivity {

    Spinner pacientes, medicamentos;
    TextView txtId,txtNombre,txtDireccion,txtCelular,txtEmail,txtFecha;
    int id;
    Button btnGuardarReceta;

    BaseHelper conn;

    ArrayList<String> listaPersonas, listaMedicamentos;
    ArrayList<Usuario> personasList;
    ArrayList<Medicamento> medicamentoslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);

        conn=new BaseHelper(getApplicationContext(),"GestorPacientes.db",null,1);

        pacientes = (Spinner) findViewById(R.id.spinner);
        medicamentos = (Spinner) findViewById(R.id.spinner2);
        txtId = (TextView) findViewById(R.id.txt_id);
        txtNombre = (TextView) findViewById(R.id.txt_nombre);
        txtDireccion = (TextView) findViewById(R.id.txt_direccion);
        txtCelular = (TextView) findViewById(R.id.txt_celular);
        txtEmail = (TextView) findViewById(R.id.txt_email);
        txtFecha = (TextView) findViewById(R.id.txt_fecha);
        btnGuardarReceta = (Button) findViewById(R.id.btn_guardar);

        consultarListaPersonas();
        consultarListaMedicamentos();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaPersonas);

        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaMedicamentos);

        pacientes.setAdapter(adaptador);

        medicamentos.setAdapter(adaptador2);


        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //pacientes.setEnabled(false);
        pacientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if (position!=0){
                    txtId.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtDireccion.setText(personasList.get(position-1).getDireccion());
                    txtCelular.setText(personasList.get(position-1).getCelular());
                    txtEmail.setText(personasList.get(position-1).getEmail());
                    txtFecha.setText(personasList.get(position-1).getFecha());
                }else{
                    txtId.setText("");
                    txtNombre.setText("");
                    txtDireccion.setText("");
                    txtCelular.setText("");
                    txtEmail.setText("");
                    txtFecha.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        medicamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position!=0){
                    txtId.setText("Medicamento: "+medicamentoslist.get(position-1).getNombre_m().toString());
                    txtNombre.setText("Padecimiento: "+medicamentoslist.get(position-1).getPadecimiento());
                    txtDireccion.setText("Instrucciones: "+medicamentoslist.get(position-1).getInstrucciones());
                    txtCelular.setText("Fecha de consulta: "+medicamentoslist.get(position-1).getFecha_consulta());
                    txtEmail.setText("Fecha de inicio: "+medicamentoslist.get(position-1).getFecha_inicio());
                    txtFecha.setText("Fecha de fin: "+medicamentoslist.get(position-1).getFecha_fin());
                }else{
                    txtId.setText("");
                    txtNombre.setText("");
                    txtDireccion.setText("");
                    txtCelular.setText("");
                    txtEmail.setText("");
                    txtFecha.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGuardarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarMascota();
                startActivity(new Intent(Receta.this,Listado.class));
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            startActivity(new Intent(Receta.this,Listado.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void consultarListaMedicamentos(){
        final String MED_TABLE = "MEDICAMENTOS";

        SQLiteDatabase db=conn.getReadableDatabase();

        Medicamento medicamento=null;

        medicamentoslist = new ArrayList<Medicamento>();

        Cursor cursor2=db.rawQuery("SELECT * FROM "+MED_TABLE+" ORDER BY FECHA_CONSULTA, NOMBRE_M",null);

        while (cursor2.moveToNext()){
            medicamento = new Medicamento();
            medicamento.setId(cursor2.getInt(0));
            medicamento.setNombre_m(cursor2.getString(1));
            medicamento.setPadecimiento(cursor2.getString(2));
            medicamento.setInstrucciones(cursor2.getString(3));
            medicamento.setFecha_consulta(cursor2.getString(4));
            medicamento.setFecha_inicio(cursor2.getString(5));
            medicamento.setFecha_fin(cursor2.getString(6));
            medicamento.setVigente(Boolean.parseBoolean(cursor2.getString(7)));

            Log.i("ID",medicamento.getId().toString());
            Log.i("NOMBRE_M",medicamento.getNombre_m());
            Log.i("PADECIMIENTO",medicamento.getPadecimiento());
            Log.i("INSTRUCCIONES",medicamento.getInstrucciones());
            Log.i("FECHA_CONSULTA",medicamento.getFecha_consulta());
            Log.i("FECHA_INICIO",medicamento.getFecha_inicio());
            Log.i("FECHA_FIN",medicamento.getFecha_fin());
            Log.i("VIGENTE",medicamento.isVigente()+"");

            medicamentoslist.add(medicamento);

        }
        obtenerLista2();

    }

    private void obtenerLista2() {

        listaMedicamentos=new ArrayList<String>();
        listaMedicamentos.add("Seleccione una opción");


      for(int i=0;i<medicamentoslist.size();i++){
            listaMedicamentos.add(medicamentoslist.get(i).getId()+" - "+medicamentoslist.get(i).getNombre_m());
      }
    }

    private void consultarListaPersonas() {

        final String PAC_TABLE = "PACIENTES";

        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario persona=null;

        personasList =new ArrayList<Usuario>();


        Cursor cursor=db.rawQuery("SELECT * FROM "+PAC_TABLE,null);


        while (cursor.moveToNext()){
            persona=new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setDireccion(cursor.getString(2));
            persona.setCelular(cursor.getString(3));
            persona.setEmail(cursor.getString(4));
            persona.setFecha(cursor.getString(5));

            Log.i("ID",persona.getId().toString());
            Log.i("NOMBRE",persona.getNombre());
            Log.i("DIRECCION",persona.getDireccion());
            Log.i("CELULAR",persona.getCelular());
            Log.i("EMAIL",persona.getEmail());
            Log.i("FECHA",persona.getFecha());

            personasList.add(persona);

        }
        obtenerLista();


    }

    private void obtenerLista() {
        Bundle objetoEnviado=getIntent().getExtras();
        Usuario user=null;

        if(objetoEnviado!=null){
            user= (Usuario) objetoEnviado.getSerializable("usuario");
            id = Integer.parseInt(user.getId().toString());

        }
        listaPersonas=new ArrayList<String>();
        listaPersonas.add(personasList.get(id-1).getId()+" - "+personasList.get(id-1).getNombre());

    }
    private void registrarMascota() {

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        ContentValues values2=new ContentValues();


        int idCombo= (int) pacientes.getSelectedItemId();
        int idCombo2 = (int) medicamentos.getSelectedItemId();
        /**
         * Valida la seleccion del combo de dueños, si el usuario elige "seleccione" entonces
         * se retorna el id 0 ya que la palabra "seleccione" se encuentra en la pos 0 del combo,
         * sinó entonces se retorna la posicion del combo para consultar el usuario almacenado en la lista
         */
        if (personasList.get(id-1).getId()!=0){
            Bundle objetoEnviado=getIntent().getExtras();
            Usuario user=null;
            user= (Usuario) objetoEnviado.getSerializable("usuario");
            id = Integer.parseInt(user.getId().toString());

            Log.i("TAMAÑO",personasList.size()+"");
            Log.i("TAMAÑO2",medicamentoslist.size()+"");
            Log.i("id combo",personasList.get(id-1).getId()+"");
            Log.i("id combo2",idCombo2+"");
            Log.i("id combo - 1",personasList.get(id-1).getId()+"");
            Log.i("id combo2 - 1",(idCombo2-1)+"");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo
            int idDuenio=personasList.get(id-1).getId();
            int idMed=medicamentoslist.get(idCombo2-1).getId();

            Log.i("id DUEÑO",personasList.get(id-1).getId()+"");
            Log.i("id SUEÑO2",idMed+"");

            values.put("ID_PACIENTES",idDuenio);
            values.put("ID_MEDICAMENTOS",idMed);

            Long idResultante=db.insert("DETALLE","ID_MEDICAMENTOS",values);
            //Long idResultante2=db.insert("DETALLE","ID_MEDICAMENTOS",values);

            Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
            db.close();

        }else{
            Toast.makeText(getApplicationContext(),"Debe seleccionar un Dueño",Toast.LENGTH_LONG).show();
        }


    }

}
