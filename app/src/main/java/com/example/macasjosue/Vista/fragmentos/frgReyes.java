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
import android.widget.TextView;
import android.widget.Toast;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.ReyesAdapter;
import com.example.macasjosue.modelo.Reyes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frgReyes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frgReyes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgReyes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frgReyes() {
        // Required empty public constructor
    }

    TextView datos;
    RecyclerView recyclerReyes;
    ReyesAdapter adapter;
    List<Reyes> lista;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgReyes.
     */
    // TODO: Rename and change types and number of parameters
    public static frgReyes newInstance(String param1, String param2) {
        frgReyes fragment = new frgReyes();
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
        View vista = inflater.inflate(R.layout.fragment_frg_reyes, container, false);
        datos = vista.findViewById(R.id.lblReyes);
        recyclerReyes = vista.findViewById(R.id.recyclerReyes);
        obtenerReyesArchivo();
        return vista;
    }

    private void obtenerReyesArchivo(){
        try{
           /* InputStream input = getResources().openRawResource(R.raw.reyes);
            BufferedReader lector = new BufferedReader(new InputStreamReader(input));
            String informacion = lector.readLine();
            datos.setText(informacion);*/

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(getResources().openRawResource(R.raw.reyes), null);
            NodeList listagodo = doc.getElementsByTagName("godo");

            Toast.makeText(getContext(),"numero de reyes es: " + listagodo.getLength(),Toast.LENGTH_SHORT).show();
            datos.setText("numero de reyes es: " + listagodo.getLength());

            lista = new ArrayList<Reyes>();

            for(int i=0; i < listagodo.getLength(); i++){
                Reyes rey = new Reyes();
                Node node = listagodo.item(i);
                Element element2 = (Element) node;
                rey.setNombre(getValue("nombre", element2));
                rey.setPeriodo(getValue("periodo", element2));
                lista.add(rey);
            }

            adapter = new ReyesAdapter(lista);
            recyclerReyes.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerReyes.setAdapter(adapter);


        }catch (Exception e){
            Log.e("ERROR SD", e.getMessage());
        }
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
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
