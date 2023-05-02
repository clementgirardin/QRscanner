package com.example.scanner;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
        // Pass the scannedText to MainFragment to add to the ListView
        mainFragment mainFragment = new mainFragment();
        mainFragment.addItemToListView(scannedText);
        // Return to MainFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, mainFragment).addToBackStack(null).commit();
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
