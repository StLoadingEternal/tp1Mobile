package com.climoilou.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import Donnee.Data;

public class SondageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sondage);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        mettreAjourVue(calculerMoyenne(this));

        findViewById(R.id.buttonEnvoyer).setOnClickListener(view -> {

            EditText note = findViewById(R.id.noteValue);
            sauvegarderMoyenne(this, Double.parseDouble(note.getText().toString()));
            double moyenne = calculerMoyenne(this);

            mettreAjourVue(moyenne);

        });
    }

    private void mettreAjourVue(double moyenne){
        TextView textView = findViewById(R.id.moyenneValue);
        textView.setText(String.valueOf(moyenne));
    }
    private void sauvegarderMoyenne(Context context, double moyenne) {
        try {
            FileOutputStream fos = context.openFileOutput("notes.txt", Context.MODE_APPEND);
            String ligne = moyenne + "\n";
            fos.write(ligne.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private double calculerMoyenne(Context context) {
        double moyenne = 0;
        int count = 0;
        try {
            FileInputStream fis = context.openFileInput("notes.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            double somme = 0;

            while ((line = reader.readLine()) != null) {
                double moyenneLu = Double.parseDouble(line.trim());
                somme += moyenneLu;
                count++;
            }
            reader.close();

            if (count > 0) {
                moyenne = somme / count;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return moyenne;
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