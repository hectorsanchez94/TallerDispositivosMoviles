package hectorsanchez.ittepic.edu.mx.sensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView1);
        sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        List<Sensor> lista = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder data = new StringBuilder();
        for(int i=1; i<lista.size(); i++){
            data.append("Nombre :"+lista.get(i).getName() + "\n");
            data.append("Fabricante: "+lista.get(i).getVendor() + "\n");
            data.append("Version: "+lista.get(i).getVersion() + "\n");
            data.append("Maximun range: "+lista.get(i).getMaximumRange()+ "\n");
            data.append("Minimun delay: "+lista.get(i).getMinDelay()+ "\n");
            data.append("Type: "+lista.get(i).getStringType()+ "\n");
            data.append("Power: "+lista.get(i).getPower()+ "\n\n");
        }
        textView.setText(data);
    }
}
