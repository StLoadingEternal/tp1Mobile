package com.climoilou.myapplication;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import AdaptatersView.CalendrierAdaptater;
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

        //récupération des données passées à l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Parcelable[] tab_a = extras.getParcelableArray("activites_tab");
            activitiesTab = new Activite[tab_a.length];
            for (int i = 0; i < tab_a.length; i++) {
                activitiesTab[i] = (Activite) tab_a[i];
            }
        }

        //Adaptater personnalisé pour la listView des calendrier
        CalendrierAdaptater adapter = new CalendrierAdaptater(this, activitiesTab);
        ListView listView = findViewById(R.id.listeCalendrier);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

}
