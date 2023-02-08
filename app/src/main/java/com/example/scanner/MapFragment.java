package com.example.scanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialise la vue
        View view = inflater.inflate(R.layout.fragment_map,container,false);

        // Initialisation map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_google);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                // Quand la map est chargée
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        // Quand la map est cliquée
                        // Initialise le marker
                        MarkerOptions markerOptions = new MarkerOptions();

                        // Set la position du marqueur
                        markerOptions.position(latLng);
                        // Set le titre du marqueur
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        // Ajout du marqueur sur la map
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });
        return view;
    }
}