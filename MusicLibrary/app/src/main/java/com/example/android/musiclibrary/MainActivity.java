package com.example.android.musiclibrary;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView favorite = (TextView) findViewById(R.id.home_favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent favoriteIntent = new Intent(MainActivity.this, FavoriteList.class);
                startActivity(favoriteIntent);
            }
        });

        TextView recent = (TextView) findViewById(R.id.home_recent);
        recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recentIntent = new Intent(MainActivity.this, RecentList.class);
                startActivity(recentIntent);
            }
        });

        ImageView pianoDance = (ImageView) findViewById(R.id.album_piano_dance);
        pianoDance.setOnClickListener(new albumClickListener());

        ImageView bajaCafe = (ImageView) findViewById(R.id.album_baja_cafe);
        bajaCafe.setOnClickListener(new albumClickListener());

        ImageView pieces = (ImageView) findViewById(R.id.album_pieces);
        pieces.setOnClickListener(new albumClickListener());

        ImageView trois = (ImageView) findViewById(R.id.album_trois);
        trois.setOnClickListener(new albumClickListener());
/**
        Button playFavorite = (Button) findViewById(R.id.play_favorite);
        playFavorite.setOnClickListener(new playClickListener());

        Button playRecent = (Button) findViewById(R.id.play_recent);
        playRecent.setOnClickListener(new playClickListener());

        Button playAlbum = (Button) findViewById(R.id.play_album);
        playAlbum.setOnClickListener(new playClickListener());
 */
    }

    public class albumClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent albumIntent = new Intent(MainActivity.this, AlbumInfo.class);
            startActivity(albumIntent);
        }
    }

    /**
    public class playClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent playIntent = new Intent(MainActivity.this, Play.class);
            startActivity(playIntent);
        }
    }
     */

}
