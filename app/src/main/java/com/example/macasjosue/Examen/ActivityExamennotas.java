package com.example.macasjosue.Examen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityExamennotas extends AppCompatActivity implements View.OnClickListener {

    EditText cajanombres, cajamateria, cajaN1, cajaN2, cajaN3;
    TextView cajapromedio;
    Button botonGuardar;
    EstudiantesAdapter adapter;
    RecyclerView recycler;
    List<ModeloEstudiante> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examennotas);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajanombres = findViewById(R.id.txtNombres);
        cajamateria= findViewById(R.id.txtMateria);
        cajaN1 = findViewById(R.id.txtNota1);
        cajaN2 = findViewById(R.id.txtNota2);
        cajaN3 = findViewById(R.id.txtNota3);
        cajapromedio = findViewById(R.id.lblPromedio);
        botonGuardar = findViewById(R.id.btnGuardar);
        botonGuardar.setOnClickListener(this);
        recycler = findViewById(R.id.recyclerEstudiantes);
        lista = new ArrayList<ModeloEstudiante>();

    }

    @Override
    public void onClick(View v) {
        String nombre = cajanombres.getText().toString();
        String materia = cajamateria.getText().toString();
        int n1 = Integer.parseInt(cajaN1.getText().toString());
        int n2 = Integer.parseInt(cajaN2.getText().toString());
        int n3 = Integer.parseInt(cajaN3.getText().toString());
        int prom = (n1+n2+n3) /3 ;
        String apr = "";
        if(prom > 6){
            apr = "Estudiante Aprobado";
            cajapromedio.setText(apr);
        }else {
            apr = "Estudiante Reprobado";
            cajapromedio.setText(apr);
        }


        ModeloEstudiante estudiante = new ModeloEstudiante();
        estudiante.setNombres(nombre);
        estudiante.setMateria(materia);
        estudiante.setNota1(n1);
        estudiante.setNota2(n2);
        estudiante.setNota3(n3);
        estudiante.setPromedio(apr);
        lista.add(estudiante);

       cargarRecicler();

    }

    private void cargarRecicler(){

        ModeloEstudiante e = new ModeloEstudiante("Josue","fisica",8,5,10,"EstudianteAprobado");
        ModeloEstudiante e1 = new ModeloEstudiante("Andres","matematicas",10,6,5,"Estudiante Aprobado");
        ModeloEstudiante e2 = new ModeloEstudiante("Sofia","calculo",9,8,6,"Estudiante Aprobado");
        ModeloEstudiante e3 = new ModeloEstudiante("Camila","programacion",5,8,3,"Estudiante Reprobado");
        lista.add(e);
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);
        adapter = new EstudiantesAdapter(lista);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }
}
