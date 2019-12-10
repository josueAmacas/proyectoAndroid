package com.example.macasjosue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //metodo para cargar menu
        //MenuInflater permite crear un objeto para menejar el archivos xml(main.xml)
        //el metodo inflate permmite agreagr un menu implementado de un archivo xml a la actividad
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Este metodo permite realizar eventos en cada item hijo de los menu
        Intent intent;
        switch (item.getItemId()){
            case R.id.opcionLogin:
                intent = new Intent(MainActivity.this,ActivityLogin.class);
                startActivity(intent);
                break;
            case R.id.opcionSumar:
                intent = new Intent(MainActivity.this,ActivitySuma.class);
                startActivity(intent);
                break;
            case R.id.opcionParametros:
                intent = new Intent(MainActivity.this,ActivityEnvparametros.class);
                startActivity(intent);
                break;
            case R.id.opcionColores:
                intent = new Intent(MainActivity.this,fragmento.class);
                startActivity(intent);
                break;

        }

        return true;
    }
}
