package com.example.scanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.plugin.Plugin;


public class MapBox extends AppCompatActivity {

    MapboxMap mapboxMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_box);

        Plugin.Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        // Récupération par id
        MapView mapView = findViewById(R.id.ma);

        mapView.getMapAsync(new OnMapReadyCallback()){
            @Override
            public void onMapReady(MapboxMap mapboxMap){
                mapboxMap.setStyle();
            }
        }
    }
}