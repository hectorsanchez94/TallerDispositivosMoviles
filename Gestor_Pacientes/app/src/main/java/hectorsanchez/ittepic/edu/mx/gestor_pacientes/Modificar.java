package hectorsanchez.ittepic.edu.mx.gestor_pacientes;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hectorsanchez.ittepic.edu.mx.gestor_pacientes.entidades.*;
import hectorsanchez.ittepic.edu.mx.gestor_pacientes.entidades.Medicamento;

public class Modificar extends AppCompatActivity {

    EditText edtNombre,edtDireccion,edtCelular,edtEmail,edtFecha;
    Button btnModificar, btnAgregar;
    ListView listView;
    //String nombre,direccion,celular,email,fecha;
    //ArrayList<Usuario> personasList;

    ArrayList<String> listado;
    ArrayList<Medicamento> listaUsuarios;
    int id;
    BaseHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        listView = (ListView) findViewById(R.id.listview);

        conn=new BaseHelper(getApplicationContext(),"GestorPacientes.db",null,1);


        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        edtNombre = (EditText) findViewById(R.id.editText);
        edtDireccion = (EditText) findViewById(R.id.editText2);
        edtCelular = (EditText) findViewById(R.id.editText3);
        edtEmail = (EditText) findViewById(R.id.editText4);
        edtFecha = (EditText) findViewById(R.id.editText5);
        btnModificar = (Button) findViewById(R.id.btn_modificar);
        btnAgregar = (Button) findViewById(R.id.btn_agrega);

        cargarListado();

        Bundle objetoEnviado=getIntent().getExtras();
        Usuario user=null;

        if(objetoEnviado!=null){
            user= (Usuario) objetoEnviado.getSerializable("usuario");
            id = Integer.parseInt(user.getId().toString());
            edtNombre.setText(user.getNombre().toString());
            edtDireccion.setText(user.getDireccion().toString());
            edtCelular.setText(user.getCelular().toString());
            edtEmail.setText(user.getEmail().toString());
            edtFecha.setText(user.getFecha().toString());

        }

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(id,edtNombre.getText().toString(),edtDireccion.getText().toString(),edtCelular.getText().toString(),edtEmail.getText().toString(),edtFecha.getText().toString());
                startActivity(new Intent(Modificar.this,Listado.class));
                finish();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Usuario user=listaUsuarios.get(position);

                Bundle objetoEnviado=getIntent().getExtras();
                Usuario user=null;

                user= (Usuario) objetoEnviado.getSerializable("usuario");

                Intent intent=new Intent(Modificar.this,Receta.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);

                intent.putExtras(bundle);
                startActivity(intent);

                //startActivity(new Intent(Modificar.this,Receta.class));
                finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                id = Integer.parseInt(listado.get(position).split(" ")[0]);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Modificar.this);
                alertDialog.setMessage("¿Desea eliminar el registro?");
                alertDialog.setTitle("Advertencia");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        Eliminar(id);
                        cargarListado();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                alertDialog.show();

                return false;
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
    private void modificar (int Id, String Nombre, String Direccion, String Celular, String Email, String Fecha){
        BaseHelper helper = new BaseHelper(this,"GestorPacientes.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            String sql = "UPDATE PACIENTES SET NOMBRE='"+Nombre+"', DIRECCION='"+Direccion+"', CELULAR='"+Celular+"', EMAIL='"+Email+"', FECHA='"+Fecha+"' WHERE ID="+Id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(this,"Registro modificado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }


    private void cargarListado(){
        consultarListaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }

    private void obtenerLista() {
        listado=new ArrayList<String>();

        for (int i=0; i<listaUsuarios.size();i++){
            listado.add(listaUsuarios.get(i).getId()+" - "
                    +listaUsuarios.get(i).getNombre_m());
        }

    }

    private void consultarListaPersonas() {
        final String PAC_TABLE = "PACIENTES";
        final String MED_TABLE = "MEDICAMENTOS";
        final String DET_TABLE = "DETALLE";
        SQLiteDatabase db=conn.getReadableDatabase();

        Medicamento usuario=null;
        Usuario usu=null;

        listaUsuarios=new ArrayList<Medicamento>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+MED_TABLE+", "+PAC_TABLE+", "+DET_TABLE+" WHERE DETALLE.ID_MEDICAMENTOS=MEDICAMENTOS.ID AND DETALLE.ID_PACIENTES=PACIENTES.ID ORDER BY FECHA_CONSULTA, NOMBRE_M",null);

        while (cursor.moveToNext()){
            usuario=new Medicamento();
            usu =new Usuario();
            //usu.setId(cursor.getInt(0));
            usuario.setId(cursor.getInt(0));
            usuario.setNombre_m(cursor.getString(1));

            listaUsuarios.add(usuario);
            //listaUsuarios.add();
        }
        obtenerLista();
    }

    private void Eliminar (int Id){
        BaseHelper helper = new BaseHelper(this,"GestorPacientes.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            String sql = "DELETE FROM DETALLE WHERE ID_MEDICAMENTOS="+Id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(this,"Registro borrado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }


}
