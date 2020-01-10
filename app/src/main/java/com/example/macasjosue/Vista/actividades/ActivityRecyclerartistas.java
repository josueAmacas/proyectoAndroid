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
        artista1.setNombres("Byron Bladimir");
        artista1.setApellidos("Caicedo Miranda");
        artista1.setNombreArtistico("Byron Caicedo");
        artista1.setFechaNacimiento("26-11-1958");
        artista1.setFoto(R.drawable.byroncaicedo);

        Artista artista2 = new Artista();
        artista2.setNombres("Angel");
        artista2.setApellidos("Guaraca");
        artista2.setNombreArtistico("Angel Guaraca");
        artista2.setFechaNacimiento("11-02-1975");
        artista2.setFoto(R.drawable.angelguaraca);

        Artista artista3 = new Artista();
        artista3.setNombres("Angel Medardo");
        artista3.setApellidos("Luzuriaga Gonzales");
        artista3.setNombreArtistico("Don Medardo");
        artista3.setFechaNacimiento("16-09-1937");
        artista3.setFoto(R.drawable.donmedardo);

        Artista artista4 = new Artista();
        artista4.setNombres("Enma Dolores");
        artista4.setApellidos("Echeverria Recuenco");
        artista4.setNombreArtistico("Lolita Echeverria");
        artista4.setFechaNacimiento("23-12-1972");
        artista4.setFoto(R.drawable.lolitaecheverria);

        listaArtista = new ArrayList<Artista>();
        listaArtista.add(artista1);
        listaArtista.add(artista2);
        listaArtista.add(artista3);
        listaArtista.add(artista4);

        adapter = new ArtistaAdapter(listaArtista);
        recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewArtistas.setAdapter(adapter);

    }

}
