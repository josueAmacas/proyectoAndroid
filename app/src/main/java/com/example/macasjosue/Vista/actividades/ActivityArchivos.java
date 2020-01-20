package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.fragmentos.frgArchivossd;
import com.example.macasjosue.Vista.fragmentos.frgArtistas;
import com.example.macasjosue.Vista.fragmentos.frgMemoriainterna;
import com.example.macasjosue.Vista.fragmentos.frgMemoriaprograma;
import com.example.macasjosue.Vista.fragmentos.frgReyes;

public class ActivityArchivos extends AppCompatActivity implements frgMemoriainterna.OnFragmentInteractionListener, frgMemoriaprograma.OnFragmentInteractionListener,
        frgArchivossd.OnFragmentInteractionListener, frgArtistas.OnFragmentInteractionListener, frgReyes.OnFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivos);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment miFragment = null;
        boolean frgSelected = false;
        FragmentTransaction transaction1 = null;
        switch (item.getItemId()){
            case R.id.opMInterna:
                miFragment = new frgMemoriainterna();
                frgSelected = true;
                transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedorFrgArchivo, miFragment);
                transaction1.commit();
                break;
            case R.id.opPrograma:
                miFragment = new frgMemoriaprograma();
                frgSelected = true;
                transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedorFrgArchivo, miFragment);
                transaction1.commit();
                break;
            case R.id.opArchivosSD:
                miFragment = new frgArchivossd();
                frgSelected = true;
                transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedorFrgArchivo, miFragment);
                transaction1.commit();
                break;
            case R.id.opArtistas:
                 miFragment = new frgArtistas();
                frgSelected = true;
                transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedorFrgArchivo, miFragment);
                transaction1.commit();
                break;
            case R.id.opArchivoReyes:
                miFragment = new frgReyes();
                frgSelected = true;
                transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedorFrgArchivo, miFragment);
                transaction1.commit();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
