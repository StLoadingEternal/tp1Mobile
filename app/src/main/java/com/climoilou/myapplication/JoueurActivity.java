package com.climoilou.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Parcelable[] tab = extras.getParcelableArray("joueurs_tab");
            playersTab = new Joueur[tab.length];
            for (int i = 0; i < tab.length; i++) {
                playersTab[i] = (Joueur) tab[i];
            }
        }
        //ArrayList<Joueur> playersList = new ArrayList<>(Arrays.asList(playersTab));

        ArrayList<String> car = new ArrayList<>();
        for (Joueur joueur : playersTab) {
            String ligne = joueur.getNom() + " " + joueur.getPrenom() + " - " + joueur.getAddresseCourriel();
            car.add(ligne);
        }

        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.joueurs_listview, car);

        ListView listView = (ListView) findViewById(R.id.liste);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.presence_item) {
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

