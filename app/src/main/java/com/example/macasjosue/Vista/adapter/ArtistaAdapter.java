package com.example.macasjosue.Vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;
import com.example.macasjosue.modelo.Artista;

import java.util.List;

public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.ViewHolderArtista> {
    List <Artista> lista;
    public ArtistaAdapter(List<Artista> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolderArtista onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artista, null);
        return new ViewHolderArtista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArtista viewHolderArtista, int pos) {
        viewHolderArtista.datonombres.setText(lista.get(pos).getNombres());
        viewHolderArtista.datoapellidos.setText(lista.get(pos).getApellidos());
        viewHolderArtista.datonombreartistico.setText(lista.get(pos).getNombreArtistico());
        viewHolderArtista.datofechanacimiento.setText((CharSequence) lista.get(pos).getFechaNacimiento());
        viewHolderArtista.datoFoto.setImageResource(lista.get(pos).getFoto());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static  class ViewHolderArtista extends RecyclerView.ViewHolder {
        TextView datonombres;
        TextView datoapellidos;
        TextView datonombreartistico;
        TextView datofechanacimiento;
        ImageView datoFoto;

        public ViewHolderArtista(@NonNull View itemView) {
            super(itemView);
            datonombres = itemView.findViewById(R.id.lblNombreArtista);
            datoapellidos = itemView.findViewById(R.id.lblApellidoArtista);
            datonombreartistico = itemView.findViewById(R.id.lblNombreartisticoArtista);
            datofechanacimiento= itemView.findViewById(R.id.lblFechanacimientoArtista);
            datoFoto = itemView.findViewById(R.id.imgFoto);
        }
    }

}
