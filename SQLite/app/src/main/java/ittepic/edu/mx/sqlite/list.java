package ittepic.edu.mx.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    ListView l1;
    ArrayList<Datos> arraydatos;
    Button add;
    AdminSQLiteOpenHelper bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list);
        add = (Button) findViewById(R.id.button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzar(view);
            }
        });

        //bd = new AdminSQLiteOpenHelper(this, "Diccionario", null, 1);

        try {
            l1 = (ListView) findViewById(R.id.list_item);
            arraydatos = new ArrayList<>();

            Datos datos;
            AdminSQLiteOpenHelper AppSQL = new AdminSQLiteOpenHelper(this, "Datos", null, 1);

            SQLiteDatabase database = AppSQL.getWritableDatabase();
            String sql = "SELECT*FROM Alumnos";
            Cursor registro = database.rawQuery(sql, null);


            if (registro.moveToFirst()) {
                do {
                    datos = new Datos(registro.getInt(0), registro.getString(1), registro.getString(2));
                    arraydatos.add(datos);

                } while (registro.moveToNext());
            }
            AdapterRegistros adapter = new AdapterRegistros(this, arraydatos);
            l1.setAdapter(adapter);
        }catch (SQLException e){

            Toast.makeText(list.this,e.getMessage(),Toast.LENGTH_LONG).show();

        }

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                TextView Id_ = (TextView) v.findViewById(R.id.concepto);
                Integer Id = Integer.parseInt(Id_.getText().toString());
                AbrirEdit(Id);
                finish();
            }
        });


    }

    public void AbrirEdit(Integer id){
        Intent appInfo = new Intent(this, MainActivity.class);
        appInfo.putExtra("ID",id);
        startActivity(appInfo);

    }

    public void lanzar(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }

}
