package com.example.android.news;

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
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EzraYang on 1/3/17.
 */

public class QueryUtils {
    private QueryUtils(){
    }

    public static List<News> fetchNewsData(String urlString){
        URL urlObj = createUrl(urlString);

        String jsonResponse = makeHTTPRequest(urlObj);

        List<News> newsList = extractNewsData(jsonResponse);

        return newsList;
    }

    private static URL createUrl(String urlString){
        URL urlObj = null;
        try {
            urlObj = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return urlObj;
    }

    private static String makeHTTPRequest(URL urlObj){
        String jsonResponse = "";

        if (urlObj == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) urlObj.openConnection();

            urlConnection.setReadTimeout(10000);

            urlConnection.setConnectTimeout(15000);

            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else {
                Log.e("makeHttpRequest", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("makeHttpRequest", "Problem retrieving the earthquake JSON result", e);
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream rawBinaryResponseCode){
        StringBuilder output = new StringBuilder();
        if (rawBinaryResponseCode != null){
            InputStreamReader inputStreamReader = new InputStreamReader(rawBinaryResponseCode, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = null;
            try {
                line = reader.readLine();
                while (line != null){
                    output.append(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                Log.e("readFromStream", "Problem reading line from Json response", e);
            }
        }
        return output.toString();
    }

    public static List<News> extractNewsData(String jsonResponse){

        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }

        List<News> newsList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            JSONObject response = jsonObject.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++){
                JSONObject aPieceOfNews = results.getJSONObject(i);
                String title = aPieceOfNews.getString("webTitle");
                String section = aPieceOfNews.getString("sectionName");
                String url = aPieceOfNews.getString("webUrl");

                newsList.add(new News(title, section, url));
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing jsonresponse", e);
        }

        return newsList;
    }


}
