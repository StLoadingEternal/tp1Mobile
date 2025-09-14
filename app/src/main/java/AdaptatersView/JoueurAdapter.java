package AdaptatersView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import aModels.Joueur;
import com.climoilou.myapplication.R;

public class JoueurAdapter extends BaseAdapter {


    Context context;
    Joueur[] players;
    LayoutInflater inflater;

    public JoueurAdapter(Context context, Joueur[] players) {
        this.context = context;
        this.players = players;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return players.length;
    }

    @Override
    public Object getItem(int position) {
        return players[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.joueurs_listview, null);
        TextView nom = (TextView) convertView.findViewById(R.id.nom);
        TextView prenom = (TextView) convertView.findViewById(R.id.prenom);
        TextView email = (TextView) convertView.findViewById(R.id.email);
        nom.setText(players[position].getNom());
        prenom.setText(players[position].getPrenom());
        email.setText(players[position].getAddresseCourriel());
        return convertView;
    }
}
