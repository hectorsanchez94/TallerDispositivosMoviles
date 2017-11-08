package hectorsanchez.ittepic.edu.mx.app_211;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String NAME="NAME",TEL="TEL",EMAIL="EMAIL";
    private EditText mEditTextName,mEditTextTel,mEditTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.textView);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String nombre = sharedPreferences.getString(NAME,null);
        String telefono = sharedPreferences.getString(TEL,null);
        String email = sharedPreferences.getString(EMAIL,null);


        if (nombre==null&&telefono==null&&email==null) {
            textView.setText("Hola Mundo");
        } else {
            textView.setText("Nombre: "+ nombre + "\n" + "Teléfono: "+ telefono + "\n" + "E-mail: "+ email + "\n");
        }
        mEditTextName = (EditText)findViewById(R.id.editText);
        mEditTextTel = (EditText)findViewById(R.id.editText3);
        mEditTextEmail = (EditText)findViewById(R.id.editText4);
    }

    public void saveText(View view) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString(NAME, mEditTextName.getText().toString());
        editor.putString(TEL, mEditTextTel.getText().toString());
        editor.putString(EMAIL, mEditTextEmail.getText().toString());
        editor.commit();

    }
}
