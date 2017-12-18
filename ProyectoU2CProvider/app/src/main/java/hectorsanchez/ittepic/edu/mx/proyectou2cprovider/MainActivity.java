package hectorsanchez.ittepic.edu.mx.proyectou2cprovider;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1;
    List adapter;
    FloatingActionButton ft;
    Bitmap[] imagenes = null;

    ByteArrayInputStream[] imgStream=null;
    String[] l = null;
    int[] id=null;
    Conexion bd;
    Bitmap bmp=null;
    byte[] blob=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new Conexion(this, "Diccionario", null, 1);


        ft=(FloatingActionButton) findViewById(R.id.btnfloat);
        ft.setOnClickListener(this);
        actua();
        final ListView lista = (ListView) findViewById(R.id.listViewed);
        adapter = new List(this, l, imagenes);
        lista.setAdapter(adapter);
    }

    public void onClick(View view) {
        //Toast.makeText(MainActivity.this,"en", Toast.LENGTH_SHORT).show();
        switch (view.getId()) {


            case R.id.btnfloat:

                Intent insertar= new Intent(MainActivity.this, New.class);
                startActivity(insertar);
                break;
        }
    }

    public void actua() {





        try {
            SQLiteDatabase base = bd.getReadableDatabase();
            String sql = "SELECT * FROM TablaImg";

            Cursor cursor = base.rawQuery(sql, null);

            l = new String[cursor.getCount()];

            imagenes = new Bitmap [cursor.getCount()];


            id = new int[cursor.getCount()];
            int i = 0;
            if (cursor.moveToFirst()) {
                do {


                    l[i] = cursor.getString(1);
                    // id[i]= Integer.parseInt(cursor.getString(0));
                    //imagenes[i] = R.drawable.azul;
                    //Toast.makeText(MainActivity.this, ""+R.drawable.azul, Toast.LENGTH_LONG).show();
                    //imagenes[i] =Integer.parseInt(cursor.getString(2));

                    blob =cursor.getBlob(2);
                    bmp= BitmapFactory.decodeByteArray(blob,0,blob.length);


                    imagenes[i]= bmp;


                    i++;


                } while (cursor.moveToNext());

            } else {
                Toast.makeText(MainActivity.this, "no existe", Toast.LENGTH_LONG).show();

            }
            cursor.close();
            base.close();
        } catch (SQLiteException sqle) {
            //imprime el error
        }

    }
}
