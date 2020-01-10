package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.ArtistaAdapter;
import com.example.macasjosue.modelo.Artista;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ActivityMemoriainterna extends AppCompatActivity implements View.OnClickListener {

    Button botonGuardar, botonBuscarTodos;
    EditText cajaNombres, cajaApellidos,cajaNombreArtistico, cajaFechaNacimiento;
    TextView datos;
    RecyclerView recyclerViewMI;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;
    String lineas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoriainterna);
        tomarControl();
    }

    private void tomarControl(){
        botonGuardar = findViewById(R.id.btnGuardarMI);
        botonBuscarTodos = findViewById(R.id.btnBuscartodosMI);
        cajaNombres = findViewById(R.id.txtNombresMI);
        cajaApellidos = findViewById(R.id.txtApellidosMI);
        cajaNombreArtistico= findViewById(R.id.txtNombreArtisticoMI);
        cajaFechaNacimiento = findViewById(R.id.txtFNacimiento);
        datos =findViewById(R.id.lblDatosMI);
        botonGuardar.setOnClickListener(this);
        botonBuscarTodos.setOnClickListener(this);
        recyclerViewMI = findViewById(R.id.recyclerMI);
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
            artista.setFechaNacimiento(lista[3]);
            listaArtista.add(artista);
        }

        adapter = new ArtistaAdapter(listaArtista);
        recyclerViewMI.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMI.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.btnGuardarMI:
               try{
                   OutputStreamWriter escribir = new OutputStreamWriter(openFileOutput("Archivoartistas.txt", Context.MODE_APPEND));
                   escribir.write(cajaNombres.getText().toString()+","+ cajaApellidos.getText().toString() + ","+cajaNombreArtistico.getText().toString() +","+cajaFechaNacimiento.getText().toString()+";");
                   escribir.close();
                   cajaNombres.setText("");
                   cajaApellidos.setText("");
                   cajaNombreArtistico.setText("");
                   cajaFechaNacimiento.setText("");

               } catch (Exception e){
                   Log.e("archivoMI","Ha ocurrido un error" + e.getMessage());
               }
               break;
           case R.id.btnBuscartodosMI:
               try {
                   BufferedReader lector = new BufferedReader(new InputStreamReader(openFileInput("Archivoartistas.txt")));
                   lineas = lector.readLine();
                   datos.setText(lineas);
                   lector.close();
                   cargarRecycler();
               }catch (Exception e){
                   Log.e("archivoMI","Ha ocurrido un error" + e.getMessage());
               }
               break;
       }
    }

}
