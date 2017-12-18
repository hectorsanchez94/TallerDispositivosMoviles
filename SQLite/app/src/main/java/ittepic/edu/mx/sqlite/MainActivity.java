package ittepic.edu.mx.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText ed1, ed2,ed3;
    AdminSQLiteOpenHelper bd;
    Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = (EditText) findViewById(R.id.concepto);
        ed2 = (EditText) findViewById(R.id.definicion);
        ed3 = (EditText) findViewById(R.id.editText);
        bd = new AdminSQLiteOpenHelper(this, "Datos", null, 1);


    }

    public void create (View view){

        try {

            SQLiteDatabase base = bd.getWritableDatabase();
            String SQL = "INSERT INTO TablaDic VALUES('" + ed1.getText() + "','" + ed2.getText() + "','" + ed3.getText() +"')";
            base.execSQL(SQL);
            base.close();
            Toast.makeText(MainActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();


            ed1.setText(""); ed2.setText(""); ed2.setText("");

        } catch (SQLException e) {

            Toast.makeText(MainActivity.this, e.getMessage() + "Existen campos vacios", Toast.LENGTH_SHORT).show();

        }

    }

    public void read(View v) {

        try {
            SQLiteDatabase base = bd.getReadableDatabase();

            String sql = "SELECT * FROM TablaDic WHERE Concepto='"+ed1.getText()+"'";


            Cursor cursor = base.rawQuery(sql, null);



            if (cursor.moveToFirst()) {
                do {
                    ed2.setText(cursor.getString(2));
                    ed3.setText(cursor.getString(2));

                } while (cursor.moveToNext());

            } else {
                Toast.makeText(MainActivity.this, "no existe", Toast.LENGTH_LONG).show();
                ed1.setText(""); ed2.setText("");

            }
            cursor.close();
            base.close();
        } catch (SQLiteException sqle) {
            Toast.makeText(MainActivity.this, "no existe", Toast.LENGTH_SHORT).show();
        }

    }

    public void delete(View v) {

        try {

            SQLiteDatabase base = bd.getWritableDatabase();
            String SQL ="DELETE FROM TablaDic  WHERE Concepto='" +ed1.getText() + "'";
            base.execSQL(SQL);
            base.close();

            Toast.makeText(MainActivity.this, "Se elimino Correctamente", Toast.LENGTH_SHORT).show();
            ed1.setText(""); ed2.setText("");

        } catch (SQLiteException sqle) {
            Toast.makeText(MainActivity.this, "no existe", Toast.LENGTH_SHORT).show();
            ed1.setText(""); ed2.setText("");
        }
    }

    public void update(View v) {

        try {

            SQLiteDatabase base = bd.getWritableDatabase();
            String SQL ="UPDATE TablaDic SET defini='"+ed2.getText()+"' WHERE Concepto='" +ed1.getText() + "'";
            base.execSQL(SQL);
            base.close();
            ed2.setText("");
            read(v);

            Toast.makeText(MainActivity.this, "Se actualizo Correctamente", Toast.LENGTH_SHORT).show();

            ed1.setText(""); ed2.setText("");

        } catch (SQLiteException sqle) {
            Toast.makeText(MainActivity.this, "no existe", Toast.LENGTH_SHORT).show();
            ed1.setText(""); ed2.setText("");
        }

    }

}
