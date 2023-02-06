package com.example.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_scan;
    public static TextView txt_resultat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération par id
        btn_scan = (Button) findViewById(R.id.btn_scan);
        txt_resultat = (TextView) findViewById(R.id.txt_resultat);

        // Lance l'activité scan_code_qr au clique du bouton
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),googleMapActivity.class));
            }
        });
    }
}