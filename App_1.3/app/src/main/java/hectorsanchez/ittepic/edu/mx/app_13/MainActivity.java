package hectorsanchez.ittepic.edu.mx.app_13;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String redesSociales[]=new String[]{"ABREGO DELGADO ALEXIA ARIANA",
            "CARO LOPEZ LUIS RICARDO",
            "CARVAJAL GUTIERREZ RAUL RAFAEL",
            "CASILLAS UREÑA FERMIN MICHET",
            "CRESPO DURAN PABLO ARTURO",
            "ESPINO SERRANO CESAR RAMSES",
            "ESPINOZA ORONIA CARLOS EDUARDO",
            "FREGOSO CORONA JESÚS EDUARDO",
            "GALAVIZ ARROYO OLIVER JASIEL",
            "GALINDO DELGADO DONADONY DONAJI",
            "GONZALEZ CASTELLANOS EDGAR FABRIZIO",
            "HERNANDEZ SANDOVAL ABRIL YARELI",
            "JIMENEZ ORTIZ JUAN DANIEL",
            "LIZOLA CHAVARÍN LUIS FERNANDO",
            "MEDRANO BARAJAS JUAN DANIEL",
            "RAMIREZ GARCIA JUAN RAMON",
            "RODRIGUEZ ADAME OLIVER GIOVANNY",
            "SANCHEZ JIMENEZ ANA PATRICIA",
            "SÁNCHEZ PARTIDA HÉCTOR OMAR",
            "VALDEZ CORREA CARLOS GERMAN",
            "CARRILLO GONZÁLEZ CARLOS ARTURO",
            "HERNANDEZ BAEZA JAVIER",
            "IBARRA MARAVILLAS JONATHAN GUADALUPE",
            "MARIN BAUTISTA IRVING ALDAHYR",
            "OCAMPO AGUILAR JESÚS ANDRÉS",
            "SALAZAR NAVARRO IRAN JAIR",
            "TEJEDA MORA CESARFacebook"};
    private String control[]=new String[]{"13400378-ISC",
            "13400395-ISC",
            "13400396-ISC",
            "12400256-ISC",
            "13400397-ISC",
            "13400402-ISC",
            "12401119-ISC",
            "13400405-ISC",
            "13400406-ISC",
            "13400408-ISC",
            "13400419-ISC",
            "13400430-ISC",
            "13400436-ISC",
            "13400441-ISC",
            "13400457-ISC",
            "13400478-ISC",
            "13400507-ISC",
            "13400491-ISC",
            "12400322-ISC",
            "12400326-ISC",
            "13401087-ITIC",
            "13401367-ITIC",
            "13401097-ITIC",
            "12400663-ITIC",
            "13401100-ITIC",
            "11400309-ITIC",
            "13401105-ITIC"};

    private Integer[] imgid={
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.valde,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.yo,
            R.drawable.oronia,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu,
            R.drawable.usu
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
                String Slecteditem= redesSociales[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
