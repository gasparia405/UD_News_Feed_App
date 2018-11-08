package com.example.android.news_feed_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        News currentArticle = getItem(position);

        TextView articleName = listItemView.findViewById(R.id.article_name);
        articleName.setText(currentArticle.getArticleName());

        TextView articleSection = listItemView.findViewById(R.id.article_section);
        articleSection.setText(currentArticle.getArticleSection());

        TextView publishDate = listItemView.findViewById(R.id.publish_date);
        publishDate.setText(currentArticle.getPublishDate());

        return listItemView;
    }
}
