package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.AlumnoAdapter;
import com.example.macasjosue.controlador.SWAlumnoVolly;
import com.example.macasjosue.modelo.Alumno;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivitySWAlumnovolly extends AppCompatActivity implements View.OnClickListener {

    Button botonAgregar,botonModificar,botonListarTodos,botonEliminar,botonBuscar,botonEliminarTodos;
    EditText cajaId, cajaNombre, cajaDireccion;
    AlumnoAdapter adapter;
    RecyclerView recyclerAlumnoV;
    List<Alumno> listaAlumnosV;
    TextView cajaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swalumnovolly);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajaId = findViewById(R.id.txtIdSWVolly);
        cajaNombre= findViewById(R.id.txtNombreSWVolly);
        cajaDireccion= findViewById(R.id.txtDireccionSWVolly);
        recyclerAlumnoV = findViewById(R.id.recyclerAlumnosVolly);
        cajaDatos = findViewById(R.id.lblDatosVolly);

        botonAgregar = findViewById(R.id.btnAgregarAlumnoVolly);
        botonEliminar = findViewById(R.id.btnEliminarIdAlumnoVolly);
        botonListarTodos = findViewById(R.id.btnListarTodosAlumnosVolly);
        botonModificar= findViewById(R.id.btnModificarAlumnoVolly);
        botonBuscar = findViewById(R.id.btnBuscarIdAlumnoVolly);
        botonEliminarTodos = findViewById(R.id.btnEliminarTodoalumnoVolly);

        botonModificar.setOnClickListener(this);
        botonAgregar.setOnClickListener(this);
        botonListarTodos.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        botonListarTodos.setOnClickListener(this);
    }

    private void cargarDatos(View v){
        int id = listaAlumnosV.get(recyclerAlumnoV.getChildAdapterPosition(v)).getIdalumno();
        String nombre= listaAlumnosV.get(recyclerAlumnoV.getChildAdapterPosition(v)).getNombrealumno();
        String direccion = listaAlumnosV.get(recyclerAlumnoV.getChildAdapterPosition(v)).getDireccionalumno();
        cajaId.setText(id+"");
        cajaNombre.setText(nombre);
        cajaDireccion.setText(direccion);
    }

    public void cargarLista(List<Alumno> lista){
        listaAlumnosV = new ArrayList<>();
        listaAlumnosV = lista;
        recyclerAlumnoV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlumnoAdapter(listaAlumnosV);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos(v);
            }
        });
        recyclerAlumnoV.setAdapter(adapter);
    }

    public void vaciarDatos(){
        cajaId.setText("");
        cajaNombre.setText("");
        cajaDireccion.setText("");
    }

    @Override
    public void onClick(View v) {
        SWAlumnoVolly sw = new SWAlumnoVolly(this);
        switch (v.getId()) {
            case R.id.btnListarTodosAlumnosVolly:
                cargarLista(sw.findAllStudent());
                break;
            case R.id.btnAgregarAlumnoVolly:
                String name = cajaNombre.getText().toString();
                String direction = cajaDireccion.getText().toString();
                if(name.length() == 0 || direction.length() == 0){
                    Toast.makeText(this,"Por favor llene todos los campos",Toast.LENGTH_SHORT).show();
                }else{
                    Alumno a = new Alumno();
                    a.setNombrealumno(name);
                    a.setDireccionalumno(direction);
                    sw.insertStudent(a);
                    vaciarDatos();
                    Toast.makeText(this,"Alumno registrado con exito",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBuscarIdAlumnoVolly:
               String idalumno = cajaId.getText().toString();
               if (idalumno.length() == 0){
                   Toast.makeText(this,"Por favor llene el campos ID para Buscar Alumno",Toast.LENGTH_SHORT).show();
               }else {
                   cargarLista(sw.findByID(idalumno));
               }
                break;
            case R.id.btnModificarAlumnoVolly:
                String idal = cajaId.getText().toString();
                String nombre = cajaNombre.getText().toString();
                String direccion= cajaDireccion.getText().toString();
                if(idal.length() == 0 || nombre.length() == 0 || direccion.length() == 0){
                    Toast.makeText(this,"Por favor llene todos los campos",Toast.LENGTH_SHORT).show();
                }else {
                    Alumno alm = new Alumno();
                    alm.setIdalumno(Integer.parseInt(idal));
                    alm.setNombrealumno(nombre);
                    alm.setDireccionalumno(direccion);
                    sw.updateStudents(alm);
                    vaciarDatos();
                    Toast.makeText(this,"Datos del Alumno modificados correctamente",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarIdAlumnoVolly:
                String idE = cajaId.getText().toString();
                if (idE.length() == 0){
                    Toast.makeText(this,"Por favor llene el campos ID para Eliminar Alumno",Toast.LENGTH_SHORT).show();
                } else{
                    Alumno a2 = new Alumno();
                    a2.setIdalumno(Integer.parseInt(idE));
                    sw.Delte(a2);
                    vaciarDatos();
                    Toast.makeText(this,"Alumno eliminado correctamente",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
