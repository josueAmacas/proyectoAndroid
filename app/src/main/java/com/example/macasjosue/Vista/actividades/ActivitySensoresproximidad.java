package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.macasjosue.R;

public class ActivitySensoresproximidad extends AppCompatActivity implements SensorEventListener {

    SensorManager manager;
    Sensor sensor;
    TextView proximidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensoresproximidad);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        proximidad = findViewById(R.id.lblSensorProximidad);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float a;
        a = event.values[0];
        proximidad.setText(a+"");
        if (a < sensor.getMaximumRange()){
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }else {
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }
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
