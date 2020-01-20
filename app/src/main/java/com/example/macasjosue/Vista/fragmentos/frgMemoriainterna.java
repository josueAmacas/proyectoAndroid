package com.example.macasjosue.Vista.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frgMemoriainterna.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frgMemoriainterna#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgMemoriainterna extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frgMemoriainterna() {
        // Required empty public constructor
    }

    Button botonGuardar, botonBuscarTodos;
    EditText cajaNombres, cajaApellidos,cajaNombreArtistico, cajaFechaNacimiento;
    TextView datos;
    RecyclerView recyclerViewMI;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;
    String lineas;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgMemoriainterna.
     */
    // TODO: Rename and change types and number of parameters
    public static frgMemoriainterna newInstance(String param1, String param2) {
        frgMemoriainterna fragment = new frgMemoriainterna();
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
        View vista = inflater.inflate(R.layout.fragment_frg_memoriainterna, container, false);
        botonGuardar = vista.findViewById(R.id.btnGuardarMI);
        botonBuscarTodos = vista.findViewById(R.id.btnBuscartodosMI);
        cajaNombres = vista.findViewById(R.id.txtNombresMI);
        cajaApellidos = vista.findViewById(R.id.txtApellidosMI);
        cajaNombreArtistico= vista.findViewById(R.id.txtNombreArtisticoMI);
        cajaFechaNacimiento = vista.findViewById(R.id.txtFNacimiento);
        datos =vista.findViewById(R.id.lblDatosMI);
        botonGuardar.setOnClickListener(this);
        botonBuscarTodos.setOnClickListener(this);
        recyclerViewMI = vista.findViewById(R.id.recyclerMI);
        // Inflate the layout for this fragment
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
        recyclerViewMI.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMI.setAdapter(adapter);

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
            case R.id.btnGuardarMI:
                try{
                    OutputStreamWriter escribir = new OutputStreamWriter(getActivity().openFileOutput("Archivoartistas.txt", Context.MODE_APPEND));
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
                    BufferedReader lector = new BufferedReader(new InputStreamReader(getActivity().openFileInput("Archivoartistas.txt")));
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
