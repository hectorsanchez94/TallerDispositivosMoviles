package hectorsanchez.ittepic.edu.mx.tpdm_14;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String redesSociales[]=new String[]{"Ceviche camarón crudo","Ceviche camarón cocido","Ceviche en Aguachile",
            "Camarones empanizados","Camarones a la diabla","Camarones al mojo de ajo","Filete empanizado","Filete a la mantequilla"};

    private String control[]=new String[]{"$95.00","$105.00","$95.00","$130.00","$130.00","$120.00","$115.00","$80.00"};

    private String descripcion[]=new String[]{"Camarón pacotilla, cebolla blanca o morada, aceite de Oliva, Cilantro, limón, pepino, tomate, sal",
            "Camarón, Pulpo pescado, Cebolla morada , chile serrano , tomate, limones, jitomates, cebolla morada",
            "Camarón sin cascara y descabezado, jugo de limón, tomate, cilantro, pepinos, cebolla mediana/grande, chiles jalapeños desvenados, Sal y pimienta",
            "Camarón, leche, huevos, pan molido, knorr suiza, ajo, pimienta, sal",
            "Camarones, ajo, cebolla, mantequilla, salsa huichol, catsup, refresco de mandarina, sal",
            "Camarones pelados y desvenados, chile guajillo s/semillas, aceite de aguacate u oliva, salsa inglesa, salsa soya, espárragos, ensalada para acompañar, ajos pelados",
            "Filetes de pescado(lenguado, mero, panga, merluza), Revolver y refrigerar, huevos batidos, pan rallado, sal, pimienta, ajo y perejil, aceite suficiente para freír, harina",
            "Cebolla fileteada, basa, mantequilla, limones, tequila, Sal , pimienta"};

    private Integer[] imgid={
            R.drawable.ccrudo,
            R.drawable.ccocido,
            R.drawable.aguachile,
            R.drawable.cempanizados,
            R.drawable.diabla,
            R.drawable.ajillo,
            R.drawable.fempanizado,
            R.drawable.mantequilla
    };
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LenguajeListAdpter adapter=new LenguajeListAdpter(this,redesSociales,control,imgid);
        lista=(ListView)findViewById(R.id.listView);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               // String itemValue = (String) listView.getItemAtPosition(position);
                String Slecteditem= redesSociales[+position];
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Description.class);
                intent.putExtra("logo",imgid[position]);
                intent.putExtra("nombre",redesSociales[position]);
                intent.putExtra("desc",descripcion[position]);
                startActivity(intent);
            }
        });
    }
}
