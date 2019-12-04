package com.example.macasjosue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button botonLogin, botonSumar, botonEnviarParametros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarComponentes();
    }

    private void cargarComponentes(){
        botonLogin = findViewById(R.id.btnLoginP);
        botonSumar = findViewById(R.id.btnSumarP);
        botonEnviarParametros = findViewById(R.id.btnEnviarP);
        botonLogin.setOnClickListener(this);
        botonSumar.setOnClickListener(this);
        botonEnviarParametros.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btnLoginP:
                intent = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(intent);
                break;
            case R.id.btnSumarP:
                intent = new Intent(MainActivity.this, ActivitySuma.class);
                startActivity(intent);
                break;
            case R.id.btnEnviarP:
                intent = new Intent(MainActivity.this, ActivityEnvparametros.class);
                startActivity(intent);
                break;
        }
    }
}
