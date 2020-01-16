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
    Button botonAgregar, botonListar, botonModificar, botonEliminarTodo,getBotonEliminarCodigo, botonBuscarCodigo;
    RecyclerView recyclerProductos;
    HelperProductos helperproducto;
    List<Producto> listaProductos;
    AdapterProductos adapter;
    String cod;

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
        botonModificar = findViewById(R.id.btnModificarProducto);
        botonEliminarTodo= findViewById(R.id.btnEliminarTodoProducto);
        getBotonEliminarCodigo= findViewById(R.id.btnEliminarCodigo);
        botonBuscarCodigo= findViewById(R.id.btnListarCodigoProducto);
        botonAgregar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonEliminarTodo.setOnClickListener(this);
        getBotonEliminarCodigo.setOnClickListener(this);
        botonBuscarCodigo.setOnClickListener(this);
        helperproducto = new HelperProductos(this, "ProductoDB",null, 1);
        recyclerProductos = findViewById(R.id.recyclerProductos);
    }

    private void cargarRecycler(){
        listaProductos = helperproducto.getAll();
        adapter = new AdapterProductos(listaProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codig = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getCodigo();
                String desc = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getDescripcion();
                double prec = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getPrecio();
                int cant = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getCantidad();
                codigo.setText(codig+"");
                descripcion.setText(desc+"");
                precio.setText(prec+"");
                cantidad.setText(cant+"");
            }
        });
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
                codigo.setText("");
                descripcion.setText("");
                precio.setText("");
                cantidad.setText("");
                cargarRecycler();
                break;
            case R.id.btnListarProducto:
               cargarRecycler();
                break;
            case R.id.btnModificarProducto:
                Producto p2 = new Producto();
                p2.setCodigo(Integer.parseInt(codigo.getText().toString()));
                p2.setDescripcion(descripcion.getText().toString());
                p2.setPrecio(Double.parseDouble(precio.getText().toString()));
                p2.setCantidad(Integer.parseInt(cantidad.getText().toString()));
                helperproducto.modificar(p2);
                codigo.setText("");
                descripcion.setText("");
                precio.setText("");
                cantidad.setText("");
                cargarRecycler();
                break;
            case R.id.btnEliminarTodoProducto:
                helperproducto.eliminarTodo();
                codigo.setText("");
                descripcion.setText("");
                precio.setText("");
                cantidad.setText("");
                cargarRecycler();
                break;
            case R.id.btnEliminarCodigo:
                cod = codigo.getText().toString();
                Producto prd = new Producto();
                prd.setCodigo(Integer.parseInt(cod));
                helperproducto.eliminarCodigo(prd);
                codigo.setText("");
                descripcion.setText("");
                precio.setText("");
                cantidad.setText("");
                cargarRecycler();
                break;
            case R.id.btnListarCodigoProducto:
                cod = codigo.getText().toString();
                listaProductos = helperproducto.getProductoByCodigo(cod);
                descripcion.setText("");
                precio.setText("");
                cantidad.setText("");
                adapter = new AdapterProductos(listaProductos);
                recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int codig = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getCodigo();
                        String desc = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getDescripcion();
                        double prec = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getPrecio();
                        int cant = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getCantidad();
                        codigo.setText(codig+"");
                        descripcion.setText(desc+"");
                        precio.setText(prec+"");
                        cantidad.setText(cant+"");
                    }
                });
                recyclerProductos.setAdapter(adapter);
                break;
        }

    }
}
