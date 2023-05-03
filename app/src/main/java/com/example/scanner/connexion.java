package com.example.scanner;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class connexion extends AppCompatActivity {
     NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter[] intentFiltersArray;
    String[][] techListsArray;
    TextView infoBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        // Récupération de l'objet TextView dans la mise en page
        infoBadge = findViewById(R.id.infosBadge);

        // Vérification que l'appareil supporte la technologie NFC
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "Votre appareil ne prend pas en charge la technologie NFC.", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Initialisation du PendingIntent pour la détection de tags NFC
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter intentFilter = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        intentFiltersArray = new IntentFilter[] { intentFilter };
        techListsArray = new String[][] { new String[] { android.nfc.tech.NfcV.class.getName() } };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            // Activation de la détection de tags NFC en premier plan
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            // Désactivation de la détection de tags NFC en premier plan
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Récupération de l'objet Tag à partir de l'intent
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null) {
            // Conversion de l'identifiant de la carte en une chaîne de caractères
            String tagInfo = "ID de la carte : " + byteArrayToHexString(tag.getId());
            // Affichage des informations de la carte dans le champ texte
            infoBadge.setText(tagInfo);
        }
    }

    /**
     * Convertit un tableau de bytes en une chaîne de caractères en format hexadécimal
     *
     * @param array Le tableau de bytes à convertir
     * @return La chaîne de caractères représentant le tableau de bytes en format hexadécimal
     */
    private static String byteArrayToHexString(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02x", b & 0xFF));
        }
        return sb.toString();
    }
}
