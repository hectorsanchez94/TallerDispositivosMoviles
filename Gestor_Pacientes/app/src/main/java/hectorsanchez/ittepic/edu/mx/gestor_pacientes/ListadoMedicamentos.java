package hectorsanchez.ittepic.edu.mx.gestor_pacientes;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoMedicamentos extends AppCompatActivity {


    ListView listView;
    FloatingActionButton btnMedicamento, btnCambiar;
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
        setContentView(R.layout.activity_listado_medicamentos);

        listView = (ListView) findViewById(R.id.list);
        btnMedicamento = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        btnCambiar = (FloatingActionButton) findViewById(R.id.floatingActionBut);

        cargarListado();

        btnMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListadoMedicamentos.this, Medicamento.class));
                finish();
            }
        });

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListadoMedicamentos.this, Listado.class));
                finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                id = Integer.parseInt(listado.get(position).split(" ")[0]);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListadoMedicamentos.this);
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
                String padecimiento = listado.get(position).split("  ")[2];
                String instrucciones = listado.get(position).split("  ")[3];
                String fechaConsulta = listado.get(position).split("  ")[4];
                String fechaInicio = listado.get(position).split("  ")[5];
                String fechaFin = listado.get(position).split("  ")[6];
                String vigente = listado.get(position).split("  ")[7];


                Intent intent = new Intent(ListadoMedicamentos.this, ModificarMedicamento.class);
                intent.putExtra("ID",id);
                intent.putExtra("NOMBRE_M",nombre);
                intent.putExtra("PADECIMIENTO",padecimiento);
                intent.putExtra("INSTRUCCIONES",instrucciones);
                intent.putExtra("FECHA_CONSULTA",fechaConsulta);
                intent.putExtra("FECHA_INICIO",fechaInicio);
                intent.putExtra("FECHA_FIN",fechaFin);
                intent.putExtra("VIGENTE",vigente);
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
        BaseHelper helper = new BaseHelper(this,"GestorPacientes.db",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT ID, NOMBRE_M, PADECIMIENTO, INSTRUCCIONES, FECHA_CONSULTA, FECHA_INICIO, FECHA_FIN, VIGENTE FROM MEDICAMENTOS ORDER BY FECHA_CONSULTA, NOMBRE_M";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){

            do{

                String linea = c.getInt(0) + "  " + c.getString(1)+ "\n  "+ c.getString(2)+ "\n  "+ c.getString(3)+ "\n  "+ c.getString(4)+ "\n  "+ c.getString(5)+ "\n  "+ c.getString(6)+ "\n  "+ c.getString(7);
                datos.add(linea);

            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }

    private void Eliminar (int Id){
        BaseHelper helper = new BaseHelper(this,"GestorPacientes.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            String sql = "DELETE FROM MEDICAMENTOS WHERE ID="+Id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(this,"Registro borrado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
