package com.example.android.news;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private NewsAdapter mAdapter;

    private EditText mTopicField;

    private int loaderId = 0;

    private String mTopic = "";

    private TextView mEmptyTextView;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ListView mListView = (ListView) findViewById(R.id.list);
        mAdapter = new NewsAdapter(NewsActivity.this, new ArrayList<News>());
        mListView.setAdapter(mAdapter);

        mEmptyTextView = (TextView) findViewById(R.id.text_when_empty);
        mListView.setEmptyView(mEmptyTextView);

        mTopicField = (EditText) findViewById(R.id.topic_field) ;

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = (News) mAdapter.getItem(position);

                Uri newsUri = Uri.parse(currentNews.getmUrl());

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(browserIntent);
            }
        });

        ImageView searchBtn = (ImageView) findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if user has entered a new topic
                // if so (and everything else works fine)
                // init a new loader with loader id increment by 1
                if (mTopicField.getText().toString() != mTopic){
                    // clear up mAdapter leads to clear listView
                    mAdapter.clear();
                    // set mEmptyTextView an empty String
                    // in case it was showing topic_needed or no_conn warning previously
                    mEmptyTextView.setText("");

                    // check the internet connection on the device
                    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()){
                        // check if user has enter a key word
                        if (!mTopicField.getText().toString().isEmpty() ){
                            // set mTopic to the current input
                            mTopic = mTopicField.getText().toString();

                            // since mTopic is different from the last one
                            // have to get a new loader to load new data
                            loaderId += 1;
                            getLoaderManager().initLoader(loaderId, null, NewsActivity.this);

                        } else {
                            mEmptyTextView.setText(R.string.topic_needed);
                        }
                    } else {
                        mEmptyTextView.setText(R.string.no_conn);
                    }
                } else {
                    // do nothing when current topic is the same as last topic
                }
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        progressDialog = new ProgressDialog(NewsActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String mBaseUrlString = "http://content.guardianapis.com/search?api-key=test";
        Uri baseUrl = Uri.parse(mBaseUrlString);
        Uri.Builder urlBuilder = baseUrl.buildUpon();
        urlBuilder.appendQueryParameter("q", mTopic);

        String queryUrl = urlBuilder.toString();

        return new NewsLoader(this, queryUrl);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {

        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }

        if ( data != null & !data.isEmpty()){
            mAdapter.addAll(data);
        } else {
            mEmptyTextView.setText(R.string.no_data);
        }

    }
}
