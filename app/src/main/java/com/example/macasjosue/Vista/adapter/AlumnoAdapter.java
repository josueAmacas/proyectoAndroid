package com.example.macasjosue.Vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.macasjosue.R;
import com.example.macasjosue.modelo.Alumno;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.ViewHolderAlumno> implements View.OnClickListener{

    List<Alumno> lista;
    public AlumnoAdapter(List<Alumno> lista){
        this.lista = lista;
    }

    public View.OnClickListener click;

    @Override
    public ViewHolderAlumno onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, null);
        view.setOnClickListener(this);
        return new ViewHolderAlumno(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAlumno viewHolderAlumno, int position) {
        viewHolderAlumno.datonId.setText(lista.get(position).getIdalumno()+"");
        viewHolderAlumno.datonombre.setText(lista.get(position).getNombrealumno());
        viewHolderAlumno.datodireccion.setText(lista.get(position).getDireccionalumno());
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

    public static  class ViewHolderAlumno extends RecyclerView.ViewHolder {
        TextView datonId;
        TextView datonombre;
        TextView datodireccion;

        public ViewHolderAlumno(View itemView) {
            super(itemView);
            datonId= itemView.findViewById(R.id.lblIdAlumno);
            datonombre = itemView.findViewById(R.id.lblNombreAlumno);
            datodireccion= itemView.findViewById(R.id.lblDireccionAlumno);
        }
    }

}
