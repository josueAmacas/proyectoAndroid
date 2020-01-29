package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.CarroAdapter;
import com.example.macasjosue.modelo.Carro;

import java.util.ArrayList;
import java.util.List;

public class ActivityCarroORM extends AppCompatActivity implements View.OnClickListener {

    Button botonAgregar, botonListar, botonEliminar, botonModificar,botonEliminarPlaca,botonListarPlaca;
    EditText cajaPlaca, cajaModelo, cajaAnio, cajaMarca;
    RecyclerView recyclerCarro;
    CarroAdapter adapter;
    List<Carro> listaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_orm);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajaPlaca = findViewById(R.id.txtPlaca);
        cajaModelo= findViewById(R.id.txtModeloCarro);
        cajaAnio= findViewById(R.id.txtAnio);
        cajaMarca= findViewById(R.id.txtMarca);
        recyclerCarro = findViewById(R.id.recyclerCarro);

        botonAgregar = findViewById(R.id.btnAgregarCarro);
        botonListar = findViewById(R.id.btnListarTodosCarros);
        botonEliminar = findViewById(R.id.btnEliminarTodoCarro);
        botonModificar= findViewById(R.id.btnModificarCarro);
        botonEliminarPlaca= findViewById(R.id.btnEliminarPlaca);
        botonListarPlaca= findViewById(R.id.btnBuscarPlaca);

        botonAgregar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonEliminarPlaca.setOnClickListener(this);
        botonListarPlaca.setOnClickListener(this);

    }

    private void cargarDatos(View v){
        String placa = listaC.get(recyclerCarro.getChildAdapterPosition(v)).getPlaca();
        String marca = listaC.get(recyclerCarro.getChildAdapterPosition(v)).getMarca();
        String modelo = listaC.get(recyclerCarro.getChildAdapterPosition(v)).getModelo();
        int anio = listaC.get(recyclerCarro.getChildAdapterPosition(v)).getAnio();
        cajaPlaca.setText(placa+"");
        cajaMarca.setText(marca+"");
        cajaModelo.setText(modelo+"");
        cajaAnio.setText(anio+"");
    }

    private void vaciarTexto(){
        cajaPlaca.setText("");
        cajaMarca.setText("");
        cajaModelo.setText("");
        cajaAnio.setText("");
    }

    private void cargarRecycler(List<Carro> lista){
        adapter = new CarroAdapter(lista);
        recyclerCarro.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos(v);
            }
        });
        recyclerCarro.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregarCarro:
                String c1 = cajaPlaca.getText().toString();
                String c2 =cajaMarca.getText().toString();
                String c3 = cajaModelo.getText().toString();
                String c4 = cajaAnio.getText().toString();
                if(c1.length() == 0 || c2.length() == 0 || c3.length() == 0 || c4.length() == 0){
                    Toast.makeText(this, "Por favor llene todos los campos!!",  Toast.LENGTH_SHORT).show();
                }else{
                    Carro carro = new Carro();
                    carro.setPlaca(c1);
                    carro.setMarca(c2);
                    carro.setModelo(c3);
                    carro.setAnio(Integer.parseInt(c4));
                    carro.save();
                    vaciarTexto();
                    Toast.makeText(this, "Carro Agregado con exito!!",  Toast.LENGTH_SHORT).show();
                    listaC = Carro.getAllCars();
                    cargarRecycler(listaC);
                }
                break;
            case R.id.btnModificarCarro:
                String placaCar = cajaPlaca.getText().toString();
                Carro carro2 = Carro.getCarByPlate(placaCar);
                if(carro2 == null){
                    Toast.makeText(this, "Error!! No se puede modificar la placa de un carro!!",  Toast.LENGTH_SHORT).show();
                } else {
                    carro2.setPlaca(cajaPlaca.getText().toString());
                    carro2.setMarca(cajaMarca.getText().toString());
                    carro2.setModelo(cajaModelo.getText().toString());
                    carro2.setAnio(Integer.parseInt(cajaAnio.getText().toString()));
                    carro2.save();
                    Toast.makeText(this, "Carro modificado con exito!!",  Toast.LENGTH_SHORT).show();
                }
            case R.id.btnListarTodosCarros:
                listaC = Carro.getAllCars();
                cargarRecycler(listaC);
                vaciarTexto();
                break;
            case R.id.btnBuscarPlaca:
                String placaC = cajaPlaca.getText().toString();
                if(placaC.length() == 0){
                    Toast.makeText(this, "Por favor ingrese placa para empezar la busqueda!!",  Toast.LENGTH_SHORT).show();
                }else{
                    List<Carro> listCarro = new ArrayList<>();
                    Carro c = Carro.getCarByPlate(placaC);
                    listCarro.add(c);
                    cargarRecycler(listCarro);
                }
                break;
            case R.id.btnEliminarTodoCarro:
                Carro.deleteAllCars();
                vaciarTexto();
                listaC = Carro.getAllCars();
                cargarRecycler(listaC);
                Toast.makeText(this, "Se han eliminado todos los registros!!",  Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEliminarPlaca:
                String placaCarro = cajaPlaca.getText().toString();
                if(placaCarro.length() == 0){
                    Toast.makeText(this, "Por favor ingrese placa para poder eliminar!!",  Toast.LENGTH_SHORT).show();
                }else{
                    Carro.deleteByPlate(placaCarro);
                    vaciarTexto();
                    listaC = Carro.getAllCars();
                    cargarRecycler(listaC);
                    Toast.makeText(this, "Carro Eliminado con exito!!",  Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
