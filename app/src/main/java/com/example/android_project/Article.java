package com.example.android_project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Article extends AppCompatActivity {

    public String title;
    public String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_article);
        Button button= (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText titleInput = (EditText) findViewById(R.id.titleInput);
                EditText descriptionInput = (EditText) findViewById(R.id.descriptionInput);

                Toast.makeText(getApplicationContext(),titleInput.getText() + " + " + descriptionInput.getText(),Toast.LENGTH_SHORT).show();
            }
        });

    }



}
