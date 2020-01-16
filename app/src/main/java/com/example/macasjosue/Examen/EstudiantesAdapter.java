package com.example.macasjosue.Examen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;

import java.util.List;

public class EstudiantesAdapter extends RecyclerView.Adapter<EstudiantesAdapter.ViewHolderEstudiante> {

    List<ModeloEstudiante> lista;
    public EstudiantesAdapter(List<ModeloEstudiante> lista){this.lista = lista;}

    @Override
    public ViewHolderEstudiante onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_estudiante, null);
        return new ViewHolderEstudiante(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderEstudiante viewHolderEstudiante, int pos) {
        viewHolderEstudiante.datonombres.setText(lista.get(pos).getNombres()+"");
        viewHolderEstudiante.datomateria.setText(lista.get(pos).getMateria());
        viewHolderEstudiante.datonota1.setText(lista.get(pos).getNota1()+"");
        viewHolderEstudiante.datonota2.setText(lista.get(pos).getNota2()+"");
        viewHolderEstudiante.datonota3.setText(lista.get(pos).getNota3()+"");
        viewHolderEstudiante.datoPromedio.setText(lista.get(pos).getPromedio()+"");

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static  class ViewHolderEstudiante extends RecyclerView.ViewHolder {
        TextView datonombres;
        TextView datomateria;
        TextView datonota1;
        TextView datonota2;
        TextView datonota3;
        TextView datoPromedio;


        public ViewHolderEstudiante (View itemView) {
            super(itemView);
            datonombres= itemView.findViewById(R.id.lblnombresE);
            datomateria= itemView.findViewById(R.id.lblMateriaE);
            datonota1= itemView.findViewById(R.id.lblN1E);
            datonota2= itemView.findViewById(R.id.lblN2E);
            datonota3= itemView.findViewById(R.id.lblN3E);
            datoPromedio= itemView.findViewById(R.id.lblpromedioE);

        }
    }

}
