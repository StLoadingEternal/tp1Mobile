package com.climoilou.myapplication;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Donnee.Data;
import aModels.Activite;
import aModels.Joueur;

public class PresenceActivity extends AppCompatActivity {


    Joueur[] playersTab;
    Activite[] activitiesTab;
    Spinner spinnerPlayer;
    Spinner spinnerActivity;
    TextView activiteText;
    TextView nombrePresence ;
    RadioButton radioButton;

    //La navigation se fait seulement de la vue main vers les autres vues pour le moment. Il faut aussi garder les modifications en mémoire. Ajout de DOC

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.presences);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.presencesView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //La majorité des toolbar ont le même ID, Changer?
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //récupérations des données sur les activités et les joueurs
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

        //Mise en place des adaptateurs pour l'affichage du spinner
        ArrayAdapter<Joueur> adapterP = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playersTab );
        ArrayAdapter<Activite> adapterA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, activitiesTab );

        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //on peut utiliser des binding à la place
        //Les différents éléments de notre vue
        spinnerPlayer = findViewById(R.id.spinnerPlayer);
        spinnerActivity = findViewById(R.id.spinnerActivity);
        activiteText = findViewById(R.id.activiteText);
        nombrePresence = findViewById(R.id.nombrePresence);
        radioButton = findViewById(R.id.radioButton);

        //Configuration des deux spinner
        spinnerPlayer.setAdapter(adapterP);
        spinnerActivity.setAdapter(adapterA);

        //Affichage de l'interface au chargement du context
        mettreAjourBouton();
        mettreAjourNomActivite();
        mettreAjourNombrePresence();

        //Comportements lorsqu'on clique sur le radio button. Il semble qu'on ne peut plus le décocher après.
        radioButton.setOnClickListener(v -> {
            clickBouton();
            mettreAjourNombrePresence();
        });

        //Comportements lorsqu'on choisi un item du spinner des joueurs
        spinnerPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mettreAjourBouton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Comportements lorsqu'on choisi un item du spinner des activités
        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mettreAjourBouton();
                mettreAjourNomActivite();
                mettreAjourNombrePresence();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     *  Change le nombre de présences , à une activité, affiché dans l'interface en fonction des évènements
     */
    private void mettreAjourNombrePresence() {
        int nombre = getActiviteSelectione().getParticipants().size();
        nombrePresence.setText(String.valueOf(nombre));
    }

    /**
     *  Ajoute le joueur sélectionné dans la liste de participants de l'activité sélectionnée.
     *  Notifie ensuite qu'il y'a eu un changement dans les données pour metttre à jour l'interface
     */
    private void clickBouton() {

        getActiviteSelectione().getParticipants().add(getJoueurSelectionne());

        SpinnerAdapter adapter = spinnerActivity.getAdapter();
        if (adapter instanceof ArrayAdapter) {
            ((ArrayAdapter) adapter).notifyDataSetChanged();
        }
    }

    /**
     * Retourne l'activité sélctionnée
     * @return Activite
     */
    private Activite getActiviteSelectione() {
        //Même reférence
        return (Activite) spinnerActivity.getSelectedItem();
    }

    /**
     * Retourne le joueur sélectionné
     * @return Joueur
     */
    private Joueur getJoueurSelectionne() {
        return (Joueur) spinnerPlayer.getSelectedItem();
    }

    /**
     *  Change l'état du bouton en fonction de l'activité et du joueur choisi
     */
    private void mettreAjourBouton() {
        radioButton.setChecked(getActiviteSelectione().getParticipants().contains(getJoueurSelectionne()));
    }

    /**
     *  Change le nom d''activité affiché dans le textView en fonction de l'activité choisie.
     */
    private void mettreAjourNomActivite() {
        activiteText.setText(getActiviteSelectione().getTitre());
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
