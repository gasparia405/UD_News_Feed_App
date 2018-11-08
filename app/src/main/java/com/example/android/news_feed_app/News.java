package com.example.android.news_feed_app;

public class News {
    private String mArticleName;
    private String mPublishDate;
    private String mArticleUri;
    private String mAuthorName;

    public News(String articleName, String publishDate, String articleUri, String authorName) {
        mArticleName = articleName;
        mPublishDate = publishDate;
        mArticleUri = articleUri;
        mAuthorName = authorName;
    }

    public String getArticleName() {
        return mArticleName;
    }

    public String getPublishDate() {
        return mPublishDate;
    }

    public String getArticleUri() {
        return mArticleUri;
    }

    public String getAuthorName() {
        return mAuthorName;
    }
}
