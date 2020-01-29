package com.example.macasjosue.ui.tools;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.ArtistaAdapter;
import com.example.macasjosue.modelo.Artista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ToolsFragment extends Fragment implements View.OnClickListener {

    Button botonEscribir, botonLeer;
    EditText cajaNombres, cajaApellidos,cajaNombreArtistico,cajaFechaNacimiento;
    RecyclerView recyclerViewMSD;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;
    String lineas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_tools, container, false);
        botonEscribir= vista.findViewById(R.id.btnEscribirMSD2);
        botonLeer= vista.findViewById(R.id.btnListartodosMSD2);
        cajaNombres = vista.findViewById(R.id.txtNombresMSD2);
        cajaApellidos = vista.findViewById(R.id.txtApellidosMSD2);
        cajaNombreArtistico= vista.findViewById(R.id.txtNombreArtisticoMSD2);
        cajaFechaNacimiento = vista.findViewById(R.id.txtfechaNMSD2);
        botonEscribir.setOnClickListener(this);
        botonLeer.setOnClickListener(this);
        recyclerViewMSD = vista.findViewById(R.id.recyclerMSD2);
        return vista;
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
        recyclerViewMSD.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMSD.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEscribirMSD2:
                try{
                    String nom = cajaNombres.getText().toString();
                    String ap = cajaApellidos.getText().toString();
                    String NomA = cajaNombreArtistico.getText().toString();
                    String FecN = cajaFechaNacimiento.getText().toString();
                    if (nom.length() == 0 || ap.length() == 0 || NomA.length() == 0 || FecN.length() == 0){
                        Toast.makeText(getContext(), "Por favor llene todos los campos!!",Toast.LENGTH_SHORT).show();
                    }else {
                        File rta = Environment.getExternalStorageDirectory();//ruta de la SD
                        File file = new File(rta.getAbsoluteFile(), "archivo.txt");
                    /*File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "archivo.txt");
                    OutputStreamWriter escribir = new OutputStreamWriter(new FileOutputStream(file));
                    escribir.write(cajaNombres.getText().toString()+","+ cajaApellidos.getText().toString() + ","+cajaNombreArtistico.getText().toString() +";");
                    escribir.close();*/

                        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(nom+","+ ap+ ","+NomA+"," +FecN+";");
                        bw.close();
                        cajaNombres.setText("");
                        cajaApellidos.setText("");
                        cajaNombreArtistico.setText("");
                        cajaFechaNacimiento.setText("");
                        Toast.makeText(getContext(), "Agregado con exito!!",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Log.e("ERROR SD", e.getMessage());
                }
                break;
            case R.id.btnListartodosMSD2:
                try {
                    File rut = Environment.getExternalStorageDirectory();//ruta de la SD
                    File file = new File(rut.getAbsoluteFile(), "archivo.txt");
                    BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    lineas = lector.readLine();
                    lector.close();
                    cargarRecycler();
                }catch (Exception e){
                    Log.e("ERROR SD", e.getMessage());
                }
                break;
        }
    }
}