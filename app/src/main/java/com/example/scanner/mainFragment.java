package com.example.scanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class mainFragment extends Fragment {

    Button btn_scan;
    public static TextView txt_resultat;
    private static final int PERMISSION = 100;
    private List<String> codesQrScannes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Récupération par id
        btn_scan = view.findViewById(R.id.btn_scan);

        // Création de la liste des codes QR scannés
        codesQrScannes = new ArrayList<>();
        ListView listView = view.findViewById(R.id.liste_materiel);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, codesQrScannes);
        listView.setAdapter(adapter);

        // Lance l'activité scan_code_qr au clique du bouton
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Vérifie si les permissions de la caméra, de la localisation et des SMS sont autorisées
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                    // Si l'une des permissions n'a pas été accordée, demande les permissions nécessaires
                    requestCameraPermission();
                } else {
                    // Si toutes les permissions sont accordées, lance ScanCodeQrActivity
                    Intent intent = new Intent(getContext(), ScanCodeQrActivity.class);
                    startActivityForResult(intent, 1);
                }

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Vérifie que la requête provient bien de ScanCodeQrActivity et que le résultat est OK
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK) {
            // Récupère le texte scanné depuis les données renvoyées
            String texteScanne = data.getStringExtra("texteScanne");
            // Ajoute le texte scanné à la liste des codes QR scannés
            codesQrScannes.add(texteScanne);
            // Met à jour l'adapter de la ListView
            ArrayAdapter adapter = (ArrayAdapter) ((ListView) getView().findViewById(R.id.liste_materiel)).getAdapter();
            adapter.notifyDataSetChanged();
            // Retourne à la vue principale (mainFragment)
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }
    }

    private void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.SEND_SMS}, PERMISSION);
    }

    public void addItemToListView(String itemText) {
        // Récupération de la ListView
        ListView listView = getView().findViewById(R.id.liste_materiel);

        // Récupération de l'Adapter
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();

        // Ajout de l'élément à l'Adapter
        adapter.add(itemText);

        // Notification de l'Adapter que les données ont changé
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                // Si toutes les permissions sont accordées, lance ScanCodeQrActivity
                startActivity(new Intent(getContext(),ScanCodeQrActivity.class));
            } else {
                // Si l'une des permissions est refusée, affiche un message d'erreur
                Toast.makeText(getActivity(), "Permissions non autorisées", Toast.LENGTH_SHORT).show();
                // Demande a nouveau les permissions
                requestCameraPermission();
            }
        }
    }
}