package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macasjosue.R;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener{

    EditText cajaUsuario, cajaPassword;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajaUsuario = findViewById(R.id.txtlogin);
        cajaPassword = findViewById(R.id.txtPassword);
        boton = findViewById(R.id.btnLogin);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String user = cajaUsuario.getText().toString();
        String pass =cajaPassword.getText().toString();
        if(user.length() == 0 || pass.length() == 0){
            Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Usuario: " + user + "    Clave: " + pass, Toast.LENGTH_SHORT).show();
        }

    }
}
