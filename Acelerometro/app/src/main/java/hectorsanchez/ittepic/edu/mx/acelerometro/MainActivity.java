package hectorsanchez.ittepic.edu.mx.acelerometro;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener{

    TextView x,y,z;

    private  Sensor mAccelerometer;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        x = (TextView)findViewById(R.id.xID);

        y = (TextView)findViewById(R.id.yID);

        z = (TextView)findViewById(R.id.zID);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    protected void onResume()

    {

        super.onResume();

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (sensors.size() > 0) //dispositivo android tiene acelerometro

        {

            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);

        }

    }

    protected void onPause()
    {

        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        mSensorManager.unregisterListener(this, mAccelerometer);

        super.onPause();

    }

    protected void onStop()
    {

        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        mSensorManager.unregisterListener(this, mAccelerometer);

        super.onStop();

    }

    @Override

    public void onSensorChanged(SensorEvent event) {

        this.x.setText("X = "+event.values[SensorManager.DATA_X]);

        this.y.setText("Y = "+event.values[SensorManager.DATA_Y]);

        this.z.setText("Z = "+event.values[SensorManager.DATA_Z]);

    }

    @Override

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
