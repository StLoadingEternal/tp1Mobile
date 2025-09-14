package AdaptatersView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.climoilou.myapplication.R;

import aModels.Activite;
import aModels.Joueur;

public class CalendrierAdaptater extends BaseAdapter {

    Context context;
    Activite[] activities;
    LayoutInflater inflater;

    public CalendrierAdaptater(Context context, Activite[] activities) {
        this.context = context;
        this.activities = activities;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return activities.length;
    }

    @Override
    public Object getItem(int position) {
        return activities[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.calendrier_listview, null);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView titre = (TextView) convertView.findViewById(R.id.titre);
        TextView lieu = (TextView) convertView.findViewById(R.id.lieu);
        TextView stade = (TextView) convertView.findViewById(R.id.stade);
        TextView participations = (TextView) convertView.findViewById(R.id.participations);

        date.setText(activities[position].getDate());
        titre.setText(activities[position].getTitre());
        lieu.setText(activities[position].getLieu());
        stade.setText(activities[position].getStade().name());
        participations.setText(String.valueOf(activities[position].getParticipants().size()));

        return convertView;
    }
}
