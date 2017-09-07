package hectorsanchez.ittepic.edu.mx.tpdm_15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnEnviar;
    EditText edtTexto;
    TextView txtPalabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = (Button)findViewById(R.id.button);
        edtTexto = (EditText) findViewById(R.id.texto_principal);
        txtPalabra = (TextView) findViewById(R.id.texto_secundario);

        edtTexto.setText("");

        Bundle i = this.getIntent().getExtras();
        if(i != null) {
            String datos = i.getString("datos");
            txtPalabra.setText(datos);}

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Secundario.class);
                intent.putExtra("datos",edtTexto.getText().toString());
                startActivity(intent);
            }
        });
    }
}
