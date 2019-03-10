package com.sixteenmb.abhishekint.humor;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by abhishekint on 24-05-2017.
 */

public class AboutUs extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setSupportActionBar(toolbar);

getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(20);
        toolbar.setTitleTextColor(getResources().getColor(R.color.tabStripColor));
        getSupportActionBar().setTitle("About Us");

    }
}
