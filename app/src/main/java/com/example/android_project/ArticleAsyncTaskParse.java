package com.example.android_project;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.android_project.AsyncTaskParse.InputStreamToString;

public class ArticleAsyncTaskParse extends AsyncTask<Object, Void, Void> {
    @Override
    protected Void doInBackground(Object... objects) {
        try {
            String title = objects[0].toString();
            String description = objects[1].toString();
            if (title.length() > 0 && description.length() > 0) {
                String lien = "https://api.getpostman.com/collections";
                String apiKey = "3efad7e576c34c65937e742970ac9f38";
                URL url = new URL(lien);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("X-Api-Key", apiKey);
                connection.setRequestProperty("Content-Type", "application/json");
                DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                String body = "{\"collection\":{\"info\": {\"name\":\"" + title + "\"," +
                        "\"description\":\"" + description + "\",\"schema\":\"" +
                        "https://schema.getpostman.com/json/collection/v2.1.0/collection.json\"}," +
                        "\"item\":[{\"name\":\"POST_TEST\",\"item\":[{\"request\":{}}]}]}}";
                os.writeBytes(body);
                os.flush();
                os.close();
                connection.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                StringBuilder sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                Log.d("Retour api post : ", sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
