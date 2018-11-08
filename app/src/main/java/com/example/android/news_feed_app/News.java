package com.example.android.news_feed_app;

public class News {
    private String mArticleName;
    private String mArticleSection;
    private String mPublishDate;
    private String mArticleUri;

    public News(String articleName, String articleSection, String publishDate, String articleUri) {
        mArticleName = articleName;
        mArticleSection = articleSection;
        mPublishDate = publishDate;
        mArticleUri = articleUri;
    }

    public String getArticleName() {
        return mArticleName;
    }

    public String getArticleSection() {
        return mArticleSection;
    }

    public String getPublishDate() {
        return mPublishDate;
    }

    public String getArticleUri() {
        return mArticleUri;
    }

}
