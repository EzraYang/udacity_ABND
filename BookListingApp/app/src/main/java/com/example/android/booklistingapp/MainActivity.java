package com.example.android.booklistingapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  static final String LOG_TAG = MainActivity.class.getName();


    private final static String mBaseUrl = "https://www.googleapis.com/books/v1/volumes?maxResults=10";

    public static BookAdapter mAdapter;

    public static String queryUrl = "";

    private ListView bookListView;

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.book_list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_txt);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        Button search = (Button) findViewById(R.id.search_btn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if there's already content before clicking,
                // clear the listView
                if (bookListView != null){
                    bookListView.setAdapter(null);
                }
                // check the internet connection on the device
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()){
                    // find out what user's key search word
                    EditText inputField = (EditText) findViewById(R.id.input_field);
                    String userInput = inputField.getText().toString();

                    // check if user has enter a key word
                    if (!userInput.isEmpty()){
                        // building costomized url base on user key word
                        Uri baseURL = Uri.parse(mBaseUrl);
                        Uri.Builder urlBuilder = baseURL.buildUpon();
                        urlBuilder.appendQueryParameter("q", userInput);

                        queryUrl = urlBuilder.toString();
                        Log.i(LOG_TAG, "queryUrl is " + queryUrl);

                        // kick off FetchBookData.execute()
                        FetchBookData kickOffFetch = new FetchBookData();
                        Log.i(LOG_TAG, "button click kick off asyncTask");
                        kickOffFetch.execute();
                    } else {
                        mEmptyStateTextView.setText(R.string.no_key_word);
                    }
                } else {
                    mEmptyStateTextView.setText(R.string.no_conn);
                }
            }
        });
    }

    // here's the code I'm struggling with,
//     I've get my Book class implement parcelable interface,
//     however couldn't figure out how to utilize the parcelable.
//     Seems that the second param in outState.putParcelableArrayList("key", books);
//     should be the return value of AsyncTask doInBackground,
//     but don't know how to pass that data from onPostExecute to onSaveInstanceState.
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList("key", books);
//    }

    public void updateUi (List<Book> books){
        bookListView = (ListView) findViewById(R.id.book_list);
        mAdapter = new BookAdapter(MainActivity.this, books);
        bookListView.setAdapter(mAdapter);
    }

    private class FetchBookData extends AsyncTask<String , Void , List<Book>> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected List<Book> doInBackground(String... strings) {

            List<Book> books = QueryUtils.fetchBookData(queryUrl);

            Log.i(MainActivity.LOG_TAG, "AsyncTask has done its work in Background");

            return books;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            if (books != null && !books.isEmpty()){
                updateUi(books);
            } else {
                mEmptyStateTextView.setText(R.string.no_data);
            }
        }
    }
}


