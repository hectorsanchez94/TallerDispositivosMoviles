package hectorsanchez.ittepic.edu.mx.prueba;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String check="false",seek="0";
    //private final int seek = 0;
    private CheckBox mantel;
    private SeekBar meseros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Boolean orden = sharedPreferences.getBoolean(check, false);
        Integer mesero = sharedPreferences.getInt(seek, 0);

        if (check == null && seek == null) {
            textView.setText("Hola Mundo");
        } else {
            textView.setText("Manteles: " + orden + "\n" + "Teléfono: " + mesero);
        }

        mantel = (CheckBox) findViewById(R.id.checkBox);
        meseros = (SeekBar) findViewById(R.id.seekBar);
    }
    public void saveText(View view) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putBoolean(check, mantel.isChecked());
        editor.putInt(seek, meseros.getProgress());
        editor.commit();

    }
}
