package com.example.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.maps.MapboxMap;

public class MainActivity extends AppCompatActivity {

    Button btn_scan;
    public static TextView txt_resultat;

    MapboxMap mapboxMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Plugin.Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        // Récupération par id
        btn_scan = (Button) findViewById(R.id.btn_scan);
        txt_resultat = (TextView) findViewById(R.id.txt_resultat);
//        MapView mapView = findViewById(R.id.mapview);
//
//        mapView.getMapAsync(new OnMapReadyCallback()){
//            @Override
//            public void onMapReady(MapboxMap mapboxMap){
//                mapboxMap.setStyle();
//            }
//        }

        // Lance l'activité scan_code_qr au clique du bouton
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScanCodeQrActivity.class));
            }
        });
    }
}