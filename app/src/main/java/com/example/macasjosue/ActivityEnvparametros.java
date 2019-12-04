package com.example.macasjosue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityEnvparametros extends AppCompatActivity implements View.OnClickListener {

    EditText cajanombre, cajaApellido;
    Button botonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envparametros);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajanombre = findViewById(R.id.txtNombreEnviar);
        cajaApellido = findViewById(R.id.txtApellidoEnviar);
        botonEnviar = findViewById(R.id.btnEnviarP);
        botonEnviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ActivityEnvparametros.this, Activity_Recibirparametros.class);
        intent.putExtra("nombre",cajanombre.getText()+"");
        intent.putExtra("apellido",cajaApellido.getText()+"");
        startActivity(intent);

    }
}
