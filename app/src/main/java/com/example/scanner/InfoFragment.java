package com.example.scanner;

import android.os.Bundle;
import android.telephony.SmsManager;
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

    // initialisation des variables de coordonnées
    double latitude = 0;
    double longitude = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // Récupération par ID
        telephone = view.findViewById(R.id.telephone);
        localisation = view.findViewById(R.id.localisation);
        btn_envoie = view.findViewById(R.id.btn_envoie);

        // Récupération des arguments passés en paramètre à la création de l'instance d'infosfragment
        Bundle bundleInfos = getArguments();

        if (bundleInfos != null){
            // Récupération des valeurs si arguments n'est pas null
            latitude = bundleInfos.getDouble("latitude");
            longitude = bundleInfos.getDouble("longitude");
        }


        // Lance l'activité scan_code_qr au clique du bouton
        btn_envoie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numTel = telephone.getText().toString();
                String message = "Localisation" + latitude + longitude;

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(numTel, null, message, null, null);
            }
        });

        // set un nouveau text a localisation avec les coordonées
        localisation.setText("Localisation : " + latitude + ", " + longitude);


        return view;
    }
}