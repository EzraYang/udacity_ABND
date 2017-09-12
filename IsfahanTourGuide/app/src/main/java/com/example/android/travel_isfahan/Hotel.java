package com.example.android.travel_isfahan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Hotel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);


        ArrayList<Attraction> hotels = new ArrayList<Attraction>();

        hotels.add(new Attraction(R.drawable.hot1, R.string.hot1_name, R.string.hot1_loc));
        hotels.add(new Attraction(R.drawable.hot2, R.string.hot2_name, R.string.hot2_loc));
        hotels.add(new Attraction(R.drawable.hot3, R.string.hot3_name, R.string.hot3_loc));
        hotels.add(new Attraction(R.drawable.hot4, R.string.hot4_name, R.string.hot4_loc));
        hotels.add(new Attraction(R.drawable.hot5, R.string.hot5_name, R.string.hot5_loc));
        hotels.add(new Attraction(R.drawable.hot6, R.string.hot6_name, R.string.hot6_loc));


        AttractionAdapter attractionAdapter = new AttractionAdapter(this, hotels);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(attractionAdapter);
    }
}
