package AdaptatersView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.climoilou.myapplication.R;

import java.util.zip.Inflater;

public class PartenaireAdapter extends BaseAdapter {

    Context context;
    int[] partenaireImage;
    LayoutInflater inflater;

    public PartenaireAdapter(Context context, int[] partenaireImage) {
        this.context = context;
        this.partenaireImage = partenaireImage;
    }

    @Override
    public int getCount() {
        return partenaireImage.length;
    }

    @Override
    public Integer getItem(int postion) {
        return partenaireImage[postion];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.content_partenaires, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imagePartenaire);
        imageView.setImageResource(getItem(position));

        return convertView;
    }
}
