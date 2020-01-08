package com.example.macasjosue.Vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;
import com.example.macasjosue.modelo.Reyes;

import java.util.List;

public class ReyesAdapter extends RecyclerView.Adapter<ReyesAdapter.ViewHolderReyes>{

    List<Reyes> lista;
    public ReyesAdapter(List<Reyes> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolderReyes onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.iitem_reyes, null);
        return new ViewHolderReyes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReyes viewHolderReyes, int pos) {
        viewHolderReyes.datonombres.setText(lista.get(pos).getNombre());
        viewHolderReyes.datoperiodo.setText(lista.get(pos).getPeriodo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static  class ViewHolderReyes extends RecyclerView.ViewHolder {
        TextView datonombres;
        TextView datoperiodo;

        public ViewHolderReyes(@NonNull View itemView) {
            super(itemView);
            datonombres = itemView.findViewById(R.id.lblNombreReyes);
            datoperiodo = itemView.findViewById(R.id.lblPeriodoReyes);
        }
    }
}
