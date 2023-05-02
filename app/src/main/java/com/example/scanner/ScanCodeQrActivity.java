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
        String scannedText = result.getText();

        MainActivity.addItemToListView(scannedText);
        // Retour à MainActivity
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

    @Override
    protected void onPause() {
        super.onPause();
        // Arrête la caméra
        scannerView.stopCamera();
    }
}
