package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.macasjosue.R;

public class ActivitySuma extends AppCompatActivity implements View.OnClickListener {
    EditText entrada1, entrada2, salida;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);
        cargarComponentes();
    }

    private void cargarComponentes(){
        entrada1 = findViewById(R.id.txtEntrada1);
        entrada2 = findViewById(R.id.txtEntrada2);
        salida = findViewById(R.id.txtSalida);
        boton = findViewById(R.id.btnCalcular);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int valor1 = Integer.parseInt(entrada1.getText().toString());
        int valor2 = Integer.parseInt(entrada2.getText().toString());
        int suma = valor1+valor2;
        salida.setText(suma+"");
    }
}
