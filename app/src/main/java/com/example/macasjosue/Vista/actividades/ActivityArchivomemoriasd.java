package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.ArtistaAdapter;
import com.example.macasjosue.modelo.Artista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ActivityArchivomemoriasd extends AppCompatActivity implements View.OnClickListener{

    Button botonEscribir, botonLeer;
    EditText cajaNombres, cajaApellidos,cajaNombreArtistico;
    TextView datos;
    RecyclerView recyclerViewMSD;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;
    String lineas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivomemoriasd);
        tomarControl();
    }

    private void tomarControl(){
        botonEscribir= findViewById(R.id.btnEscribirMSD);
        botonLeer= findViewById(R.id.btnListartodosMSD);
        cajaNombres = findViewById(R.id.txtNombresMSD);
        cajaApellidos = findViewById(R.id.txtApellidosMSD);
        cajaNombreArtistico= findViewById(R.id.txtNombreArtisticoMSD);
        datos =findViewById(R.id.lblDatosMSD);
        botonEscribir.setOnClickListener(this);
        botonLeer.setOnClickListener(this);
        recyclerViewMSD = findViewById(R.id.recyclerMSD);
    }

    private void cargarRecycler(){
        listaArtista = new ArrayList<Artista>();
        String[] registro = lineas.split(";");
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
        recyclerViewMSD.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMSD.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEscribirMSD:
                try{
                    File ruta = Environment.getExternalStorageDirectory();//ruta de la SD
                    File file = new File(ruta.getAbsoluteFile(), "archivo.txt");
                    /*/File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "archivo.txt");
                    OutputStreamWriter escribir = new OutputStreamWriter(new FileOutputStream(file));
                    escribir.write(cajaNombres.getText().toString()+","+ cajaApellidos.getText().toString() + ","+cajaNombreArtistico.getText().toString() +";");
                    escribir.close();*/

                    FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(cajaNombres.getText().toString()+","+ cajaApellidos.getText().toString() + ","+cajaNombreArtistico.getText().toString() +";");
                    bw.close();
                } catch (Exception e){
                    Log.e("ERROR SD", e.getMessage());
                }
                break;
            case R.id.btnListartodosMSD:
                try {
                    File ruta = Environment.getExternalStorageDirectory();//ruta de la SD
                    File file = new File(ruta.getAbsoluteFile(), "archivo.txt");
                    BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    lineas = lector.readLine();
                    datos.setText(lineas);
                    lector.close();
                    cargarRecycler();
                }catch (Exception e){
                    Log.e("ERROR SD", e.getMessage());
                }
                break;
        }
    }
}
