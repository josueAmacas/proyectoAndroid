package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macasjosue.R;

public class ActivitySuma extends AppCompatActivity implements View.OnClickListener {
    EditText entrada1, entrada2;
    TextView salida;
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
        String n1 = entrada1.getText().toString();
        String n2 = entrada2.getText().toString();
        if(n1.length() == 0 || n2.length() == 0){
            Toast.makeText(this, "Por favor llene todos los campos!!",  Toast.LENGTH_SHORT).show();
        }else{

            int valor1 = Integer.parseInt(n1);
            int valor2 = Integer.parseInt(n2);
            int suma = valor1+valor2;
            salida.setText(suma+"");
        }
    }
}
