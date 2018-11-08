package com.example.android.news_feed_app;

import android.content.Context;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader {

    /** Query URL */
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);

        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Create a fake list of earthquake locations.
        ArrayList<News> earthquakes = QueryUtils.extractNewsArticles(mUrl);

        return earthquakes;
    }
}
