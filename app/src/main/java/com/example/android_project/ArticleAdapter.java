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

public class ArticleAdapter extends BaseAdapter {
    private List<ArticleDTO> liste_article;
    private Context mContext;
    private LayoutInflater mInflater;

    public ArticleAdapter (Context context, List<ArticleDTO> list)
    {
        this.mContext = context;
        this.liste_article = list;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return this.liste_article.size();
    }

    @Override
    public Object getItem(int i) {
        return this.liste_article.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layoutItem;
        if(view == null){
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.list_article, viewGroup, false);
        } else {
            layoutItem = (LinearLayout) view;
        }


        TextView name = (TextView) layoutItem.findViewById(R.id.name);

        name.setText(liste_article.get(i).getName());

        return layoutItem;
    }
}
