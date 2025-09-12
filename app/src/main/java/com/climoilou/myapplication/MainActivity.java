package com.climoilou.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    Joueur[] joueurs = {
            new Joueur("Dupont", "Jean", "jean.dupont@email.com"),
            new Joueur("Martin", "Claire", "claire.martin@email.com"),
            new Joueur("Durand", "Paul", "paul.durand@email.com"),
            new Joueur("Lefevre", "Julie", "julie.lefevre@email.com"),
            new Joueur("Moreau", "Luc", "luc.moreau@email.com"),
            new Joueur("Simon", "Laura", "laura.simon@email.com"),
            new Joueur("Laurent", "Marc", "marc.laurent@email.com"),
            new Joueur("Garcia", "Sophie", "sophie.garcia@email.com"),
            new Joueur("Petit", "Antoine", "antoine.petit@email.com"),
            new Joueur("Roux", "Emma", "emma.roux@email.com")
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.joueurs_item) {
            Intent intent = new Intent(this, JoueurActivity.class);
            intent.putExtra("joueurs_tab", joueurs);
            startActivity(intent);
            return true;
        } else if (id == R.id.presence_item) {
            Intent intent = new Intent(this, PresenceActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.calendrier_item) {
            Intent intent = new Intent(this, CalendrierActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.partenaires_item) {
            Intent intent = new Intent(this, PartenairesActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.sondage_item) {
            Intent intent = new Intent(this, SondageActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}