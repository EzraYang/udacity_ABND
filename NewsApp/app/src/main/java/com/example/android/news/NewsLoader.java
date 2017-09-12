package com.example.android.news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by EzraYang on 1/3/17.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrlString;

    public NewsLoader(Context context, String url){
        super(context);
        mUrlString = url;
    }

    protected void onStartLoading(){
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {

        if (mUrlString == null){
            return null;
        }

        List<News> newsList = QueryUtils.fetchNewsData(mUrlString);

        return newsList;
    }


}
