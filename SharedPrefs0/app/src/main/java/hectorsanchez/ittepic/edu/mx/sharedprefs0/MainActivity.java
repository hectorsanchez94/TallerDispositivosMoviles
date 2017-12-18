package hectorsanchez.ittepic.edu.mx.sharedprefs0;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String NAME="NAME";
    private EditText mEditTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.textView1);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String texto = sharedPreferences.getString(NAME,null);
        if (texto==null) {textView.setText("Hola Mundo");}
        else {textView.setText("--> " + texto + "!");}
        mEditTextName = (EditText)findViewById(R.id.editText1);
    }
    public void saveText(View view) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString(NAME, mEditTextName.getText().toString());
        editor.commit();
    }
}
