package com.example.android.news_feed_app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();

    private QueryUtils() {

    }

    public static ArrayList<News> extractNewsArticles(String requestUrl) {
        ArrayList<News> newsArticles = new ArrayList<>();

        String jsonResponse;

        try {

            URL url = createUrl(requestUrl);

            jsonResponse = makeHttpRequest(url);

            JSONObject jsonObject = new JSONObject(jsonResponse).getJSONObject("response");
            JSONArray results = jsonObject.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject currentNewsArticle = results.getJSONObject(i);

                JSONArray tags = currentNewsArticle.getJSONArray("tags");

                ArrayList<String> authors = new ArrayList<>();
                for (int j = 0; j < tags.length(); j++) {
                    JSONObject tagsObject = tags.getJSONObject(j);

                    authors.add(tagsObject.getString("webTitle"));
                }

                String contributors;
                int numContributors = authors.size();

                if (authors.size() > 1) {
                    contributors = authors.get(0);
                    for (int j = 1; j < authors.size(); j++) {
                        contributors += " and " + authors.get(j);
                    }
                } else if (authors.size() == 1) {
                    contributors = authors.get(0);
                } else {
                    contributors = "No Authors listed for this article.";
                }

                String articleTitle = currentNewsArticle.getString("webTitle");
                String articleSection = currentNewsArticle.getString("sectionName");
                String publishDate = currentNewsArticle.getString("webPublicationDate");
                String articleUri = currentNewsArticle.getString("webUrl");

                newsArticles.add(new News(articleTitle, articleSection, contributors, publishDate, articleUri));

            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the JSON results.", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        return newsArticles;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL", e);
        }

        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // if request was successful
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem resolving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }
}
