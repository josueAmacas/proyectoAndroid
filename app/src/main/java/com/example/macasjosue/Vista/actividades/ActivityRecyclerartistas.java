package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.ArtistaAdapter;
import com.example.macasjosue.modelo.Artista;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecyclerartistas extends AppCompatActivity {

    RecyclerView recyclerViewArtistas;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerartistas);
        tomarControl();
        cargarRecycler();
    }

    private void tomarControl(){
        recyclerViewArtistas = findViewById(R.id.recyclerArtistas);
    }

    private void cargarRecycler(){
        Artista artista1 = new Artista();
        artista1.setNombres("Luis");
        artista1.setApellidos("Miguel");
        artista1.setFoto(R.drawable.yosue);

        Artista artista2 = new Artista();
        artista2.setNombres("Don");
        artista2.setApellidos("Medardo");
        artista2.setFoto(R.drawable.yosue);

        Artista artista3 = new Artista();
        artista3.setNombres("Don");
        artista3.setApellidos("Medardo");
        artista3.setFoto(R.drawable.yosue);

        listaArtista = new ArrayList<Artista>();
        listaArtista.add(artista1);
        listaArtista.add(artista2);
        listaArtista.add(artista2);

        adapter = new ArtistaAdapter(listaArtista);
        recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewArtistas.setAdapter(adapter);

    }

}
