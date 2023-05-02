package com.example.scanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_scan;
    ImageView menu;
    static ListView listView;
    private static final int PERMISSION = 100;
    private List<String> codesQrScannes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération par id
        btn_scan = findViewById(R.id.btn_scan);
        menu = findViewById(R.id.menu);
        listView = findViewById(R.id.liste_materiel);


        // Création de la liste des codes QR scannés
        codesQrScannes = new ArrayList<>();
        listView = findViewById(R.id.liste_materiel);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, codesQrScannes);
        listView.setAdapter(adapter);

        // Lance l'activité scan_code_qr au clique du bouton
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Vérifie si les permissions de la caméra, de la localisation et des SMS sont autorisées
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                    // Si l'une des permissions n'a pas été accordée, demande les permissions nécessaires
                    requestCameraPermission();
                } else {
                    // Si toutes les permissions sont accordées, lance ScanCodeQrActivity
                    Intent intent = new Intent(MainActivity.this, ScanCodeQrActivity.class);
                    startActivityForResult(intent, 1);
                }

            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDropdownMenu(v);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Vérifie que la requête provient bien de ScanCodeQrActivity et que le résultat est OK
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Récupère le texte scanné depuis les données renvoyées
            String texteScanne = data.getStringExtra("texteScanne");
            // Ajoute le texte scanné à la liste des codes QR scannés
            codesQrScannes.add(texteScanne);
            // Met à jour l'adapter de la ListView
            ArrayAdapter adapter = (ArrayAdapter) ((ListView) findViewById(android.R.id.content).findViewById(R.id.liste_materiel)).getAdapter();
            adapter.notifyDataSetChanged();
            // Retourne à la vue principale (mainFragment)
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    private void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.SEND_SMS}, PERMISSION);
    }

    public static void addItemToListView(String itemText) {
        // Récupération de l'Adapter
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        // Ajout de l'élément à l'Adapter
        adapter.add(itemText);
        // Notification de l'Adapter que les données ont changé
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                // Si toutes les permissions sont accordées, lance ScanCodeQrActivity
                startActivity(new Intent(MainActivity.this, ScanCodeQrActivity.class));

            } else {
                // Si l'une des permissions est refusée, affiche un message d'erreur
                Toast.makeText(this, "Permissions non autorisées", Toast.LENGTH_SHORT).show();
                // Si l'une des permissions n'a pas été accordée, demande les permissions nécessaires
                requestCameraPermission();
            }
        }
    }

    private void showDropdownMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_deroulant, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Traitez la sélection de l'utilisateur ici
                switch (item.getItemId()) {
                    case R.id.materielEmprunte:
                        // Option 1 sélectionnée
                        return true;
                    case R.id.mesEmprunts:
                        // Option 2 sélectionnée
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }
}