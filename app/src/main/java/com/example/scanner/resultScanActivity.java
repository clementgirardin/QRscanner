package com.example.scanner;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class resultScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_scan);


        //----------------------------------Séparation partie du code----------------------------------------\\
        // Création instances d'infoFragment
//        InfoFragment fragmentInfos = new InfoFragment();
        InfosFragmentPopUp monFragmentPopup = InfosFragmentPopUp.newInstance();

        // Création d'un objet bundle pour y stocker les coordonées
        Bundle args = new Bundle();
        // TODO: Remplacer les args ci-dessous par les infos présente dans l'API
//        args.putDouble("latitude", latitude);
//        args.putDouble("longitude", longitude);

        // Passe le bundle en tant qu'argument aux deux fragments
        fragmentInfos.setArguments(args);
        // Ajout du fragment infos après la map
        getSupportFragmentManager().beginTransaction().replace(R.id.resultScan, fragmentInfos).commit();
        Log.d("result1", "fragments ajoutés");
    }
}