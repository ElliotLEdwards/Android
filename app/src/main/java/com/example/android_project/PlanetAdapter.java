package com.example.android_project;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PlanetAdapter extends BaseAdapter {

    private List<Planet> liste_planet;
    private Context mContext;
    private LayoutInflater mInflater;

    public PlanetAdapter (Context context, List<Planet> list)
    {
        this.mContext = context;
        this.liste_planet = list;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return this.liste_planet.size();
    }

    @Override
    public Object getItem(int i) {
        return this.liste_planet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layoutItem;
        if(view == null){
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.list_item, viewGroup, false);
        } else {
            layoutItem = (LinearLayout) view;
        }

        ImageView image = (ImageView) layoutItem.findViewById(R.id.iv_sample);
        TextView title = (TextView) layoutItem.findViewById(R.id.title);
        title.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView date = (TextView) layoutItem.findViewById(R.id.date);
        date.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView description = (TextView) layoutItem.findViewById(R.id.description);
        description.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);


        if(liste_planet.get(i).getMediaType().equals("image"))
        {
            new DownloadImageTask((ImageView) layoutItem.findViewById(R.id.iv_sample)).execute(liste_planet.get(i).getImageUrl());
        }
        else
        {
            Log.i("media", liste_planet.get(i).getMediaType());
            String idVideo = liste_planet.get(i).getImageUrl();
            Log.i("baseurl", idVideo);



            Pattern pattern = Pattern.compile("(([0-9a-zA-Z-])){11}");
            Matcher matcher = pattern.matcher(liste_planet.get(i).getImageUrl());
            Log.i("matcher", matcher.toString());
            if (matcher.find())
            {
               String url = "https://img.youtube.com/vi/" + matcher.group(0) + "/default.jpg";
                new DownloadImageTask((ImageView) layoutItem.findViewById(R.id.iv_sample)).execute(url);
                Log.i("toto", url);
            }


        }


        //image.set(liste_planet.get(i).getImageUrl());
        title.setText(liste_planet.get(i).getTitle());
        date.setText(liste_planet.get(i).getDate());
        description.setText(liste_planet.get(i).getShortDescription());

        return layoutItem;
    }
}
