package com.example.macasjosue.ui.slideshow;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.macasjosue.R;

import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class SlideshowFragment extends Fragment implements SensorEventListener {

    SensorManager manager;
    Sensor sensor;
    TextView proximidad;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        manager = (SensorManager) this.getActivity().getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        proximidad = root.findViewById(R.id.lblSensorProximidadFrg);
        return root;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float a;
        a = event.values[0];
        proximidad.setText(a+"");
        if (a < sensor.getMaximumRange()){
            this.getActivity().getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }else {
            this.getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        manager.registerListener(this,sensor,manager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}