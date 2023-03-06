package com.example.scanner;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        Log.d("result3", "Code info fragment");
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // Récupération par ID
        telephone = view.findViewById(R.id.telephone);
        localisation = view.findViewById(R.id.localisation);
        btn_envoie = view.findViewById(R.id.btn_envoie);

        // Récupère les paramètres fournis
        Bundle args = getArguments();
        assert args != null;
        latitude = args.getDouble("latitude");
        longitude = args.getDouble("longitude");

        // Récupération des arguments passés en paramètre à la création de l'instance d'infosfragment
        Bundle bundleInfos = getArguments();

        if (bundleInfos != null){
            // Récupération des valeurs si arguments n'est pas null
            latitude = bundleInfos.getDouble("latitude");
            longitude = bundleInfos.getDouble("longitude");
        }
        // set un nouveau text a localisation avec les coordonées
        localisation.setText("Localisation : " + latitude + ", " + longitude);

        Log.d("result4", "Message envoyé");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Lance l'activité scan_code_qr au clique du bouton
        btn_envoie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numTel = telephone.getText().toString();
                String message = "Localisation: " + latitude + longitude;

                // Supprime les espaces du numéro de tel si il y en a
                numTel = numTel.replaceAll("\\s+", "");
                // Vérifie si le numéro de téléphone est valide
                if (!numTel.matches("^\\+?[0-9]{10,13}$")) {
                    Toast.makeText(getActivity(), "Numéro de téléphone invalide", Toast.LENGTH_SHORT).show();
                    return;
                }

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(numTel, null, message, null, null);

                // Validation envoie du message
                Toast.makeText(getActivity(), "Message envoyé à " + numTel, Toast.LENGTH_SHORT).show();
            }
        });
    }
}