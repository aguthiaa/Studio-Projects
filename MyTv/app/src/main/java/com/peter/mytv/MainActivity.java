package com.peter.mytv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity {

    private final String API_KEY="AIzaSyA0ZY3S5vCq7EOfo5_E_q4to5VWptr6WIY";

    String channel_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent=getIntent();
        channel_1=intent.getStringExtra("channel");

        YouTubePlayerView yPlayer=(YouTubePlayerView)findViewById(R.id.youtube_player);
        YouTubePlayer.OnInitializedListener playerHandler =new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo(channel_1);
//                //iyDabk1SFUE ---KTN
//                //jL8uDJJBjMA ---Aljazeera
//                //rF0qJ8nhs8o ---CNN
//                //AdysK2T4Qe8 ---k24



            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        yPlayer.initialize(API_KEY,playerHandler);
    }
}
