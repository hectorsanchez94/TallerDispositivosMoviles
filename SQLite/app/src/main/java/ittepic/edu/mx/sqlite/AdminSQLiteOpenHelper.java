package ittepic.edu.mx.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hector on 28/02/17.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, nombre, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE TablaDic (id INTEGER PRIMARY KEY,Concepto VARCHAR(200), defini VARCHAR(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {



    }

}
