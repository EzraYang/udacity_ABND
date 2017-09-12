package com.example.android.news;

/**
 * Created by EzraYang on 1/3/17.
 */

public class News {
    String mTitle;
    String mSection;
    String mUrl;

    public News(String title, String section, String url){
        mTitle = title;
        mSection = section;
        mUrl = url;
    }

    public String getmSection() {
        return mSection;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }
}
