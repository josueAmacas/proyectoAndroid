package com.example.macasjosue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ActivityFragment2 extends AppCompatActivity implements Comunicador, View.OnClickListener, Frgescuchar1.OnFragmentInteractionListener, Frgescuchar2.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void responder(String datos) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Frgescuchar2 frg2 = (Frgescuchar2) fragmentManager.findFragmentById(R.id.Frgescucha2);
        frg2.cambiarTexto(datos);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
