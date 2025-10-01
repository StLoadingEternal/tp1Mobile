package com.climoilou.myapplication;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import AdaptatersView.JoueurAdapter;
import Donnee.Data;
import aModels.Joueur;

public class JoueurActivity extends AppCompatActivity {

    Joueur[] playersTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joueurs);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.playersView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Accès direct aux joueurs dans Data (plus via Intent)
        playersTab = Data.getJoueurs();

        // Adapter personnalisé joueur qu'on applique à la liste view
        JoueurAdapter adapter = new JoueurAdapter(this, playersTab);
        ListView listView = findViewById(R.id.listeJoueur);
        listView.setAdapter(adapter);

        //Évènement clic
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Joueur joueur = (Joueur) parent.getItemAtPosition(position);
            new AlertDialog.Builder(this)
                    .setTitle("Contact du joueur")
                    .setMessage("Le numéro de téléphone est : " + joueur.getContact())
                    .show();
        });
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

