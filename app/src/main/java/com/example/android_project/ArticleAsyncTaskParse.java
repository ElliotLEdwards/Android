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
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();



        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
