package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.macasjosue.R;

public class ActivitySensoresAcelerometro extends AppCompatActivity implements SensorEventListener {

    SensorManager manager;
    Sensor sensor;
    TextView x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensoresacelerometro);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        cargarComonentes();
    }

    private void cargarComonentes(){
        x = findViewById(R.id.lblSensorX);
        y = findViewById(R.id.lblSensorY);
        z= findViewById(R.id.lblSensorZ);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float a,b,c;
        a = event.values[0];
        b = event.values[1];
        c = event.values[2];

        x.setText(a+"");
        y.setText(b+"");
        z.setText(c+"");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this,sensor,manager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
