package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.AlumnoAdapter;
import com.example.macasjosue.controlador.SWAlumnosHilo;
import com.example.macasjosue.modelo.Alumno;

import org.json.JSONArray;
import org.json.JSONObject;

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
    String insert = "/insertar_alumno.php";
    String get = "/obtener_alumnos.php";
    String getById = "/obtener_alumno_por_id.php";
    String Update = "/actualizar_alumno.php";
    String Delete = "/borrar_alumno.php";

    SWAlumnosHilo sw ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swlumen);
        cargarComponentes();
    }

    private void cargarByYD(String cadena){
        try {
            JSONObject json = new JSONObject(cadena);
            String estado = json.getString("estado");
            List<Alumno> lista = new ArrayList<>();
            if(estado.equals("1")){
                Alumno alumno = new Alumno();
                alumno.setIdalumno(Integer.parseInt(json.getJSONObject("alumno").getString("idAlumno")));
                alumno.setNombrealumno(json.getJSONObject("alumno").getString("nombre"));
                alumno.setDireccionalumno(json.getJSONObject("alumno").getString("direccion"));
                lista.add(alumno);
                cargarLista(lista);
            }else{
                Toast.makeText(getApplicationContext(),"No existe estudiante", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarLista(List<Alumno> lista){
        listaAlumnos = new ArrayList<>();
        listaAlumnos = lista;
        recyclerAlumno.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlumnoAdapter(listaAlumnos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cargarDatos(v);
            }
        });
        recyclerAlumno.setAdapter(adapter);
    }

    private void cargarRecycler(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray alumnos = jsonObject.getJSONArray("alumnos");
            listaAlumnos = new ArrayList<>();
            for (int i = 0; i< alumnos.length() ; i++){
                JSONObject a = alumnos.getJSONObject(i);
                Alumno alum = new Alumno();
                alum.setIdalumno(Integer.parseInt(a.getString("idalumno")));
                alum.setNombrealumno(a.getString("nombre"));
                alum.setDireccionalumno(a.getString("direccion"));
                listaAlumnos.add(alum);
            }
            cargarLista(listaAlumnos);
        }catch (Exception ex){
            ex.printStackTrace();
            Log.e("Mensaje", "Hubo error cargar Recycler");
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
        int id = listaAlumnos.get(recyclerAlumno.getChildAdapterPosition(v)).getIdalumno();
        String nombre= listaAlumnos.get(recyclerAlumno.getChildAdapterPosition(v)).getNombrealumno();
        String direccion = listaAlumnos.get(recyclerAlumno.getChildAdapterPosition(v)).getDireccionalumno();
        cajaId.setText(id+"");
        cajaNombre.setText(nombre);
        cajaDireccion.setText(direccion);
    }

    public void vaciarCampos(){
        cajaId.setText("");
        cajaNombre.setText("");
        cajaDireccion.setText("");
    }

    @Override
    public void onClick(View v) {
        sw = new SWAlumnosHilo();
        switch (v.getId()){
            case R.id.btnListarTodosAlumnos:
                try {
                    String cadena = sw.execute(host.concat(get), "1").get();
                    cargarRecycler(cadena);
                }catch (Exception ex){
                    Log.e("mesagge","Error x aca");
                }
                break;
            case R.id.btnAgregarAlumno:
                try {
                    String name = cajaNombre.getText().toString();
                    String direction = cajaDireccion.getText().toString();
                    if (name.length() == 0 || direction.length() == 0) {
                        Toast.makeText(this, "Por Favor llene todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        sw.execute(host.concat(insert), "2", name, direction);
                        vaciarCampos();
                        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Log.e("mesagge","Error x aca2");
                }
                break;
            case R.id.btnBuscarIdAlumno:
                try {
                    String cid = cajaId.getText().toString();
                    if (cid.length() == 0) {
                        Toast.makeText(this, "Por Favor llene el campo ID para poder buscar", Toast.LENGTH_SHORT).show();
                    } else {
                        String busq = sw.execute(host.concat(getById) + "?idalumno=" + cid, "3").get();
                        cargarByYD(busq);
                    }
                }catch (Exception ex){
                    Log.e("mesagge","Error x aca3");
                }
                break;
            case R.id.btnEliminarIdAlumno:
                String id = cajaId.getText().toString();
                if(id.length() == 0){
                    Toast.makeText(this,"Por Favor llene el campo ID para poder Eliminar",Toast.LENGTH_SHORT).show();
                } else {
                    sw.execute(host.concat(Delete), "4", id);
                    vaciarCampos();
                    Toast.makeText(this,"Alumno eliminado con exito",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnModificarAlumno:
                sw.execute(host.concat(Update),"5", cajaId.getText().toString(),cajaNombre.getText().toString(),cajaDireccion.getText().toString());
                vaciarCampos();
                Toast.makeText(this,"Alumno Modificado con exito!!",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
