package com.example.scanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeQrActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code_qr);

        // Initialisation du scanner
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }

    /**
     * SET le text renvoyé par le scan du code QR
     * @param result
     */
    @Override
    public void handleResult(Result result) {
//        MainActivity.txt_resultat.setText(result.getText());
//        // Retour à la page d'accueil après le scan pour visualiser le résultat
//        onBackPressed();


        // Affichage de la map
        // Récupération données code QR
        String resultQRcode = result.getText();
        // Affichage données récupérées dans la textView
        MainActivity.txt_resultat.setText(resultQRcode);

        // Vérifie si la chaîne commence par "geo:"
        if (resultQRcode.startsWith("geo:")) {
            // Extrait la chaîne de caractère "geo:" renvoyée par le code QR
            resultQRcode = resultQRcode.substring(4);
        }

        // Sépare la latitude de la longitude retournés par le code QR
        String[] localisation = resultQRcode.split(",");

        // Récupération latitude & longitude après la séparation des valeurs
        double latitude = Double.parseDouble(localisation[0]);
        double longitude = Double.parseDouble(localisation[1]);


        // Envoie des coordonnées pour infos fragment
        // Création d'une instance d'infoFragment
        InfoFragment fragmentInfos = new InfoFragment();
        // Création d'un objet bundle pour y stocker les coordonées
        Bundle bundleInfos = new Bundle();
        bundleInfos.putDouble("latitude", latitude);
        bundleInfos.putDouble("longitude", longitude);
        // Passe le bundle en tant qu'argument
        fragmentInfos.setArguments(bundleInfos);


        // Création d'une instance d'infoFragment
        InfoFragment fragmentMap = new InfoFragment();
        // Création d'un objet bundle pour y stocker les coordonées
        Bundle bundleLoca = new Bundle();
        bundleLoca.putDouble("latitude", latitude);
        bundleLoca.putDouble("longitude", longitude);
        // Passe le bundle en tant qu'argument
        fragmentMap.setArguments(bundleLoca);

        // Remplace activity main par le fragment map
        getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentMap).commit();
        // ajout du fragment infos après la map
        getSupportFragmentManager().beginTransaction().add(R.id.main, fragmentInfos).commit();


        // Start l'activité et fournis la latitude et longitude en paramètre
//        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
//        intent.putExtra("latitude", latitude);
//        intent.putExtra("longitude", longitude);
//        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Définit l'appel de la fonction handleResult à chaque scan du scanneur
        scannerView.setResultHandler(this);
        // Démarre la caméra
        scannerView.startCamera();
    }
}