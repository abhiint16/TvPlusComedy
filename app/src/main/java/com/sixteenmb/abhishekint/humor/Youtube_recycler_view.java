package com.sixteenmb.abhishekint.humor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by abhishekint on 20-05-2017.
 */

public class Youtube_recycler_view extends AppCompatActivity {

    AdView adView;
    ProgressDialog progressDialog;
    String web_series_name;
    YoutubeRecyclerView_Adapter youtubeRecyclerView_adapter=new YoutubeRecyclerView_Adapter();
    Youtube_Recycler_item_data youtube_recycler_item_data;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    InterstitialAd mInterstitialAd;
    int adShowno2=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
/*
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
        // status bar is hidden, so hide that too if necessary.*/
        setContentView(R.layout.youtube_recycler_view);

        mInterstitialAd = new InterstitialAd(this);
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_utubeview));

        AdRequest adRequest = new AdRequest.Builder().build();
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        toolbar=(Toolbar)findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.tabStripColor));
        getSupportActionBar().setElevation(20);
        web_series_name=getIntent().getStringExtra("web_series_name");
        getSupportActionBar().setTitle(web_series_name);

        recyclerView=(RecyclerView)findViewById(R.id.youtube_playview_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        layoutManager=new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);


        youtube_recycler_item_data=new Youtube_Recycler_item_data(youtubeRecyclerView_adapter,recyclerView,web_series_name);
        youtube_recycler_item_data.URL_THUMB=youtube_recycler_item_data.getURL(getIntent().getStringExtra("tabName"));
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");

        progressDialog.show();
        youtube_recycler_item_data.setItem();




    }


    class YoutubeRecyclerView_Adapter extends RecyclerView.Adapter<YoutubeRecyclerView_Adapter.ViewHolder>
    {

        @Override
        public YoutubeRecyclerView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_recycler_item,parent,false);
            progressDialog.cancel();
            adView=(AdView)findViewById(R.id.banner_adview);
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            adView.loadAd(adRequest);
            adView.setAdListener(new AdListener()
            {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }
            });
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(YoutubeRecyclerView_Adapter.ViewHolder holder, int position) {

            holder.networkImageView.setImageUrl(youtube_recycler_item_data.listImgID.get(position),youtube_recycler_item_data.imageLoader);
            holder.networkImageView.setScaleType(NetworkImageView.ScaleType.FIT_XY);
            holder.textView.setText(youtube_recycler_item_data.listEpisodeName.get(position));

        }

        @Override
        public int getItemCount() {
            return youtube_recycler_item_data.listImgID.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            NetworkImageView networkImageView;
            TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);
                networkImageView=(NetworkImageView)itemView.findViewById(R.id.youtube_recycler_item_networkview);
                textView=(TextView)itemView.findViewById(R.id.youtube_recycler_item_textview);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                final int position=getAdapterPosition();
                mInterstitialAd.setAdListener(new AdListener()
                {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        adShowno2++;
                        Intent intent=new Intent(getBaseContext(),Youtube_video_play.class);
                        intent.putExtra("video_id",youtube_recycler_item_data.listVideoId.get(position));
                        intent.putExtra("video_name",youtube_recycler_item_data.listEpisodeName.get(position));
                        intent.putExtra("web_series_name",youtube_recycler_item_data.listWebSeriesName.get(position));
                        startActivity(intent);
                    }
                });
                if(mInterstitialAd.isLoaded()&& adShowno2%3==0)
                    mInterstitialAd.show();
                else{
                    adShowno2++;
                Intent intent=new Intent(getBaseContext(),Youtube_video_play.class);
                intent.putExtra("video_id",youtube_recycler_item_data.listVideoId.get(position));
                intent.putExtra("video_name",youtube_recycler_item_data.listEpisodeName.get(position));
                intent.putExtra("web_series_name",youtube_recycler_item_data.listWebSeriesName.get(position));
                startActivity(intent);}
            }
        }
    }

}
