package com.example.macasjosue.Vista.fragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.macasjosue.R;

public class fragmento extends AppCompatActivity implements View.OnClickListener, FrgUno.OnFragmentInteractionListener, frgDos.OnFragmentInteractionListener {

    Button botonFrg1,botonFrg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmento);
        cargarComponentes();
    }

    private void cargarComponentes(){
        botonFrg1 = findViewById(R.id.btnFrg1);
        botonFrg2 = findViewById(R.id.btnFrg2);
        botonFrg1.setOnClickListener(this);
        botonFrg2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFrg1:
                FrgUno fragmento1 = new FrgUno();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedorFragmentos, fragmento1);
                transaction1.commit();
                break;
            case R.id.btnFrg2:
                frgDos fragmento2 = new frgDos();
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.contenedorFragmentos, fragmento2);
                transaction2.commit();

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
