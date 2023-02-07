package com.example.scanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;


public class MapBox extends AppCompatActivity {

    private MapView mapView;
    MapboxMap mapboxMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_box);

        MapView mapView = findViewById(R.id.mapView);

    }
}