package com.climoilou.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.climoilou.myapplication.databinding.ActivityPartenairesBinding;

import AdaptatersView.PartenaireAdapter;
import Donnee.Data;

public class PartenairesActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPartenairesBinding binding;


    int[] images = {
            R.drawable.ig,
            R.drawable.google,
            R.drawable.nike,
            R.drawable.ig,
            R.drawable.spotify,
            R.drawable.nike,
            R.drawable.google,
            R.drawable.ig,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partenaires);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.partenaireView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        GridView gridView = findViewById(R.id.partenaireGrid);
        PartenaireAdapter adapter = new PartenaireAdapter(this, images);
        gridView.setAdapter(adapter);
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