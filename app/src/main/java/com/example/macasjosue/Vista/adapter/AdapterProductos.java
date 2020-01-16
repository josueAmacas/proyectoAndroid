package com.example.macasjosue.Vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;
import com.example.macasjosue.modelo.Producto;

import java.util.List;

public class AdapterProductos extends RecyclerView.Adapter<AdapterProductos.ViewHolderProducto> implements View.OnClickListener {

    List<Producto> lista;

    public AdapterProductos(List<Producto> lista){
        this.lista = lista;
    }

    public View.OnClickListener click;

    @Override
    public ViewHolderProducto onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemproductos, null);
        view.setOnClickListener(this);
        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderProducto viewHolderProducto, int pos) {
        viewHolderProducto.datocodigo.setText(lista.get(pos).getCodigo()+"");
        viewHolderProducto.datodescripcion.setText(lista.get(pos).getDescripcion());
        viewHolderProducto.datoprecio.setText(lista.get(pos).getPrecio()+"");
        viewHolderProducto.datocantidad.setText(lista.get(pos).getCantidad()+"");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {
        if(click != null){
            click.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.click = listener;
    }

    public static  class ViewHolderProducto extends RecyclerView.ViewHolder {
        TextView datocodigo;
        TextView datodescripcion;
        TextView datoprecio;
        TextView datocantidad;

        public ViewHolderProducto(View itemView) {
            super(itemView);
            datocodigo = itemView.findViewById(R.id.txtCodigo);
            datodescripcion = itemView.findViewById(R.id.txtDescripcion);
            datoprecio = itemView.findViewById(R.id.txtPrecio);
            datocantidad = itemView.findViewById(R.id.txtCantidad);
        }
    }
}
