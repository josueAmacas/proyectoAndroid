package com.example.macasjosue.ui.share;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.AdapterProductos;
import com.example.macasjosue.controlador.HelperProductos;
import com.example.macasjosue.modelo.Producto;

import java.util.List;

public class ShareFragment extends Fragment implements View.OnClickListener{

    EditText codigo,descripcion,precio,cantidad;
    Button botonAgregar, botonListar, botonModificar, botonEliminarTodo,getBotonEliminarCodigo, botonBuscarCodigo;
    RecyclerView recyclerProductos;
    HelperProductos helperproducto;
    List<Producto> listaProductos;
    AdapterProductos adapter;
    String cod;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_share, container, false);
        codigo = root.findViewById(R.id.txtCodigoP2);
        descripcion = root.findViewById(R.id.txtDescripcionP2);
        precio= root.findViewById(R.id.txtPrecioP2);
        cantidad= root.findViewById(R.id.txtCantidadP2);
        botonAgregar = root.findViewById(R.id.btnAgregarProducto2);
        botonListar= root.findViewById(R.id.btnListarProducto2);
        botonModificar = root.findViewById(R.id.btnModificarProducto2);
        botonEliminarTodo= root.findViewById(R.id.btnEliminarTodoProducto2);
        getBotonEliminarCodigo= root.findViewById(R.id.btnEliminarCodigo2);
        botonBuscarCodigo= root.findViewById(R.id.btnListarCodigoProducto2);
        botonAgregar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonEliminarTodo.setOnClickListener(this);
        getBotonEliminarCodigo.setOnClickListener(this);
        botonBuscarCodigo.setOnClickListener(this);
        helperproducto = new HelperProductos(getContext(), "ProductoDB",null, 1);
        recyclerProductos = root.findViewById(R.id.recyclerProductos2);
        return root;
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
        recyclerProductos.setLayoutManager(new LinearLayoutManager(getContext()));
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
            case R.id.btnAgregarProducto2:
                String codi = codigo.getText().toString();
                String desc = descripcion.getText().toString();
                String prec = precio.getText().toString();
                String cant = cantidad.getText().toString();
                if(codi.length() ==0 || desc.length() == 0 || prec.length() == 0 || cant.length() == 0){
                    Toast.makeText(getContext(), "Debe llenar todos los campos!!",  Toast.LENGTH_SHORT).show();
                }else {
                    Producto p = new Producto();
                    p.setCodigo(Integer.parseInt(codi));
                    p.setDescripcion(desc);
                    p.setPrecio(Double.parseDouble(prec));
                    p.setCantidad(Integer.parseInt(cant));
                    helperproducto.insertar(p);
                    vaciarTexto();
                    Toast.makeText(getContext(), "Producto Agregado con exito!!",  Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnListarProducto2:
                listaProductos = helperproducto.getAll();
                cargarRecycler(listaProductos);
                break;
            case R.id.btnModificarProducto2:
                String codig = codigo.getText().toString();
                String descr = descripcion.getText().toString();
                String preci = precio.getText().toString();
                String canti = cantidad.getText().toString();
                if(codig.length() ==0 || descr.length() == 0 || preci.length() == 0 || canti.length() == 0){
                    Toast.makeText(getContext(), "Debe seleccionar un producto para modificar!!",  Toast.LENGTH_SHORT).show();
                }else {
                    Producto p2 = new Producto();
                    p2.setCodigo(Integer.parseInt(codig));
                    p2.setDescripcion(descr);
                    p2.setPrecio(Double.parseDouble(preci));
                    p2.setCantidad(Integer.parseInt(canti));
                    helperproducto.modificar(p2);
                    vaciarTexto();
                    Toast.makeText(getContext(), "Producto Modificado con exito!!",  Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarTodoProducto2:
                helperproducto.eliminarTodo();
                vaciarTexto();
                Toast.makeText(getContext(), "Se han eliminado todos los registros!!",  Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEliminarCodigo2:
                cod = codigo.getText().toString();
                if(cod.length() == 0){
                    Toast.makeText(getContext(), "Por favor ingrese codigo para poder eliminar producto!!",  Toast.LENGTH_SHORT).show();
                } else {
                    Producto prd = new Producto();
                    prd.setCodigo(Integer.parseInt(cod));
                    helperproducto.eliminarCodigo(prd);
                    vaciarTexto();
                    Toast.makeText(getContext(), "Producto Eliminado con exito!!",  Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnListarCodigoProducto2:
                cod = codigo.getText().toString();
                if(cod.length() == 0){
                    Toast.makeText(getContext(), "Por favor ingrese codigo para poder buscar producto!!",  Toast.LENGTH_SHORT).show();
                } else {
                    listaProductos = helperproducto.getProductoByCodigo(cod);
                    cargarRecycler(listaProductos);
                }
                break;
        }
    }
}