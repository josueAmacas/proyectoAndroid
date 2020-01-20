package com.example.macasjosue;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.macasjosue.Examen.ActivityExamennotas;
import com.example.macasjosue.Vista.actividades.ActivityArchivos;
import com.example.macasjosue.Vista.actividades.ActivityEnvparametros;
import com.example.macasjosue.Vista.actividades.ActivityFragment2;
import com.example.macasjosue.Vista.actividades.ActivityLogin;
import com.example.macasjosue.Vista.actividades.ActivityProductohelper;
import com.example.macasjosue.Vista.actividades.ActivitySueldos;
import com.example.macasjosue.Vista.actividades.ActivitySuma;
import com.example.macasjosue.Vista.fragmentos.fragmento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityNavigationdrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationdrawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_navigationdrawer, menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Este metodo permite realizar eventos en cada item hijo de los menu
        Intent intent;
        switch (item.getItemId()){
            case R.id.opcionLogin:
                intent = new Intent(this, ActivityLogin.class);
                startActivity(intent);
                break;
            case R.id.opcionSumar:
                intent = new Intent(this, ActivitySuma.class);
                startActivity(intent);
                break;
            case R.id.opcionParametros:
                intent = new Intent(this, ActivityEnvparametros.class);
                startActivity(intent);
                break;
            case R.id.opcionColores:
                intent = new Intent(this, fragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionEscucha:
                intent = new Intent(this, ActivityFragment2.class);
                startActivity(intent);
                break;
            case R.id.opcionSumarDlg:
                final Dialog dlgSumar = new Dialog(this);
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
                        Toast.makeText(ActivityNavigationdrawer.this, "La suma es:  "+ suma,Toast.LENGTH_SHORT).show();
                        dlgSumar.hide();
                    }
                });

                dlgSumar.show();
                break;
            /*case R.id.opcionArtistas:
                intent = new Intent(this, ActivityRecyclerartistas.class);
                startActivity(intent);
                break;
            case R.id.opcionInterno:
                intent = new Intent(this, ActivityMemoriainterna.class);
                startActivity(intent);
                break;
            case R.id.opcionPrograma:
                intent = new Intent(this, ActivityMemoriaprograma.class);
                startActivity(intent);
                break;
            case R.id.opcionArchivoReyes:
                intent = new Intent(this, ActivityProgramareyes.class);
                startActivity(intent);
                break;
            case R.id.opcionArchivosSD:
                intent = new Intent(this, ActivityArchivomemoriasd.class);
                startActivity(intent);
                break;*/
            case R.id.opcionArchivos:
                intent = new Intent(this, ActivityArchivos.class);
                startActivity(intent);
                break;
            case R.id.opcionHelper:
                intent = new Intent(this, ActivityProductohelper.class);
                startActivity(intent);
                break;
            case R.id.opcionSueldos:
                intent = new Intent(this, ActivitySueldos.class);
                startActivity(intent);
                break;
            case R.id.opcionExamen:
                intent = new Intent(this, ActivityExamennotas.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id == R.id.nav_home){

        }else if(id == R.id.nav_gallery){

        }else if(id == R.id.nav_slideshow){

        }else if(id == R.id.nav_tools){

        }else if(id == R.id.nav_share){

        }else if(id == R.id.nav_send){

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
