package hectorsanchez.ittepic.edu.mx.proyectou2cprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hector on 03/04/17.
 */

public class Conexion extends SQLiteOpenHelper {

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE TablaImg (id INTEGER PRIMARY KEY AUTOINCREMENT,Concepto VARCHAR(200), image BLOB)");
        db.execSQL("CREATE TABLE TablaAfi (id INTEGER PRIMARY KEY AUTOINCREMENT,Concepto VARCHAR(200))");
        db.execSQL("CREATE TABLE TbAfiDet (id INTEGER,Concepto VARCHAR(200))");

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public  void InsertarPer(String nombre, byte[] img, Conexion bd){
        try {

            SQLiteDatabase base = bd.getWritableDatabase();
            String SQL = "INSERT INTO TablaImg VALUES(NULL,'" + nombre + "','" + img + "')";
            base.execSQL(SQL);
            base.close();

        }
        catch (SQLiteException e) {
        }

    }

    public  void InsertarAfi(String nombre,Conexion bd){
        try {

            SQLiteDatabase base = bd.getWritableDatabase();
            String SQL = "INSERT INTO TablaAfi VALUES(NULL,'" + nombre + "')";
            base.execSQL(SQL);
            base.close();

        }
        catch (SQLiteException e) {
        }

    }
    public  void InsertarAfiDet(Integer Id, String nombre,Conexion bd){
        try {

            SQLiteDatabase base = bd.getWritableDatabase();
            String SQL = "INSERT INTO TablaAfi VALUES(NULL," + Id + ",'"+nombre+"')";
            base.execSQL(SQL);
            base.close();

        }
        catch (SQLiteException e) {
        }

    }


}

