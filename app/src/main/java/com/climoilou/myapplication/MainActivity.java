package com.climoilou.myapplication;

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

import Donnee.Data;

public class MainActivity extends AppCompatActivity {



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
        return AppBar.onCreateOptionsMenu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return AppBar.onOptionsItemSelected(item, this) || super.onOptionsItemSelected(item);
    }


}