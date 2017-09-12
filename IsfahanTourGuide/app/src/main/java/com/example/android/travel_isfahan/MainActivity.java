package com.example.android.travel_isfahan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up sight intent
        ImageView sightEntrance = (ImageView) findViewById(R.id.sight);
        sightEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sightsIntent = new Intent(MainActivity.this, Sight.class);
                startActivity(sightsIntent);
            }
        });

        // set up hotel intent
        ImageView hotelEntrance = (ImageView) findViewById(R.id.hotel);
        hotelEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hotelIntent = new Intent(MainActivity.this, Hotel.class);
                startActivity(hotelIntent);
            }
        });

        // set up shopping intent
        ImageView shoppingEntrance = (ImageView) findViewById(R.id.shopping);
        shoppingEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shoppingIntent = new Intent(MainActivity.this, Shopping.class);
                startActivity(shoppingIntent);
            }
        });

        // set up restaurant intent
        ImageView restaurantEntrance = (ImageView) findViewById(R.id.restaurant);
        restaurantEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restaurantIntent = new Intent(MainActivity.this, Restaurant.class);
                startActivity(restaurantIntent);
            }
        });



    }
}
