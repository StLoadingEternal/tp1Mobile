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

    //La navigation se fait seulement de la vue main vers les autres vues pour le moment. Il faut aussi garder les modifications en mémoire. Ajout de DOC
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

        //Récupération des données passées à l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Parcelable[] tab = extras.getParcelableArray("joueurs_tab");
            playersTab = new Joueur[tab.length];
            for (int i = 0; i < tab.length; i++) {
                playersTab[i] = (Joueur) tab[i];
            }
        }

        //ArrayList<Joueur> playersList = new ArrayList<>(Arrays.asList(playersTab));
        //ListAdapter adapter = new ArrayAdapter<Joueur>(this, android.R.layout.simple_list_item_1, playersList);

        //Adapter personnalisé joueur qu'on apllique à la liste view
        JoueurAdapter adapter = new JoueurAdapter(this, playersTab);
        ListView listView = findViewById(R.id.listeJoueur);
        listView.setAdapter(adapter);

        //Affiche d'une alerte d'information lorsqu'on clique sur un joueur de la liste
        listView.setOnItemClickListener( (parent, view, position, id) -> {
            Joueur joueur = (Joueur) parent.getItemAtPosition(position);//Lancer une exception peut-être
            new AlertDialog.Builder(this).setTitle("Contact du joueur").setMessage("Le numéro de téléphone est :" + joueur.getContact()).show();
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return AppBar.onCreateOptionsMenu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return AppBar.onOptionsItemSelected(item, this, Data.getJoueurs(), Data.getActivites())
                || super.onOptionsItemSelected(item);
    }


}

