package com.example.android.news_feed_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);

        final ArrayList<News> news = new ArrayList<>();
        news.add(new News("Red Sox win Pennant","Sports", "Oct 04, 2018", "" ));
        news.add(new News("White Sox win Pennant","Sports", "Oct 04, 2017", "" ));
        news.add(new News("Cubs win Pennant","Sports", "Oct 04, 2016", "" ));
        news.add(new News("Indians Sox win Pennant","Sports", "Oct 04, 2015", "" ));
        news.add(new News("Brewers win Pennant","Sports", "Oct 04, 2014", "" ));

        ListView listView = findViewById(R.id.list_view);

        NewsAdapter newsAdapter = new NewsAdapter(this, news);

        listView.setAdapter(newsAdapter);
    }
}
