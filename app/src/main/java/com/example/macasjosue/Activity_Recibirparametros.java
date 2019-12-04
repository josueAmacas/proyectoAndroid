package com.example.macasjosue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity_Recibirparametros extends AppCompatActivity {

    TextView nombre,apellido,recibirA, recibirN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__recibirparametros);
        cargarComponentes();


    }

    private void cargarComponentes(){
        nombre = findViewById(R.id.lblNombre);
        apellido = findViewById(R.id.lblApellido);
        recibirN = findViewById(R.id.lblNombreRecibir);
        recibirA = findViewById(R.id.lblApellidoRecibir);

        String nombres = getIntent().getStringExtra("nombre");
        String apellidos = getIntent().getStringExtra("apellido");

        recibirN.setText(nombres);
        recibirA.setText(apellidos);
    }
}
