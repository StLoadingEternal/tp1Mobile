package com.climoilou.myapplication;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import AdaptatersView.CalendrierAdaptater;
import Donnee.Data;
import aModels.Activite;

public class CalendrierActivity extends AppCompatActivity {


    Activite[] activitiesTab;

    //La navigation se fait seulement de la vue main vers les autres vues pour le moment. Il faut aussi garder les modifications en mémoire. Ajout de DOC
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendrier);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.calendrierView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        // Récupérer directement depuis Data (pas depuis l'intent)
        activitiesTab = Data.getActivites();

        // Adaptater personnalisé pour la listView des calendrier
        CalendrierAdaptater adapter = new CalendrierAdaptater(this, activitiesTab);
        ListView listView = findViewById(R.id.listeCalendrier);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return AppBar.onCreateOptionsMenu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return AppBar.onOptionsItemSelected(item, this) || super.onOptionsItemSelected(item);
    }

}
