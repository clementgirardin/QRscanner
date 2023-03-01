package com.example.scanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap mMap;
//    private ActivityMapsBinding binding;
    double latitude;
    double longitude;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            // Test valeurs fixe
//        double latitude = 47.98;
//        double longitude = 79.012;

            // Récupère les paramètres fournis
            assert getArguments() != null;
            double latitude = getArguments().getDouble("latitude", 0);
            double longitude = getArguments().getDouble("longitude", 0);

            // Ajout d'un marker avec les coordonées fournies
            LatLng coordonees = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(coordonees).title(latitude + " , " + longitude));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordonees));
            // Zoom sur le marker
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordonees, 15));
        }
}