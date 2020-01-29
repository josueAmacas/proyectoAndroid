package com.example.macasjosue.Vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;
import com.example.macasjosue.modelo.Carro;

import java.util.List;

public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.ViewHolderCarro> implements View.OnClickListener {

    List<Carro> lista;

    public CarroAdapter(List<Carro> lista){
        this.lista = lista;
    }

    public View.OnClickListener click;

    @Override
    public ViewHolderCarro onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_carro, null);
        view.setOnClickListener(this);
        return new ViewHolderCarro(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderCarro viewHolderCarro, int position) {
        viewHolderCarro.datoPlaca.setText(lista.get(position).getPlaca()+"");
        viewHolderCarro.datoMarca.setText(lista.get(position).getMarca());
        viewHolderCarro.datoModelo.setText(lista.get(position).getModelo()+"");
        viewHolderCarro.datoAnio.setText(lista.get(position).getAnio()+"");
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

    public static  class ViewHolderCarro extends RecyclerView.ViewHolder {
        TextView datoPlaca;
        TextView datoModelo;
        TextView datoMarca;
        TextView datoAnio;

        public ViewHolderCarro( View itemView) {
            super(itemView);
            datoPlaca = itemView.findViewById(R.id.lblPlacaC);
            datoMarca = itemView.findViewById(R.id.lblMarcaC);
            datoModelo = itemView.findViewById(R.id.lblModeloC);
            datoAnio = itemView.findViewById(R.id.lblAnioC);
        }
    }


}
