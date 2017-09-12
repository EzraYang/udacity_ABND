package com.example.android.booklistingapp;

import android.text.TextUtils;
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
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private final static String LOG_TAG = "QueryUtils";

    private QueryUtils(){

    }

    public static List<Book> fetchBookData(String stringURL){
        URL urlObj = createUrl(stringURL);

        String jsonResponse = makeHTTPRequest(urlObj);

        List<Book> books = extractBookData(jsonResponse);

        return books;

    }

    private static URL createUrl(String stringURL){
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHTTPRequest(URL urlObj) {
        String jsonResponse = "";

        if (urlObj == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) urlObj.openConnection();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem in connection", e);
        }


        urlConnection.setReadTimeout(10000);

        urlConnection.setConnectTimeout(15000);

        try {
            urlConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        try {
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else {
                Log.e(QueryUtils.LOG_TAG, "getting response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(LOG_TAG, "jsonresponse is " + jsonResponse);
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream){
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }

    private static List<Book> extractBookData(String jsonString){
        int i;
        int j;

        if (TextUtils.isEmpty(jsonString)){
            return null;
        }

        List<Book> books = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            // parse jsonResponse only when "items" exists, else return null
            if (jsonObject.has("items")){
                JSONArray items = jsonObject.getJSONArray("items");

                // looping through all jsonObj in items
                for (j = 0; j < items.length(); ++j){
                    JSONObject bookInfo = items.getJSONObject(j);
                    JSONObject volumeInfo = bookInfo.getJSONObject("volumeInfo");
                    String title = volumeInfo.getString("title");

                    String authorString = "";
                    if (volumeInfo.has("authors")){
                        JSONArray authors = volumeInfo.getJSONArray("authors");
                        StringBuilder authorStringBuilder = new StringBuilder();
                        for (i = 0; i < authors.length(); ++i){
                            try {
                                authorStringBuilder.append(authors.getString(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            authorStringBuilder.append("/");
                        }
                        authorString = authorStringBuilder.toString();
                        // remove the last "/" in author String
                        authorString = authorString.substring(0, authorString.length() - 1);
                    } else {
                        authorString = "-";
                    }

                    // if there's no subtitle, set the subtitle field to an empty String
                    String subtile = null;
                    try {
                        subtile = volumeInfo.getString("subtitle");
                    } catch (JSONException e) {
                        subtile = "-";
                    }

                    Log.i(LOG_TAG, "we have the " + j + "th book");
                    books.add(new Book(title, subtile, authorString));
                }
            } else {
                return null;
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem extracting Json response", e);
        }

        return books;
    }
}


















