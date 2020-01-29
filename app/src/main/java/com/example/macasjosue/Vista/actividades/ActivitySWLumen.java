package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.AlumnoAdapter;
import com.example.macasjosue.modelo.Alumno;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivitySWLumen extends AppCompatActivity implements View.OnClickListener {

    Button botonAgregar,botonModificar,botonListarTodos,botonEliminar,botonBuscar,botonEliminarTodos;
    EditText cajaId, cajaNombre, cajaDireccion;
    AlumnoAdapter adapter;
    RecyclerView recyclerAlumno;
    List<Alumno> listaAlumnos;

    //definimos url del servicio web
    String host = "http://reneguaman.000webhostapp.com";
    String insert = "/insertar_allumno.php";
    String get = "/obtener_alumnos.php";
    String getById = "/obtener_alumno_por_id.php";
    String Update = "/actualizar_alumno.php";
    String Delete = "/borrar_alumno.php";

    ServicioWeb sw ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swlumen);
        cargarComponentes();
    }

    //acceder al servicio web mediante Hilo
    class ServicioWeb extends AsyncTask<String, Void,String>{
        String consulta ="";

        @Override
        protected String doInBackground(String... parametros) {

            URL url = null;
            String ruta = parametros[0]; //esta es la ruta..... http://reneguaman..../obtener_alumnos.php
            if(parametros[1].equals("1")){
                try {
                    url = new URL(ruta);
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    int codigoRespuesta = conexion.getResponseCode();
                    if (codigoRespuesta == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(conexion.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader(in));
                        consulta += lector.readLine();
                        lector.close();
                        Toast.makeText(ActivitySWLumen.this,consulta+"",Toast.LENGTH_SHORT).show();
                        Log.e("mensaje",consulta);
                    }else {
                        consulta += "no hubo conexion";
                        Toast.makeText(ActivitySWLumen.this,"No hay coneccion",Toast.LENGTH_SHORT).show();
                        Log.e("mensaje","no hay coneccion");
                    }
                }catch (Exception ex){
                    Log.e("mensaje","no hubo coneccion");
                }
            }
            return consulta;
        }

        @Override
        protected void onPostExecute(String s) {
            cargarRecycler(consulta);
        }
    }

    private void cargarRecycler(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray alumnos = jsonObject.getJSONArray("alumnos");
            listaAlumnos = new ArrayList<>();
            for (int i = 0; i< alumnos.length() ; i++){
                JSONObject a = alumnos.getJSONObject(i);
                Alumno alum = new Alumno();
                alum.setIdalumno(a.getString("idalumno"));
                alum.setNombrealumno(a.getString("nombre"));
                alum.setDireccionalumno(a.getString("direccion"));
                listaAlumnos.add(alum);
            }
            adapter = new AlumnoAdapter(listaAlumnos);
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargarDatos(v);
                }
            });
            recyclerAlumno.setLayoutManager(new LinearLayoutManager(this));
            recyclerAlumno.setAdapter(adapter);

        }catch (Exception ex){
            Toast.makeText(this,"Hubo error cargar Recycler",Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarComponentes(){
        cajaId = findViewById(R.id.txtIdSWHilo);
        cajaNombre= findViewById(R.id.txtNombreSWHilo);
        cajaDireccion= findViewById(R.id.txtDireccionSWHilo);
        recyclerAlumno = findViewById(R.id.recyclerAlumnos);

        botonAgregar = findViewById(R.id.btnAgregarAlumno);
        botonEliminar = findViewById(R.id.btnEliminarIdAlumno);
        botonListarTodos = findViewById(R.id.btnListarTodosAlumnos);
        botonModificar= findViewById(R.id.btnModificarAlumno);
        botonBuscar = findViewById(R.id.btnBuscarIdAlumno);
        botonEliminarTodos = findViewById(R.id.btnEliminarTodoalumno);

        botonModificar.setOnClickListener(this);
        botonAgregar.setOnClickListener(this);
        botonListarTodos.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        botonListarTodos.setOnClickListener(this);
    }

    private void cargarDatos(View v){
        String id = listaAlumnos.get(recyclerAlumno.getChildAdapterPosition(v)).getIdalumno();
        String nombre= listaAlumnos.get(recyclerAlumno.getChildAdapterPosition(v)).getNombrealumno();
        String direccion = listaAlumnos.get(recyclerAlumno.getChildAdapterPosition(v)).getDireccionalumno();
        cajaId.setText(id+"");
        cajaNombre.setText(nombre+"");
        cajaDireccion.setText(direccion+"");
    }

    @Override
    public void onClick(View v) {
        sw = new ServicioWeb();
        switch (v.getId()){
            case R.id.btnAgregarAlumno:
                Toast.makeText(ActivitySWLumen.this,"Agregar",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnListarTodosAlumnos:
                sw.execute(host.concat(get),"1");
                break;
        }
    }
}
