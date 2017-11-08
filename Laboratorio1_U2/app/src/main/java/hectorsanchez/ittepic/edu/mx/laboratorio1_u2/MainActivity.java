package hectorsanchez.ittepic.edu.mx.laboratorio1_u2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String NAME="NAME",TEL="TEL",DIREC="DIREC", FEC="FEC",HOR_I="12:00",HOR_F="12:00";
    private final String PLAT="",POST="",check="false",seek="0";
    private EditText mEditTextName,mEditTextTel,mEditTextDirec,mEditTextFec,mEditTextHor_i,mEditTextHor_f,mEditTextPlat,mEditTextPost;
    private CheckBox mantel;
    private SeekBar meseros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.textView2);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String nombre = sharedPreferences.getString(NAME,null);
        String telefono = sharedPreferences.getString(TEL,null);
        String direcc = sharedPreferences.getString(DIREC,null);
        String fecha = sharedPreferences.getString(FEC,null);
        String h_i = sharedPreferences.getString(HOR_I,null);
        String h_f = sharedPreferences.getString(HOR_F,null);
        String platillo = sharedPreferences.getString(PLAT,null);
        String postre = sharedPreferences.getString(POST,null);
        Boolean orden = sharedPreferences.getBoolean(check, false);
        Integer mesero = sharedPreferences.getInt(seek, 0);


        if (nombre==null&&telefono==null&&direcc==null&&fecha==null&&h_i==null&&h_f==null&&platillo==null&&postre==null&&orden==null&&mesero==null) {
            textView.setText("Hola Mundo");
        } else {
            textView.setText("Nombre: "+ nombre + "\n" + "Teléfono: "+ telefono + "\n" + "Dirección: "+ direcc + "\n"+ "Fecha: "+ fecha + "\n"+ "Hora de inicio: "+ h_i + "\n"+ "Hora de fin: "+ h_f + "\n"+ "Platillo: "+ platillo + "\n"+ "Postre: "+ postre + "\n"+"Manteles: " + orden + "\n" + "Meseros: " + mesero);
        }
        mEditTextName = (EditText)findViewById(R.id.editText);
        mEditTextTel = (EditText)findViewById(R.id.editText3);
        mEditTextDirec = (EditText)findViewById(R.id.editText4);
        mEditTextFec = (EditText)findViewById(R.id.editText2);
        mEditTextHor_i = (EditText)findViewById(R.id.editText5);
        mEditTextHor_f = (EditText)findViewById(R.id.editText6);
        mEditTextPlat = (EditText)findViewById(R.id.editText7);
        mEditTextPost = (EditText)findViewById(R.id.editText8);
        mantel = (CheckBox) findViewById(R.id.checkBox);
        meseros = (SeekBar) findViewById(R.id.seekBar);
    }

    public void saveText(View view) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString(NAME, mEditTextName.getText().toString());
        editor.putString(TEL, mEditTextTel.getText().toString());
        editor.putString(DIREC, mEditTextDirec.getText().toString());
        editor.putString(FEC, mEditTextFec.getText().toString());
        editor.putString(HOR_I, mEditTextHor_i.getText().toString());
        editor.putString(HOR_F, mEditTextHor_f.getText().toString());
        editor.putString(PLAT, mEditTextPlat.getText().toString());
        editor.putString(POST, mEditTextPost.getText().toString());
        editor.putBoolean(check, mantel.isChecked());
        editor.putInt(seek, meseros.getProgress());
        editor.commit();

    }
}
