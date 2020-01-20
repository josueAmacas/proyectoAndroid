package com.example.macasjosue.Vista.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.ArtistaAdapter;
import com.example.macasjosue.modelo.Artista;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frgArtistas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frgArtistas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgArtistas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frgArtistas() {
        // Required empty public constructor
    }

    RecyclerView recyclerViewArtistas;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgArtistas.
     */
    // TODO: Rename and change types and number of parameters
    public static frgArtistas newInstance(String param1, String param2) {
        frgArtistas fragment = new frgArtistas();
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
        View vista = inflater.inflate(R.layout.fragment_frg_artistas, container, false);
        recyclerViewArtistas = vista.findViewById(R.id.recyclerArtistas);
        cargarRecycler();
        return vista;
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
        recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewArtistas.setAdapter(adapter);

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
