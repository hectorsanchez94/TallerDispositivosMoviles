package hectorsanchez.ittepic.edu.mx.a223_12400322;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hector on 19/09/17.
 */

public class BaseHelper extends SQLiteOpenHelper{

    String tabla = "CREATE TABLE CONTACTOS(ID INTEGER PRIMARY KEY, NOMBRE TEXT, TELEFONO TEXT, EMAIL TEXT)";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE "+tabla);
        db.execSQL(tabla);

    }
}
