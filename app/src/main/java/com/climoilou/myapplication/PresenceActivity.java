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

import java.util.Arrays;
import java.util.Optional;

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

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //on recupère les références sur nos widgets
        spinnerPlayer = findViewById(R.id.spinnerPlayer);
        spinnerActivity = findViewById(R.id.spinnerActivity);
        activiteText = findViewById(R.id.activiteText);
        nombrePresence = findViewById(R.id.nombrePresence);
        radioButton = findViewById(R.id.radioButton);

        //Action à réaliser lorsque l'activité demarre
        mettreEnPlacSpinners();
        mettreAjourBouton();
        mettreAjourNomActivite();
        mettreAjourNombrePresence();
        definirListeners();

    }

    /**
     * Definit les actions à réaliser en fonction des évènemenets
     */
    private void definirListeners(){

        //radioBouton listener
        radioButton.setOnClickListener(v -> {
            clickBouton();
            mettreAjourNombrePresence();
        });

        //spinner des joueurs listener
        spinnerPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mettreAjourBouton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //spinner des activités listener
        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mettreAjourBouton();
                mettreAjourNomActivite();
                mettreAjourNombrePresence();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Met en place les deux spinners de la vue à partir des données de Data
     */
    private void mettreEnPlacSpinners(){
        // Accès direct aux données partagées, plus via Intent
        playersTab = Data.getJoueurs();
        activitiesTab = Data.getActivites();

        // Mise en place des adaptateurs pour l'affichage du spinner
        ArrayAdapter<Joueur> adapterP = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playersTab);
        ArrayAdapter<Activite> adapterA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, activitiesTab);

        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPlayer.setAdapter(adapterP);
        spinnerActivity.setAdapter(adapterA);
    }

    /**
     *  Change le nombre de présences d'une activité affichée dans l'interface en fonction des évènements. Met à jour aussi les DATAS
     */
    private void mettreAjourNombrePresence() {
        int nombre = getActiviteSelectione().getParticipants().size();

        //Met à jour DATA
        Optional<Activite> activiteTrouvee = Data.trouverActiviteParTitre(getActiviteSelectione().getTitre());
        if (activiteTrouvee.isPresent()){
            Activite activite = activiteTrouvee.get();

        }
        nombrePresence.setText(String.valueOf(nombre));//Change le nombre dans l'interface
    }

    /**
     *  Ajoute le joueur sélectionné dans la liste de participants de l'activité sélectionnée.
     *  Notifie ensuite qu'il y'a eu un changement dans les données pour metttre à jour l'interface
     */
    private void clickBouton() {

        //Au clic du radio bouton ajoute le joueur comme participant de l'activité
        getActiviteSelectione().getParticipants().add(getJoueurSelectionne());

        //Met à jour les données des activités dans l'adaptater
        SpinnerAdapter adapter = spinnerActivity.getAdapter();
        if (adapter instanceof ArrayAdapter) {
            ((ArrayAdapter) adapter).notifyDataSetChanged();
        }
    }

    /**
     * Retourne l'activité sélectionnée dans le spinner
     * @return Activite
     */
    private Activite getActiviteSelectione() {
        //Même reférence
        return (Activite) spinnerActivity.getSelectedItem();
    }

    /**
     * Retourne le joueur sélectionné dans le spinner
     * @return Joueur
     */
    private Joueur getJoueurSelectionne() {
        return (Joueur) spinnerPlayer.getSelectedItem();
    }

    /**
     *  Change l'état du bouton radio en fonction de l'activité et du joueur choisi (checked si le joueur participe à l'activité)
     */
    private void mettreAjourBouton() {
        radioButton.setChecked(getActiviteSelectione().getParticipants().contains(getJoueurSelectionne()));
    }

    /**
     *  Change le nom d'activité affiché dans le textView en fonction de l'activité choisie.
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
        return AppBar.onOptionsItemSelected(item, this) || super.onOptionsItemSelected(item);
    }


}
