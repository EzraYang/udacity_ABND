package com.example.android.travel_isfahan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Restaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<Attraction> res = new ArrayList<Attraction>();

        res.add(new Attraction(R.drawable.res1, R.string.res1_name, R.string.res1_loc));
        res.add(new Attraction(R.drawable.res2, R.string.res2_name, R.string.res2_loc));
        res.add(new Attraction(R.drawable.res3, R.string.res3_name, R.string.res3_loc));
        res.add(new Attraction(R.drawable.res4, R.string.res4_name, R.string.res4_loc));
        res.add(new Attraction(R.drawable.res5, R.string.res5_name, R.string.res5_loc));
        res.add(new Attraction(R.drawable.res6, R.string.res6_name, R.string.res6_loc));

        AttractionAdapter sightsAdapter = new AttractionAdapter(this, res);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(sightsAdapter);
    }
}
