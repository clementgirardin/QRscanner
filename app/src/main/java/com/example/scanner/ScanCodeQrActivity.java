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

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }

    /**
     * SET le text renvoyé par le scan du code QR
     * @param result
     */
    @Override
    public void handleResult(Result result) {
        MainActivity.txt_resultat.setText(result.getText());
        // Retour à la page d'accueil après le scan pour visualiser le résultat
        onBackPressed();
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