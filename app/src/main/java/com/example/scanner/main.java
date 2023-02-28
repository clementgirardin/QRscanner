package com.example.scanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class main extends Fragment {

    Button btn_scan;
    Button showMap;
    public static TextView txt_resultat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Récupération par id
        btn_scan = btn_scan.findViewById(R.id.btn_scan);
        txt_resultat = txt_resultat.findViewById(R.id.txt_resultat);

        // Lance l'activité scan_code_qr au clique du bouton
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}