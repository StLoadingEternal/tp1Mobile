package com.climoilou.myapplication;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import aModels.Activite;
import aModels.Joueur;

public class PresenceActivity extends AppCompatActivity {


    Joueur[] playersTab;
    Activite[] activitiesTab;

    //Il reste la logique d'implémentation des présences 

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.presences);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.presencesView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Parcelable[] tab_j = extras.getParcelableArray("joueurs_tab");
            playersTab = new Joueur[tab_j.length];
            for (int i = 0; i < tab_j.length; i++) {
                playersTab[i] = (Joueur) tab_j[i];
            }

            Parcelable[] tab_a = extras.getParcelableArray("activites_tab");
            activitiesTab = new Activite[tab_a.length];
            for (int i = 0; i < tab_a.length; i++) {
                activitiesTab[i] = (Activite) tab_a[i];
            }


        }

        ArrayAdapter<Joueur> adapterP = new ArrayAdapter<Joueur>(this, android.R.layout.simple_spinner_item, playersTab );
        ArrayAdapter<Activite> adapterA = new ArrayAdapter<Activite>(this, android.R.layout.simple_spinner_item, activitiesTab );

        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerPlayer = (Spinner) findViewById(R.id.spinnerPlayer);
        Spinner spinnerActivity = (Spinner) findViewById(R.id.spinnerActivity);

        spinnerPlayer.setAdapter(adapterP);
        spinnerActivity.setAdapter(adapterA);

        RadioButton radioButton = findViewById(R.id.radioButton);
        radioButton.setOnClickListener(v -> {
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
}
