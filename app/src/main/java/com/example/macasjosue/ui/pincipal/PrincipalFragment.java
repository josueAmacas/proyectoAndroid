package com.example.macasjosue.ui.pincipal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.macasjosue.R;


public class PrincipalFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_principal, container, false);
        ImageView welcome = root.findViewById(R.id.imgWelcome);
        ImageView app = root.findViewById(R.id.imgApp);
        return root;
    }

}
