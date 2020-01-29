package com.example.macasjosue.ui.gallery;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.fragmentos.FrgUno;
import com.example.macasjosue.Vista.fragmentos.frgDos;

public class GalleryFragment extends Fragment implements View.OnClickListener,  FrgUno.OnFragmentInteractionListener, frgDos.OnFragmentInteractionListener{

    Button botonFrg1,botonFrg2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        botonFrg1 = root.findViewById(R.id.btnFragm1);
        botonFrg2 = root.findViewById(R.id.btnFragm2);
        botonFrg1.setOnClickListener(this);
        botonFrg2.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFrg1:
                FrgUno fragmento1 = new FrgUno();
                FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedorFrg, fragmento1);
                transaction1.commit();
                break;
            case R.id.btnFrg2:
                frgDos fragmento2 = new frgDos();
                FragmentTransaction transaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.contenedorFrg, fragmento2);
                transaction2.commit();

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}