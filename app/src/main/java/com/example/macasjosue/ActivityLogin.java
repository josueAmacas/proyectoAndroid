package com.example.macasjosue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Toast.makeText(ActivityLogin.this, "Usuario: " + cajaUsuario.getText() +
                "Clave" + cajaPassword.getText(), Toast.LENGTH_SHORT).show();
    }
}
