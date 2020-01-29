package com.example.macasjosue.Examen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityExamennotas extends AppCompatActivity implements View.OnClickListener {

    EditText cajanombres, cajamateria, cajaN1, cajaN2, cajaN3;
    TextView cajapromedio;
    Button botonGuardar, botonListar;
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
        botonListar = findViewById(R.id.btnListarE);
        botonGuardar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        recycler = findViewById(R.id.recyclerEstudiantes);
        lista = new ArrayList<ModeloEstudiante>();

        ModeloEstudiante e = new ModeloEstudiante("Josue","fisica",8,5,10,"EstudianteAprobado");
        ModeloEstudiante e1 = new ModeloEstudiante("Andres","matematicas",10,6,5,"Estudiante Aprobado");
        ModeloEstudiante e2 = new ModeloEstudiante("Sofia","calculo",9,8,6,"Estudiante Aprobado");
        ModeloEstudiante e3 = new ModeloEstudiante("Camila","programacion",5,8,3,"Estudiante Reprobado");
        lista.add(e);
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGuardar:
                String nombre = cajanombres.getText().toString();
                String materia = cajamateria.getText().toString();
                String nt1 = cajaN1.getText().toString();
                String nt2 = cajaN2.getText().toString();
                String nt3 = cajaN3.getText().toString();
                if(nombre.length() == 0 || materia.length() == 0 || nt1.length() == 0 || nt2.length() == 0 || nt3.length() == 0){
                    Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    int n1 = Integer.parseInt(nt1);
                    int n2 = Integer.parseInt(nt2);
                    int n3 = Integer.parseInt(nt3);
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
                break;
            case  R.id.btnListarE:
                cargarRecicler();
                break;
        }

    }

    private void cargarRecicler(){
        adapter = new EstudiantesAdapter(lista);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }
}
