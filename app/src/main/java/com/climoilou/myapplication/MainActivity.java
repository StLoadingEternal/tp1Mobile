package com.climoilou.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import aModels.Activite;
import aModels.Joueur;

public class MainActivity extends AppCompatActivity {



    Joueur[] joueurs = new Joueur[] {
            new Joueur("Léa", "Tremblay", "lea.tremblay@gmail.com", "(514) 555-1234"),
            new Joueur("Émile", "Gagnon", "emile.gagnon@videotron.ca", "(438) 555-5678"),
            new Joueur("Sophie", "Morin", "sophie.morin@hotmail.com", "(418) 555-2468"),
            new Joueur("Nathan", "Smith", "nathan.smith@bell.net", "(613) 555-7890"),
            new Joueur("Olivia", "Lavoie", "olivia.lavoie@icloud.com", "(581) 555-1122"),
            new Joueur("Jacob", "Martin", "jacob.martin@yahoo.ca", "(819) 555-3344"),
            new Joueur("Chloe", "Dubois", "chloe.dubois@outlook.com", "(450) 555-9988"),
            new Joueur("Lucas", "Roy", "lucas.roy@live.ca", "(709) 555-8877"),
            new Joueur("Ava", "Bouchard", "ava.bouchard@gmail.com", "(873) 555-7766"),
            new Joueur("William", "Ouellet", "william.ouellet@protonmail.com", "(236) 555-6655")
    };

    Activite[] activites = new Activite[] {
            new Activite("2025-09-15 20:00", "Montreal Impact vs Toronto FC", "Montréal", Activite.Stade.DOMICILE, 0),
            new Activite("2025-09-18 19:30", "Toronto Raptors vs Boston Celtics", "Toronto", Activite.Stade.DOMICILE, 0),
            new Activite("2025-09-22 21:00", "Vancouver Whitecaps vs LA Galaxy", "Vancouver", Activite.Stade.DOMICILE, 0),
            new Activite("2025-09-25 20:30", "Montreal Impact vs NYCFC", "Montréal", Activite.Stade.DOMICILE, 0),
            new Activite("2025-09-29 19:45", "Toronto Raptors vs Chicago Bulls", "Toronto", Activite.Stade.DOMICILE, 0),
            new Activite("2025-10-02 18:00", "Vancouver Whitecaps vs Seattle Sounders", "Vancouver", Activite.Stade.EXTERIEUR, 0)
    };


    //La navigation se fait seulement de la vue main vers les autres vues pour le moment. Il faut aussi garder les modifications en mémoire. Ajout de DOC

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
            intent.putExtra("joueurs_tab", joueurs);
            intent.putExtra("activites_tab", activites);
            startActivity(intent);
            return true;
        } else if (id == R.id.calendrier_item) {
            Intent intent = new Intent(this, CalendrierActivity.class);
            intent.putExtra("activites_tab", activites);
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