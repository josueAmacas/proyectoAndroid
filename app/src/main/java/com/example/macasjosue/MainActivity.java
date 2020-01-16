package com.example.macasjosue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macasjosue.Vista.actividades.ActivityEnvparametros;
import com.example.macasjosue.Vista.actividades.ActivityFragment2;
import com.example.macasjosue.Vista.actividades.ActivityLogin;
import com.example.macasjosue.Vista.actividades.ActivityProgramareyes;
import com.example.macasjosue.Vista.actividades.ActivityRecyclerartistas;
import com.example.macasjosue.Vista.actividades.ActivitySuma;
import com.example.macasjosue.Vista.fragmentos.fragmento;

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
    public boolean onOptionsItemSelected(MenuItem item) {
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
                intent = new Intent(MainActivity.this, fragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionEscucha:
                intent = new Intent(MainActivity.this, ActivityFragment2.class);
                startActivity(intent);
                break;
            case R.id.opcionSumarDlg:
                final Dialog dlgSumar = new Dialog(MainActivity.this);
                dlgSumar.setContentView(R.layout.dlg_sumar);
                final EditText cajaN1 = dlgSumar.findViewById(R.id.txtN1Dlg);
                final EditText cajaN2 = dlgSumar.findViewById(R.id.txtN2Dlg);
                Button botorSumarDlg = dlgSumar.findViewById(R.id.btnSumarDlg);
                botorSumarDlg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int n1 = Integer.parseInt(cajaN1.getText().toString());
                        int n2 = Integer.parseInt(cajaN2.getText().toString());
                        int suma = n1 + n2;
                        Toast.makeText(MainActivity.this, "La suma es:  "+ suma,Toast.LENGTH_SHORT).show();
                        dlgSumar.hide();
                    }
                });

                dlgSumar.show();
                break;
            case R.id.opcionArtistas:
                intent = new Intent(MainActivity.this, ActivityRecyclerartistas.class);
                startActivity(intent);
                break;
            case R.id.opcionArchivoReyes:
                intent = new Intent(MainActivity.this, ActivityProgramareyes.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
