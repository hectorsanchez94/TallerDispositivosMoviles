package hectorsanchez.ittepic.edu.mx.a21_sqlitebasico;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciar clase DBAdapter
        DBAdapter db = new DBAdapter(this);
        //---Añadir datos
        db.open();
        long id = db.insertContact("Hector Sanchez", "heomsanchezpa@ittepic.edu..mx");
        id = db.insertContact("Gianny Rivera", "giedriveraza@ittepic.edu..mx");
        id = db.insertContact("Carlos Valdez", "jucavaldezco@ittepic.edu..mx");
        id = db.insertContact("Eduardo Espinoza", "edcaespinozaor@ittepic.edu..mx");
        id = db.insertContact("Paola Amaral", "paliamaralca@ittepic.edu..mx");
        id = db.insertContact("Mariela Bueno", "mabuenoro@ittepic.edu..mx");
        id = db.insertContact("Ernesto Pacheco", "erpachecomo@ittepic.edu..mx");
        id = db.insertContact("Miguel Jimenez", "miagjimeneznu@ittepic.edu..mx");
        db.close();
        db.open();
        // Explorar el cursos
        Cursor c = db.getAllContacts();
        if (c.moveToFirst()) {
            do {
                DisplayContact(c);
            } while (c.moveToNext());
        }
        db.close();
    }
    // Desplegar el contenido vía Toast
    public void DisplayContact(Cursor c) {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Name: " + c.getString(1) + "\n" +
                        "Email: " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }
}
