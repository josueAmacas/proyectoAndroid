package com.example.macasjosue.Vista.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frgArchivossd.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frgArchivossd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgArchivossd extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frgArchivossd() {
        // Required empty public constructor
    }

    Button botonEscribir, botonLeer;
    EditText cajaNombres, cajaApellidos,cajaNombreArtistico,cajaFechaNacimiento;
    TextView datos;
    RecyclerView recyclerViewMSD;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;
    String lineas;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgArchivossd.
     */
    // TODO: Rename and change types and number of parameters
    public static frgArchivossd newInstance(String param1, String param2) {
        frgArchivossd fragment = new frgArchivossd();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_frg_archivossd, container, false);
        botonEscribir= vista.findViewById(R.id.btnEscribirMSD);
        botonLeer= vista.findViewById(R.id.btnListartodosMSD);
        cajaNombres = vista.findViewById(R.id.txtNombresMSD);
        cajaApellidos = vista.findViewById(R.id.txtApellidosMSD);
        cajaNombreArtistico= vista.findViewById(R.id.txtNombreArtisticoMSD);
        cajaFechaNacimiento = vista.findViewById(R.id.txtfechaNMSD);
        datos = vista.findViewById(R.id.lblDatosMSD);
        botonEscribir.setOnClickListener(this);
        botonLeer.setOnClickListener(this);
        recyclerViewMSD = vista.findViewById(R.id.recyclerMSD);
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEscribirMSD:
                try{
                    File rta = Environment.getExternalStorageDirectory();//ruta de la SD
                    File file = new File(rta.getAbsoluteFile(), "archivo.txt");
                    /*/File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "archivo.txt");
                    OutputStreamWriter escribir = new OutputStreamWriter(new FileOutputStream(file));
                    escribir.write(cajaNombres.getText().toString()+","+ cajaApellidos.getText().toString() + ","+cajaNombreArtistico.getText().toString() +";");
                    escribir.close();*/

                    FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(cajaNombres.getText().toString()+","+ cajaApellidos.getText().toString() + ","+cajaNombreArtistico.getText().toString() +"," +cajaFechaNacimiento.getText().toString() +";");
                    bw.close();
                    cajaNombres.setText("");
                    cajaApellidos.setText("");
                    cajaNombreArtistico.setText("");
                    cajaFechaNacimiento.setText("");
                } catch (Exception e){
                    Log.e("ERROR SD", e.getMessage());
                }
                break;
            case R.id.btnListartodosMSD:
                try {
                    File rut = Environment.getExternalStorageDirectory();//ruta de la SD
                    File file = new File(rut.getAbsoluteFile(), "archivo.txt");
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
