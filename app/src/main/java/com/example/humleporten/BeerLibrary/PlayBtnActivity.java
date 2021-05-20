package com.example.humleporten.BeerLibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.example.humleporten.MainScreen.BeerActivity;
import com.example.humleporten.MainScreen.MainActivity;
import com.example.humleporten.R;

public class PlayBtnActivity extends AppCompatActivity{

    Button startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        startBtn = (Button) findViewById(R.id.btnPlayActivyty);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlayBtnActivity.this, VideoPlayer.class));
            }
        });
    }
}
/*
public class PlayBtnActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bottom);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()){
                    case R.id.beerLibrarybt:
                        startActivity(new Intent(getApplicationContext(), BeerActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.beerBrewing:
                        return true;
                }
                return false;
            }
        });

        Button btnPlayVideo = (Button) findViewById(R.id.btnPlayVideo);
        Button btnVideoPlayList = (Button) findViewById(R.id.btnVideoPlayList);

        btnPlayVideo.setOnClickListener(this);
        btnVideoPlayList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.btnPlayVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent(this, ExerciseLibraryActivity.GOOGLE_API_KEY, ExerciseLibraryActivity.YOUTUBE_VIDEO_ID);
                break;

            case R.id.btnVideoPlayList:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, ExerciseLibraryActivity.GOOGLE_API_KEY, ExerciseLibraryActivity.YOUTUBE_PLAYLIST);
                break; default:
        }

        if(intent != null) {
            startActivity(intent);
        }
    }
}
*/
/* SOURCE FOR Android YouTube Player View Library by Pierfrancesco Soffritti:
https://github.com/PierfrancescoSoffritti/android-youtube-player

INSPIRATION CODE FROM: https://www.youtube.com/watch?v=qzcGfN9S_QY&ab_channel=AbdulAzizAhwan
 */