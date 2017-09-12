package com.example.android.travel_isfahan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Shopping extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);


        ArrayList<Attraction> shops = new ArrayList<Attraction>();

        shops.add(new Attraction(R.drawable.shop1, R.string.shop1_name, R.string.shop1_loc));
        shops.add(new Attraction(R.drawable.shop2, R.string.shop2_name, R.string.shop2_loc));
        shops.add(new Attraction(R.drawable.shop3, R.string.shop3_name, R.string.shop3_loc));
        shops.add(new Attraction(R.drawable.shop4, R.string.shop4_name, R.string.shop4_loc));
        shops.add(new Attraction(R.drawable.shop5, R.string.shop5_name, R.string.shop5_loc));
        shops.add(new Attraction(R.drawable.shop6, R.string.shop6_name, R.string.shop6_loc));

        AttractionAdapter sightsAdapter = new AttractionAdapter(this, shops);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(sightsAdapter);
    }
}
