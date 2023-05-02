package com.example.scanner;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class mesEmprunts extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_emprunts);

        // Récupération de la liste et du bouton de test
        listView = findViewById(R.id.liste_emprunts);
    }
}
