package hectorsanchez.ittepic.edu.mx.a222_12400322;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView listView;
    FloatingActionButton btnFloting;
    ArrayList<String> listado;
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
    }

    private void cargarListado(){
        listado = ListaContactos();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }


    private ArrayList<String> ListaContactos(){
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this,"BD1.db",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT Id, nombre, telefono, email FROM CONTACTOS";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){

            do{

                String linea = c.getInt(0) + " " + c.getString(1)+ "\nTel: "+ c.getString(2)+ "\nE-mail: "+ c.getString(3);
                datos.add(linea);

            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }
}
