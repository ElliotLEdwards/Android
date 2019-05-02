package com.example.android_project;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ArticleAsyncTaskParse extends AsyncTask<Object, Void, Void> {
    @Override
    protected Void doInBackground(Object... objects) {
        try{

            String dateDebut = (String) objects[0];
            String dateFin = (String) objects[1];
            planets = (ArrayList<Planet>) objects[2];
            adapter = (PlanetAdapter) objects[3];
            String lien ="https://api.nasa.gov/planetary/apod?start_date="+ dateDebut +"&end_date="+ dateFin +"&api_key=tTVoD66Uy47eCyMdHv94Q1EDsw2dPKOsjAO6vr2G";
            URL url = new URL(lien);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String result = InputStreamToString(inputStream);
            JSONArray array = new JSONArray(result);



            for(int i=0; i<array.length(); i++)
            {
                JSONObject obj = new JSONObject(array.getString(i));

                String title = obj.getString("title");


                Planet planet = new Planet();

                planet.setTitle(obj.getString("title"));
                planet.setDate(obj.getString("date"));
                planet.setDescription(obj.getString("explanation"));
                planet.setImageUrl(obj.getString("url"));
                planet.setMediaType(obj.getString("media_type"));

                planets.add(planet);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
