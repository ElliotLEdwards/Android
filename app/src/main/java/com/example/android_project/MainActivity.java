package com.example.android_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Planet> planets;
    private AsyncTaskParse AsyncTask;
    private PlanetAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        planets = new ArrayList<Planet>();
        adapter = new PlanetAdapter(this, planets);

        InternetConnection i = new InternetConnection();
        if (i.isConnectedInternet(MainActivity.this)) {
            Toast.makeText(MainActivity.this, "Connecté", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Pas de connexion à internet", Toast.LENGTH_SHORT).show();
        }

        final ListView listeView = (ListView) findViewById(R.id.list);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        listeView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        AsyncTask = (AsyncTaskParse) new AsyncTaskParse().execute("2018-06-04", "2018-06-06", planets, adapter);

        listeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //LinearLayout layout = new LinearLayout();
                //ImageView imageView = layout.findViewById(R.id.iv_sample);


                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle(planets.get(i).getTitle());
                //adb.setView(layout);

                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View imageView = factory.inflate(R.layout.sample, null);
                adb.setView(imageView);

                if(planets.get(i).getMediaType().equals("image"))
                {
                    new DownloadImageTask((ImageView) imageView.findViewById(R.id.iv_sample)).execute(planets.get(i).getImageUrl());
                }
                else
                {
                    String idVideo = planets.get(i).getImageUrl();
                    Pattern pattern = Pattern.compile("(([0-9a-zA-Z-])){11}");
                    Matcher matcher = pattern.matcher(planets.get(i).getImageUrl());

                    if (matcher.find())
                    {
                        String url = "https://img.youtube.com/vi/" + matcher.group(0) + "/default.jpg";
                        new DownloadImageTask((ImageView) imageView.findViewById(R.id.iv_sample)).execute(url);
                        Log.i("toto", url);
                    }
                }
                adb.setMessage(planets.get(i).getLongDescription());
                adb.setView(imageView);
                adb.setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                adb.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.ajouter){
            Intent myIntent = new Intent(MainActivity.this, Article.class);
            MainActivity.this.startActivity(myIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
