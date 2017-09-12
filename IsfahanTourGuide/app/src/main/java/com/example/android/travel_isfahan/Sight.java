package com.example.android.travel_isfahan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Sight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);


        ArrayList<Attraction> sights = new ArrayList<Attraction>();

        sights.add(new Attraction(R.drawable.sig1, R.string.sig1_name, R.string.sig1_loc));
        sights.add(new Attraction(R.drawable.sig2, R.string.sig2_name, R.string.sig2_loc));
        sights.add(new Attraction(R.drawable.sig3, R.string.sig3_name, R.string.sig3_loc));
        sights.add(new Attraction(R.drawable.sig4, R.string.sig4_name, R.string.sig4_loc));
        sights.add(new Attraction(R.drawable.sig5, R.string.sig5_name, R.string.sig5_loc));
        sights.add(new Attraction(R.drawable.sig6, R.string.sig6_name, R.string.sig6_loc));
        sights.add(new Attraction(R.drawable.sig7, R.string.sig7_name, R.string.sig7_loc));


        AttractionAdapter sightsAdapter = new AttractionAdapter(this, sights);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(sightsAdapter);
    }
}
