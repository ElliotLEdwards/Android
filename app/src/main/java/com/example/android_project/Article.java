package com.example.android_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Article extends AppCompatActivity {

    private String title;
    private String description;
    private ArticleAsyncTaskParse AsyncTask;
    private ArticleAdapter adapter;
    private ArrayList<ArticleDTO> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_article);
        Button button = (Button) findViewById(R.id.addButton);
        articles = new ArrayList<ArticleDTO>();
        final ListView listeView = (ListView) findViewById(R.id.listArticle);
        adapter = new ArticleAdapter(this, articles);
        listeView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        GetArticleAsyncTask as = (GetArticleAsyncTask) new GetArticleAsyncTask().execute(adapter, articles);

        listeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                DeleteArticleAsyncTask as = (DeleteArticleAsyncTask) new DeleteArticleAsyncTask().execute(adapter, articles, articles.get(i).getUid(), articles.get(i));
                AlertDialog.Builder adb = new AlertDialog.Builder(Article.this);
                adb.setTitle("Delete Area");
                adb.setMessage("Vous avez supprimé l'article !");
                adb.setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                adb.show();


            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText titleInput = (EditText) findViewById(R.id.titleInput);
                EditText descriptionInput = (EditText) findViewById(R.id.descriptionInput);

                AsyncTask = (ArticleAsyncTaskParse) new ArticleAsyncTaskParse().execute(titleInput.getText(), descriptionInput.getText());
                GetArticleAsyncTask as = (GetArticleAsyncTask) new GetArticleAsyncTask().execute(adapter, articles);
                Toast.makeText(getApplicationContext(), titleInput.getText() + " + " + descriptionInput.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
