package com.sixteenmb.abhishekint.humor;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by abhishekint on 20-05-2017.
 */

public class Youtube_video_play extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView youTubePlayerView;
    final String API_KEY="AIzaSyAB-5qUbSkM629ZcB0jCBK-WGGWPS5zZ90";
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // If the Android version is lower than Jellybean, use this call to hide
        // the status bar.
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        setContentView(R.layout.youtube_video_play);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube_playerview);
        youTubePlayerView.initialize(API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        // loadVideo() will auto play video
        // Use cueVideo() method, if you don't want to play it automatically
        youTubePlayer.loadVideo(getIntent().getStringExtra("video_id"));
        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        youTubePlayer.setShowFullscreenButton(false);
        youTubePlayer.setFullscreen(true);
        //When you flip the screen, the activity that was playing the video
        //gets destroyed, and a new activity gets created with everything reset. Its a feature, not a bug. :)

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
       /* Intent intent=new Intent(Youtube_video_play.this,MainActivity.class);
        startActivity(intent);*/
    }

 /*   @Override
    public void onBackPressed() {
        if(youTubePlayerView.ini)
        super.onBackPressed();

    }*/
   // Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
}
