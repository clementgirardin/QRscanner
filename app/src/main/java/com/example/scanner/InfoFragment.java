package com.example.scanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class InfoFragment extends Fragment {

    EditText telephone;
    TextView localisation;
    Button btn_envoie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // Récupération par ID
        telephone = view.findViewById(R.id.telephone);
        localisation = view.findViewById(R.id.localisation);
        btn_envoie = view.findViewById(R.id.btn_envoie);


        localisation.setText("Localisation : " + 12);


        return view;
    }
}