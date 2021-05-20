package com.example.humleporten.BeerLibrary;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.example.humleporten.MainScreen.BeerActivity;
import com.example.humleporten.MainScreen.MainActivity;
import com.example.humleporten.R;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    YouTubePlayerView mPlayer;
    String key ="AIzaSyCFUZiBhSzTw51PO4WeJF3qvp7doVrTrGo";
    String id = "VqNxYtM_Cf8";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        mPlayer =(YouTubePlayerView) findViewById(R.id.videoViewYT);
        mPlayer.initialize(key,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
    if (!wasRestored){
        youTubePlayer.cueVideo(id);
        youTubePlayer.play();
    }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
/*SOURCE https://www.youtube.com/watch?v=P156VjNAqjY&ab_channel=DailyTuition */