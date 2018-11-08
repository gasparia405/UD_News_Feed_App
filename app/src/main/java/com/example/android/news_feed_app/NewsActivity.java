package com.example.android.news_feed_app;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private NewsAdapter mAdapter;

    private static final String GUARDIAN_API = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);

        ListView listView = findViewById(R.id.list_view);

        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNewsArticle = mAdapter.getItem(position);

                Uri newsArticleUri = Uri.parse(currentNewsArticle.getArticleUri());

                Intent webPageIntent = new Intent(Intent.ACTION_VIEW, newsArticleUri);
                startActivity(webPageIntent);
            }
        });

        ArticleAsyncTask task = new ArticleAsyncTask();
        task.execute(GUARDIAN_API);
    }

    private class ArticleAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {
        @Override
        protected ArrayList<News> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) return null;

            ArrayList<News> result = QueryUtils.extractNewsArticles(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            mAdapter.clear();

            if (news != null && !news.isEmpty()){mAdapter.addAll(news);}
        }
    }
}
