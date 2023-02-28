package com.example.scanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class resultScanActivity extends AppCompatActivity {
    double latitude = 0;
    double longitude = 0
            ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_scan);


        // Création d'une instance d'infoFragment
        MapsFragment fragmentMap = new MapsFragment();
        // Création d'un objet bundle pour y stocker les coordonées
        Bundle bundleLoca = new Bundle();
        bundleLoca.putDouble("latitude", latitude);
        bundleLoca.putDouble("longitude", longitude);
        // Passe le bundle en tant qu'argument
        fragmentMap.setArguments(bundleLoca);

        // Remplace activity main par le fragment map
        getSupportFragmentManager().beginTransaction().replace(R.id.resultScan, fragmentMap).commit();

        // Envoie des coordonnées pour infos fragment
        // Création d'une instance d'infoFragment
        InfoFragment fragmentInfos = new InfoFragment();
        // Création d'un objet bundle pour y stocker les coordonées
        Bundle bundleInfos = new Bundle();
        bundleInfos.putDouble("latitude", latitude);
        bundleInfos.putDouble("longitude", longitude);
        // Passe le bundle en tant qu'argument
        fragmentInfos.setArguments(bundleInfos);
        // ajout du fragment infos après la map
        getSupportFragmentManager().beginTransaction().add(R.id.resultScan, fragmentInfos).commit();
    }
}