package com.example.macasjosue.Vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;
import com.example.macasjosue.modelo.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolderUsuario> implements View.OnClickListener {

    List<Usuario> lista;

    public UsuarioAdapter(List<Usuario> lista){
        this.lista = lista;
    }

    public View.OnClickListener click;

    @Override
    public ViewHolderUsuario onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, null);
        view.setOnClickListener(this);
        return new ViewHolderUsuario(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderUsuario viewHolderUsuario, int position) {
        viewHolderUsuario.datonCedula.setText(lista.get(position).getCedula()+"");
        viewHolderUsuario.datoNombre.setText(lista.get(position).getNombres());
        viewHolderUsuario.datoProfesion.setText(lista.get(position).getProfesion());
        if (lista.get(position).getImagen()!=null){
            viewHolderUsuario.imagen.setImageBitmap(lista.get(position).getImagen());
        }else{
            viewHolderUsuario.imagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.click = listener;
    }

    @Override
    public void onClick(View v) {
        if(click != null){
            click.onClick(v);
        }
    }

    public static  class ViewHolderUsuario extends RecyclerView.ViewHolder {
        TextView datonCedula,datoNombre,datoProfesion;
        ImageView imagen;

        public ViewHolderUsuario(View itemView) {
            super(itemView);
            datonCedula = itemView.findViewById(R.id.lblCedulaUsuario);
            datoNombre = itemView.findViewById(R.id.lblNombresUsuario);
            datoProfesion = itemView.findViewById(R.id.lblProfesionUsuario);
            imagen = itemView.findViewById(R.id.imgUsuario);
        }
    }

}
