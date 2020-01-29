package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macasjosue.R;

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
        String nombre = cajanombre.getText().toString();
        String apellido = cajaApellido.getText().toString();
        if(nombre.length() == 0 || apellido.length() == 0){
            Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
        }else {
            Bundle bundle =  new Bundle();
            Intent intent = new Intent(ActivityEnvparametros.this, Activity_Recibirparametros.class);
            //intent.putExtra("nombre",cajanombre.getText()+"");
            //intent.putExtra("apellido",cajaApellido.getText()+"");
            bundle.putString("nombre",nombre);
            bundle.putString("apellido",apellido);
            intent.putExtras(bundle);
            startActivity(intent);
            cajanombre.setText("");
            cajaApellido.setText("");
        }


    }
}
