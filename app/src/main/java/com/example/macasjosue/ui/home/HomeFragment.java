package com.example.macasjosue.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.macasjosue.R;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private EditText entrada1;
    private EditText entrada2;
    private TextView respuesta;
    private Button suma;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        entrada1 = root.findViewById(R.id.txtE1);
        entrada2 = root.findViewById(R.id.txtE2);
        respuesta = root.findViewById(R.id.txtRespuestaS);
        suma = root.findViewById(R.id.btnCalcularS);
        suma.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        String n1 = entrada1.getText().toString();
        String n2 = entrada2.getText().toString();
        if(n1.length() == 0 || n2.length() == 0){
            Toast.makeText(getContext(), "Por favor llene todos los campos!!",  Toast.LENGTH_SHORT).show();
        }else{

            int valor1 = Integer.parseInt(n1);
            int valor2 = Integer.parseInt(n2);
            int suma = valor1+valor2;
            respuesta.setText(suma+"");
        }
    }
}