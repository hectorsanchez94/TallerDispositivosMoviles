package hectorsanchez.ittepic.edu.mx.tpdm_14;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        TextView txtTitle = (TextView) findViewById(R.id.texto_principal);
        ImageView imageView = (ImageView) findViewById(R.id.icon);
        TextView etxDescripcion = (TextView) findViewById(R.id.texto_secundario);


        Intent i = getIntent();
        int FlagId = i.getIntExtra("logo",0);
        String nom = i.getExtras().getString("nombre");
        String des = i.getExtras().getString("desc");
        imageView.setImageResource(FlagId);
        txtTitle.setText(nom);
        etxDescripcion.setText(des);

    }
}
