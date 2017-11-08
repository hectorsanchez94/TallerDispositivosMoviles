package hectorsanchez.ittepic.edu.mx.gestor_pacientes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hector on 27/09/17.
 */

public class BaseHelper extends SQLiteOpenHelper {

    String tablapacientes = "CREATE TABLE PACIENTES(ID INTEGER PRIMARY KEY, NOMBRE TEXT, DIRECCION TEXT, CELULAR TEXT, EMAIL TEXT, FECHA TEXT)";
    String tablamedicamentos = "CREATE TABLE MEDICAMENTOS(ID INTEGER PRIMARY KEY, NOMBRE_M TEXT, PADECIMIENTO TEXT, INSTRUCCIONES TEXT, FECHA_CONSULTA TEXT, FECHA_INICIO TEXT, FECHA_FIN TEXT, VIGENTE BOOLEAN, ID_PACIENTE INTEGER)";
    String tabladetalle = "CREATE TABLE DETALLE(ID_PACIENTES integer, ID_MEDICAMENTOS integer); ALTER TABLE DETALLE ADD CONSTRAINT FK_ID_MEDICAMENTOS FOREIGN KEY (ID_PACIENTES) REFERENCES MEDICAMENTOS (ID); ALTER TABLE DETALLE ADD CONSTRAINT FK_ID_PACIENTES FOREIGN KEY (ID_PACIENTES) REFERENCES PACIENTES (ID)";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablapacientes);
        db.execSQL(tablamedicamentos);
        db.execSQL(tabladetalle);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE PACIENTES");
        db.execSQL("DROP TABLE MEDICAMENTOS");
        db.execSQL("DROP TABLE DETALLE");
        db.execSQL(tablapacientes);
        db.execSQL(tablamedicamentos);
        db.execSQL(tabladetalle);
    }
}
