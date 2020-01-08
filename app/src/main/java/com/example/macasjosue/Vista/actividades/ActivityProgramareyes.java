package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.ReyesAdapter;
import com.example.macasjosue.modelo.Reyes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ActivityProgramareyes extends AppCompatActivity {

    TextView datos;
    RecyclerView recyclerReyes;
    ReyesAdapter adapter;
    List<Reyes> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programareyes);
        cargarComponentes();
        obtenerReyesArchivo();
    }

    private void cargarComponentes(){
        datos = findViewById(R.id.lblReyes);
        recyclerReyes = findViewById(R.id.recyclerReyes);
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

            Toast.makeText(this,"numero de reyes es: " + listagodo.getLength(),Toast.LENGTH_SHORT).show();
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
            recyclerReyes.setLayoutManager(new LinearLayoutManager(this));
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
}
