package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.ArtistaAdapter;
import com.example.macasjosue.modelo.Artista;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ActivityMemoriaprograma extends AppCompatActivity implements View.OnClickListener {

    Button botonLeer;
    TextView cajaverArchivo;
    RecyclerView recyclerViewArtistas;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;
    InputStream input;
    BufferedReader lector;
    String cadena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoriaprograma);
        tomarControl();
    }

    private void tomarControl(){
        recyclerViewArtistas = findViewById(R.id.recyclerLeerArchivo);
        botonLeer = findViewById(R.id.btnLeer);
        cajaverArchivo = findViewById(R.id.lblLeer);
        botonLeer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try{
            input = getResources().openRawResource(R.raw.archivoraw);
            lector = new BufferedReader(new InputStreamReader(input));
            cadena = lector.readLine();
            //cajaverArchivo.setText(cadena);
            cargarRecycler();
        } catch (Exception e){
            Log.e("archivoraw.txt","Ha ocurrido un error" + e.getMessage());
        }
    }

    private void cargarRecycler(){
        listaArtista = new ArrayList<Artista>();
        String[] registro = cadena.split(";");
        String[] lista;

        for (int i=0 ; i<registro.length; i++){
            Artista artista = new Artista();
            lista = registro[i].split(",");
            artista.setNombres(lista[0]);
            artista.setApellidos(lista[1]);
            artista.setNombreArtistico(lista[2]);
            listaArtista.add(artista);
        }

        adapter = new ArtistaAdapter(listaArtista);
        recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewArtistas.setAdapter(adapter);

    }
}
