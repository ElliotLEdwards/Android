package com.example.android_project;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class GetArticleAsyncTask extends AsyncTask<Object, Void, Void> {

    private ArticleAdapter adapter;
    private ArrayList<ArticleDTO> articles;

    @Override
    protected Void doInBackground(Object... objects) {
        try{
            adapter = (ArticleAdapter) objects[0];
            articles = (ArrayList<ArticleDTO>) objects[1];
            articles.clear();
            String lien = "https://api.getpostman.com/collections";
            String apiKey = "3efad7e576c34c65937e742970ac9f38";
            URL url = new URL(lien);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String result = InputStreamToString(inputStream);
            JSONObject object = new JSONObject(result);
            JSONArray array = object.getJSONArray("collections");



                for(int i = 0 ; i < array.length() ; i++){
                    ArticleDTO article = new ArticleDTO();
                    article.setName(array.getJSONObject(i).getString("name"));
                    article.setUid((array.getJSONObject(i).getString("uid")));
                    articles.add(article);
                }


    } catch (Exception e) {
        e.printStackTrace();
    }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.notifyDataSetChanged();
    }

    public static String InputStreamToString(InputStream in, int bufSize) {
        final StringBuilder out = new StringBuilder();
        final byte[] buffer = new byte[bufSize];
        try {
            for (int ctr; (ctr = in.read(buffer)) != -1; ) {
                out.append(new String(buffer, 0, ctr));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot convert stream to string", e);
        }

        return out.toString();
    }

    public static String InputStreamToString(InputStream in) {

        return InputStreamToString(in, 1024);
    }

}
