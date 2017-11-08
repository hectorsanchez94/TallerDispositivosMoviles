package hectorsanchez.ittepic.edu.mx.a223_12400322;

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
import android.content.DialogInterface;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView listView;
    FloatingActionButton btnFloting;
    ArrayList<String> listado;
    int id;
    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        listView = (ListView) findViewById(R.id.list);
        btnFloting = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        cargarListado();


        btnFloting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Listado.this, MainActivity.class));
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
                int id = Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre = listado.get(position).split("  ")[1];
                String telefono = listado.get(position).split("  ")[2];
                String email = listado.get(position).split("  ")[3];

                Intent intent = new Intent(Listado.this, Modificar.class);
                intent.putExtra("ID",id);
                intent.putExtra("NOMBRE",nombre);
                intent.putExtra("TELEFONO",telefono);
                intent.putExtra("EMAIL",email);
                startActivity(intent);
                finish();
            }
        });

    }



    private void cargarListado(){
        listado = ListaContactos();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }


    private ArrayList<String> ListaContactos(){
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this,"BD2.db",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT Id, nombre, telefono, email FROM CONTACTOS";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){

            do{

                String linea = c.getInt(0) + "  " + c.getString(1)+ "\n  "+ c.getString(2)+ "\n  "+ c.getString(3);
                datos.add(linea);

            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }

    private void Eliminar (int Id){
        BaseHelper helper = new BaseHelper(this,"BD2.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            String sql = "DELETE FROM CONTACTOS WHERE ID="+Id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(this,"Registro borrado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
