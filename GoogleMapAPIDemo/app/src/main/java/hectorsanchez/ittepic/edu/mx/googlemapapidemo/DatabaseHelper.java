package hectorsanchez.ittepic.edu.mx.googlemapapidemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hector on 25/11/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    String SQLcreate = "CREATE TABLE position (lat TEXT, lon TEXT)";
    private static final String DATABASE_NAME = "Coordenadas.db";
    public static  final String LATITUD = "lat";
    public static  final String LONGITUD = "lon";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLcreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        android.util.Log.v("position","Upgrading database, wich will destroy all old date");
        db.execSQL("DROP TABLE IF EXISTS position");
        db.execSQL(SQLcreate);
    }
}
