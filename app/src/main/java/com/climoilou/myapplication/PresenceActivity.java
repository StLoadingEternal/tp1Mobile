package com.climoilou.myapplication;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

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

        Toolbar myToolbar = findViewById(R.id.toolbar);
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

        ArrayAdapter<Joueur> adapterP = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playersTab );
        ArrayAdapter<Activite> adapterA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, activitiesTab );

        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //on peut utiliser des binding à la place
        spinnerPlayer = findViewById(R.id.spinnerPlayer);
        spinnerActivity = findViewById(R.id.spinnerActivity);
        activiteText = findViewById(R.id.activiteText);
        nombrePresence = findViewById(R.id.nombrePresence);
        radioButton = findViewById(R.id.radioButton);

        spinnerPlayer.setAdapter(adapterP);
        spinnerActivity.setAdapter(adapterA);

        mettreAjourBouton();
        mettreAjourNomActivite();
        mettreAjourNombrePresence();

        radioButton.setOnClickListener(v -> {
            clickBouton();
            mettreAjourNombrePresence();
        });

        spinnerPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mettreAjourBouton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
     *
     */
    private void mettreAjourNombrePresence() {
        int nombre = getActiviteSelectione().getParticipants().size();
        nombrePresence.setText(String.valueOf(nombre));
    }

    /**
     *
     */
    private void clickBouton() {

        getActiviteSelectione().getParticipants().add(getJoueurSelectionne());

        SpinnerAdapter adapter = spinnerActivity.getAdapter();
        if (adapter instanceof ArrayAdapter) {
            ((ArrayAdapter) adapter).notifyDataSetChanged();
        }
    }

    /**
     *
     * @return
     */
    private Activite getActiviteSelectione() {
        //Même reférence
        return (Activite) spinnerActivity.getSelectedItem();
    }

    /**
     *
     * @return
     */
    private Joueur getJoueurSelectionne() {
        return (Joueur) spinnerPlayer.getSelectedItem();
    }

    /**
     *
     */
    private void mettreAjourBouton() {
        radioButton.setChecked(getActiviteSelectione().getParticipants().contains(getJoueurSelectionne()));
    }

    /**
     *
     */
    private void mettreAjourNomActivite() {
        activiteText.setText(getActiviteSelectione().getTitre());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }


}
