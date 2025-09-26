package com.climoilou.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

public class AppBar {

    public static boolean onCreateOptionsMenu(Activity activity, Menu menu) {
        activity.getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public static boolean onOptionsItemSelected(@NonNull MenuItem item, Activity activity,
                                                Object joueurs, Object activites) {

        int id = item.getItemId();


        if (id == R.id.joueurs_item) {
            Intent intent = new Intent(activity, JoueurActivity.class);
            intent.putExtra("joueurs_tab", (java.io.Serializable) joueurs);
            activity.startActivity(intent);
            return true;
        } else if (id == R.id.presence_item) {
            Intent intent = new Intent(activity, PresenceActivity.class);
            intent.putExtra("joueurs_tab", (java.io.Serializable) joueurs);
            intent.putExtra("activites_tab", (java.io.Serializable) activites);
            activity.startActivity(intent);
            return true;
        } else if (id == R.id.calendrier_item) {
            Intent intent = new Intent(activity, CalendrierActivity.class);
            intent.putExtra("activites_tab", (java.io.Serializable) activites);
            activity.startActivity(intent);
            return true;
        } else if (id == R.id.partenaires_item) {
            Intent intent = new Intent(activity, PartenairesActivity.class);
            activity.startActivity(intent);
            return true;
        } else if (id == R.id.sondage_item) {
            Intent intent = new Intent(activity, SondageActivity.class);
            activity.startActivity(intent);
            return true;
        } else {
            return false;
        }
    }
}
