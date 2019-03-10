package com.sixteenmb.abhishekint.humor;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Window window;
    MyAdapter myAdapter;
    ViewPager viewPager ;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
CollapsingToolbarLayout collapsingToolbarLayout;
    NavigationView navigationView;
    TabLayout tabLayout;
ImageView left,right;
    Col_Recycler_Adapter col_recycler_adapter=new Col_Recycler_Adapter();
    Col_rec_data col_rec_data;
    boolean doubleBackToExitPressedOnce=false;
    Toolbar toolbar;
    TextView new_update;
    InterstitialAd mInterstitialAd;
    int adToShowNo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Log.e("inside mainactivyt","inside aminsactivty");
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);*/
        setContentView(R.layout.activity_main);


        //onNewIntent(getIntent());
        viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(2);
        myAdapter=new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);

        left=(ImageView)findViewById(R.id.left);
        right=(ImageView)findViewById(R.id.right);
        new_update=(TextView)findViewById(R.id.new_update);

        recyclerView=(RecyclerView)findViewById(R.id.col_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        col_rec_data=new Col_rec_data(recyclerView,col_recycler_adapter);
        col_rec_data.PrefetchData();


        window= getWindow();
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);

       // drawerLayout.s

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(20);
        getSupportActionBar().setTitle("Humorous");
        //toolbar.setSubtitle("vvvv");
        toolbar.setTitleTextColor(getResources().getColor(R.color.tabStripColor));
        toolbar.setCollapsible(true);
       // getSupportActionBar().
//        getSupportActionBar().setHideOnContentScrollEnabled(true);

        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.none));
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //below line is used to chnage the colcor of the strip
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tabStripColor));
        //below line is used to change the height of the strip
        tabLayout.setSelectedTabIndicatorHeight(5);
        //below is line is used to change the color of the tab
        tabLayout.setBackgroundColor(getResources().getColor(R.color.toolbar));
        //below one line is used to change color of tab text
        //tabLayout.setTabTextColors(getResources().getColor(R.color.tabTextUnselectedColor), getResources().getColor(R.color.tabTextSelectedColor));

        //tabLayout.getT
        tabLayout.setTabTextColors(getResources().getColor(R.color.tabTextUnselected), getResources().getColor(R.color.tabStripColor));

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager)
        {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                if(tab.getPosition()==0)
                {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.toolbar));

                }
                else if(tab.getPosition()==1)
                {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar2));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.toolbar2));
                }
                else if(tab.getPosition()==2)
                {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar3));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.toolbar3));
                }
            }
        });

        navigationView=(NavigationView)findViewById(R.id.drawer_navview);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.menu_aboutus:
                        Intent intent=new Intent(MainActivity.this,AboutUs.class);
                        startActivity(intent);

                        break;
                    case R.id.menu_feedback:
                        Intent emailIntent=new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        String[] s={"abhishekint16@gmail.com"};
                        emailIntent.putExtra(Intent.EXTRA_EMAIL,s);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Suggestion | Humor");
                        emailIntent.putExtra(Intent.EXTRA_TEXT,"");
                        emailIntent.setType("message/rfc822");//message/rfc822   text/plain
                        startActivity(Intent.createChooser(emailIntent,"Send EMAIL using...."));

                        break;
                    case R.id.menu_share:
                        Intent shareIntent=new Intent(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Humor");
                        shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getPackageName());
                        shareIntent.setType("text/plain");
                        startActivity(Intent.createChooser(shareIntent,"Complete action using ...."));
                        break;
                    case R.id.menu_star:
                        try{
                            Uri uri=Uri.parse("market://details?id="+getPackageName());
                            Intent intent1=new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent1);


                        }catch(ActivityNotFoundException e)
                        {

                            Uri uri=Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName());
                            Intent intent1=new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent1);

                        }
                        break;
                    case R.id.menu_appslist:
                        try{
                            Uri uri=Uri.parse("market://developer?id=abhiint");
                            Intent intent1=new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent1);


                        }catch(ActivityNotFoundException e)
                        {

                            Uri uri=Uri.parse("https://play.google.com/store/apps/developer?id=abhiint");
                            Intent intent1=new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent1);

                        }
                        break;
                    case R.id.menu_send:
                        Intent sendIntent=new Intent(MainActivity.this,Send_direct_msg.class);
                        startActivity(sendIntent);
                        break;

                    }



                return false;
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial));

        AdRequest adRequest = new AdRequest.Builder().build();
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);


    }

/*
    protected void onNewIntent(Intent intent) {
        String action = intent.getAction();
        String data = intent.getDataString();
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            String recipeId = data.substring(data.lastIndexOf("/") + 1);
            Uri contentUri = RecipeContentProvider.CONTENT_URI.buildUpon()
                    .appendPath(recipeId).build();
            showRecipe(contentUri);
            Toast.makeText(this,"aaaaaaaaaaa",Toast.LENGTH_LONG).show();
        }
    }
*/

    class Col_Recycler_Adapter extends RecyclerView.Adapter<Col_Recycler_Adapter.ViewHolder>
    {

        @Override
        public Col_Recycler_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e("inside oncreviewhol","inside oncreviewhol");
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.col_recy_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Col_Recycler_Adapter.ViewHolder holder, int position) {

            Log.e("inside onbind", "inside onbind");
            Log.e("kksannas"," "+col_rec_data.list_img_id+col_rec_data.list_web_series_episode_name);
            holder.networkImageView.setImageUrl(col_rec_data.list_img_id.get(position),col_rec_data.imageLoader);
            holder.textView.setText(col_rec_data.list_web_series_episode_name.get(position)+" ( "+col_rec_data
            .list_web_series_name.get(position)+" )");
            Log.e("leaving onbidn","leaaving onbine");
        }

        @Override
        public int getItemCount() {
            Log.e("inside itemcount"," "+col_rec_data.list_img_id.size());
            return col_rec_data.list_img_id.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            NetworkImageView networkImageView;
            TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);
                Log.e("inside viewhlder","inside vieholdr");
                networkImageView=(NetworkImageView)itemView.findViewById(R.id.col_recy_item_image);
                textView=(TextView)itemView.findViewById(R.id.col_recy_item_text);
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
                        adToShowNo++;
                        Intent intent=new Intent(getBaseContext(),Youtube_video_play.class);
                        intent.putExtra("video_id",col_rec_data.list_video_id.get(position));
                        intent.putExtra("video_name",col_rec_data.list_web_series_episode_name.get(position));
                        intent.putExtra("web_series_name",col_rec_data.list_web_series_name.get(position));
                        startActivity(intent);
                    }

                });
                if(mInterstitialAd.isLoaded()&&adToShowNo%3==0)
                    mInterstitialAd.show();
                else{
                    adToShowNo++;
                    Intent intent=new Intent(getBaseContext(),Youtube_video_play.class);
                    intent.putExtra("video_id",col_rec_data.list_video_id.get(position));
                    intent.putExtra("video_name",col_rec_data.list_web_series_episode_name.get(position));
                    intent.putExtra("web_series_name",col_rec_data.list_web_series_name.get(position));
                    startActivity(intent);}
            }
        }
    }


   /* public void init()
    {
        List<String> listImage=Splash_Screen.list_img_id;
        List<String> listText=Splash_Screen.list_web_series_name;
        col_pager.setAdapter(new Col_Pager_Adapter(MainActivity.this,listImage,listText));

    }*/

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_aboutus:
                Intent intent=new Intent(MainActivity.this,AboutUs.class);
                startActivity(intent);

                break;
            case R.id.menu_feedback:
                Intent emailIntent=new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                String[] s={"abhishekint16@gmail.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL,s);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Suggestion | Humor");
                emailIntent.putExtra(Intent.EXTRA_TEXT,"");
                emailIntent.setType("message/rfc822");//message/rfc822   text/plain
                startActivity(Intent.createChooser(emailIntent,"Send EMAIL using...."));

                break;
            case R.id.menu_share:
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Humor");
                shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getPackageName());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent,"Complete action using ...."));
                break;

            case R.id.menu_star:
                try{
                    Uri uri=Uri.parse("market://details?id="+getPackageName());
                    Intent intent1=new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent1);


                }catch(ActivityNotFoundException e)
                {

                    Uri uri=Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName());
                    Intent intent1=new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent1);

                }
                break;
            case R.id.menu_appslist:
                try{
                    Uri uri=Uri.parse("market://developer?id=abhiint");
                    Intent intent1=new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent1);


                }catch(ActivityNotFoundException e)
                {

                    Uri uri=Uri.parse("https://play.google.com/store/apps/developer?id=abhiint");
                    Intent intent1=new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent1);

                }
                break;
            case R.id.menu_send:
                Intent sendIntent=new Intent(MainActivity.this,Send_direct_msg.class);
                startActivity(sendIntent);
                break;

        }

        return true;
    }
}
