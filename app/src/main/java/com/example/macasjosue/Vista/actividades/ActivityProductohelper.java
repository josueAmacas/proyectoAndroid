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

    private void cargarDatos(View v){
        int codig = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getCodigo();
        String desc = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getDescripcion();
        double prec = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getPrecio();
        int cant = listaProductos.get(recyclerProductos.getChildAdapterPosition(v)).getCantidad();
        codigo.setText(codig+"");
        descripcion.setText(desc+"");
        precio.setText(prec+"");
        cantidad.setText(cant+"");
    }

    private void vaciarTexto(){
        codigo.setText("");
        descripcion.setText("");
        precio.setText("");
        cantidad.setText("");
    }

    private void cargarRecycler(List<Producto> listaP){
        adapter = new AdapterProductos(listaP);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos(v);
            }
        });
        recyclerProductos.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregarProducto:
                String codi = codigo.getText().toString();
                String desc = descripcion.getText().toString();
                String prec = precio.getText().toString();
                String cant = cantidad.getText().toString();
                if(codi.length() ==0 || desc.length() == 0 || prec.length() == 0 || cant.length() == 0){
                    Toast.makeText(this, "Debe llenar todos los campos!!",  Toast.LENGTH_SHORT).show();
                }else {
                    Producto p = new Producto();
                    p.setCodigo(Integer.parseInt(codi));
                    p.setDescripcion(desc);
                    p.setPrecio(Double.parseDouble(prec));
                    p.setCantidad(Integer.parseInt(cant));
                    helperproducto.insertar(p);
                    vaciarTexto();
                    Toast.makeText(this, "Producto Agregado con exito!!",  Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnListarProducto:
                listaProductos = helperproducto.getAll();
               cargarRecycler(listaProductos);
                break;
            case R.id.btnModificarProducto:
                String codig = codigo.getText().toString();
                String descr = descripcion.getText().toString();
                String preci = precio.getText().toString();
                String canti = cantidad.getText().toString();
                if(codig.length() ==0 || descr.length() == 0 || preci.length() == 0 || canti.length() == 0){
                    Toast.makeText(this, "Debe seleccionar un producto para modificar!!",  Toast.LENGTH_SHORT).show();
                }else {
                    Producto p2 = new Producto();
                    p2.setCodigo(Integer.parseInt(codig));
                    p2.setDescripcion(descr);
                    p2.setPrecio(Double.parseDouble(preci));
                    p2.setCantidad(Integer.parseInt(canti));
                    helperproducto.modificar(p2);
                    vaciarTexto();
                    Toast.makeText(this, "Producto Modificado con exito!!",  Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarTodoProducto:
                helperproducto.eliminarTodo();
                vaciarTexto();
                Toast.makeText(this, "Se han eliminado todos los registros!!",  Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEliminarCodigo:
                cod = codigo.getText().toString();
                if(cod.length() == 0){
                    Toast.makeText(this, "Por favor ingrese codigo para poder eliminar producto!!",  Toast.LENGTH_SHORT).show();
                } else {
                    Producto prd = new Producto();
                    prd.setCodigo(Integer.parseInt(cod));
                    helperproducto.eliminarCodigo(prd);
                    vaciarTexto();
                    Toast.makeText(this, "Producto Eliminado con exito!!",  Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnListarCodigoProducto:
                cod = codigo.getText().toString();
                if(cod.length() == 0){
                    Toast.makeText(this, "Por favor ingrese codigo para poder buscar producto!!",  Toast.LENGTH_SHORT).show();
                } else {
                    listaProductos = helperproducto.getProductoByCodigo(cod);
                    cargarRecycler(listaProductos);
                }
                break;
        }

    }
}
