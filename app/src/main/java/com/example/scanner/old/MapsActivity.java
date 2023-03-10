package com.example.scanner.old;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.scanner.R;
import com.example.scanner.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtenez le SupportMapFragment et soyez averti lorsque la carte est prête à être utilisée.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Remplacement de mapFragment à l'activitée
        getSupportFragmentManager().beginTransaction().replace(R.id.map, mapFragment, "MapsFragment").addToBackStack(null).commit();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Test valeurs fixe
//        double latitude = 47.98;
//        double longitude = 79.012;

        // Récupère les paramètres fournis
        double latitude = getIntent().getDoubleExtra("latitude", 0);
        double longitude = getIntent().getDoubleExtra("longitude", 0);

        // Ajout d'un marker avec les coordonées fournies
        LatLng coordonees = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(coordonees).title(latitude + " , " + longitude));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordonees));
        // Zoom sur le marker
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordonees, 15));
    }
}