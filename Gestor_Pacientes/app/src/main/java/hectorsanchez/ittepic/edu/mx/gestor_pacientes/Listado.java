package hectorsanchez.ittepic.edu.mx.gestor_pacientes;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hectorsanchez.ittepic.edu.mx.gestor_pacientes.entidades.Usuario;

public class Listado extends AppCompatActivity {

    ListView listView;
    FloatingActionButton btnFloting, btnMedicamento, btnCambiar;
    ArrayList<String> listado;
    ArrayList<Usuario> listaUsuarios;
    int id;
    BaseHelper conn;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);


        conn=new BaseHelper(getApplicationContext(),"GestorPacientes.db",null,1);

        listView = (ListView) findViewById(R.id.list);
        btnFloting = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        btnMedicamento = (FloatingActionButton) findViewById(R.id.floatingMedicamento);
        btnCambiar = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        cargarListado();


        btnMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Listado.this, Medicamento.class));
                finish();
            }
        });

        btnFloting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Listado.this, MainActivity.class));
                finish();
            }
        });
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Listado.this, ListadoMedicamentos.class));
                finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                id = Integer.parseInt(listado.get(position).split(" ")[0]);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Listado.this);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Usuario user=listaUsuarios.get(position);

                Intent intent=new Intent(Listado.this,Modificar.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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
                    +listaUsuarios.get(i).getNombre());
        }

    }

    private void consultarListaPersonas() {
        final String PAC_TABLE = "PACIENTES";
        final String MED_TABLE = "MEDICAMENTOS";
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario usuario=null;
        listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+PAC_TABLE+" ORDER BY NOMBRE",null);

        while (cursor.moveToNext()){
            usuario=new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setDireccion(cursor.getString(2));
            usuario.setCelular(cursor.getString(3));
            usuario.setEmail(cursor.getString(4));
            usuario.setFecha(cursor.getString(5));

            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }

    private void Eliminar (int Id){
        BaseHelper helper = new BaseHelper(this,"GestorPacientes.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            String sql = "DELETE FROM PACIENTES WHERE ID="+Id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(this,"Registro borrado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

}
