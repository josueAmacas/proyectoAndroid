package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.AdapterProductos;
import com.example.macasjosue.controlador.HelperProductos;
import com.example.macasjosue.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ActivityProductohelper extends AppCompatActivity implements View.OnClickListener {

    EditText codigo,descripcion,precio,cantidad;
    Button botonAgregar, botonListar;
    RecyclerView recyclerProductos;
    HelperProductos helperproducto;
    List<Producto> listaProductos;
    AdapterProductos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productohelper);
        cargarComponentes();
    }

    private void cargarComponentes(){
        codigo = findViewById(R.id.txtCodigoP);
        descripcion = findViewById(R.id.txtDescripcionP);
        precio= findViewById(R.id.txtPrecioP);
        cantidad= findViewById(R.id.txtCantidadP);
        botonAgregar = findViewById(R.id.btnAgregarProducto);
        botonListar= findViewById(R.id.btnListarProducto);
        botonAgregar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        helperproducto = new HelperProductos(this, "ProductoDB",null, 1);
        recyclerProductos = findViewById(R.id.recyclerProductos);
    }

    private void cargarRecycler(){
        listaProductos = helperproducto.getAll();
        adapter = new AdapterProductos(listaProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        recyclerProductos.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregarProducto:
                Producto p = new Producto();
                p.setCodigo(Integer.parseInt(codigo.getText().toString()));
                p.setDescripcion(descripcion.getText().toString());
                p.setPrecio(Double.parseDouble(precio.getText().toString()));
                p.setCantidad(Integer.parseInt(cantidad.getText().toString()));
                helperproducto.insertar(p);
                break;
            case R.id.btnListarProducto:
               cargarRecycler();
                break;
        }

    }
}
