package com.sixteenmb.abhishekint.humor;




/**
 * Created by abhishekint on 09-05-2017.
 */

/*
public class Youtube_PlayerView extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlayerStateChangeListener, YouTubePlayer.PlaybackEventListener, View.OnTouchListener, View.OnClickListener{

    String setTime;
    LinearLayout linearLayout;

    YouTubePlayer youTubePlayer1;

    TextView textView,youtube_time;
    SeekBar seekBar;
    //Handler handler;
    int j;
    //Runnable runnable;
    RecyclerView.LayoutManager layoutManager;
    final String API_KEY="AIzaSyAB-5qUbSkM629ZcB0jCBK-WGGWPS5zZ90";
    YouTubePlayerView youTubePlayerView;
    ImageView play_pause_btn , config_change;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);


        setContentView(R.layout.youtube_recycler_view);

        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube_playerview);
        youTubePlayerView.initialize(API_KEY,this);

*/
/*
        textView=(TextView)findViewById(R.id.youtube_playerview_tv);
        youtube_time=(TextView)findViewById(R.id.youtube_time);

        play_pause_btn=(ImageView) findViewById(R.id.play_pause_button);
        play_pause_btn.setOnClickListener(this);

        config_change=(ImageView)findViewById(R.id.config_change);
        config_change.setOnClickListener(this);*//*



      //  handler=new Handler();
    //    seekBar=(SeekBar)findViewById(R.id.seekbar);

      */
/*  this.runOnUiThread(this);
        seekBar.setOnSeekBarChangeListener(this);
*//*


  //      linearLayout=(LinearLayout)findViewById(R.id.main_linear_lay);




        youTubePlayerView.setOnTouchListener(this);

        textView.setText(getIntent().getStringExtra("video_name"));
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            // loadVideo() will auto play video
        // Use cueVideo() method, if you don't want to play it automatically
        //youTubePlayer.loadVideo(getIntent().getStringExtra("video_id"));
        youTubePlayer1=youTubePlayer;
        youTubePlayer1.setPlayerStateChangeListener(this);
        youTubePlayer1.setShowFullscreenButton(true);
        youTubePlayer1.setManageAudioFocus(true);
        youTubePlayer1.setPlaybackEventListener(this);
        if(!b) {
            youTubePlayer1.loadVideo(getIntent().getStringExtra("video_id"));
  //          seekBar.setMax(youTubePlayer1.getDurationMillis());

        }

*/
/*
        seekBar.setMax(youTubePlayer1.getDurationMillis());
        playCycle();*//*



        if (null ==youTubePlayer1 ) return;
        String formattedTime = formatTime(youTubePlayer1.getDurationMillis());
        textView.setText(formattedTime);
        youtube_time.setText(formattedTime);
        //youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);

        youTubePlayer1.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        //When you flip the screen, the activity that was playing the video
        //gets destroyed, and a new activity gets created with everything reset. Its a feature, not a bug. :)

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }


    private String formatTime(int millis) {
        int seconds = millis / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        String hText="--:";
        if(hours==0)
            hText="--:";
        else if(hours!=0)
            hText=hours+":";

        String mtext=minutes%60 +":";
        String stext=seconds%60+" ";


        return hText+mtext+stext;
    }

    //////////////////////////////////////////////////////////////////////////////

    @Override
    public void onLoading() {
       youtube_time.setText("Loading");
    }

    @Override
    public void onLoaded(String s) {
        // s is the video id . this method returns the video id
//youtube_time.setText(youTubePlayer1.getDurationMillis()/1000);
    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }

    ////////////////////////////////////////////////////

    @Override
    public void onPlaying() {
//handler.postDelayed(runnable,100);
    }

    @Override
    public void onPaused() {
//handler.removeCallbacks(runnable);
    }

    @Override
    public void onStopped() {
//handler.removeCallbacks(runnable);
    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {
//handler.postDelayed(runnable,100);
    }
////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
        {
            if(linearLayout.getVisibility()==View.VISIBLE)
            {
                linearLayout.setVisibility(View.GONE);
            }
            else if(linearLayout.getVisibility()==View.GONE)
            {
                linearLayout.setVisibility(View.VISIBLE);
            }

        }
        return false;
    }

    @Override
    public void onClick(View view) {

    }
//////////////////////////////////////
    */
/*//*
/play pause btn controlling
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.play_pause_button)
        {
            if (null != youTubePlayer1&& youTubePlayer1.isPlaying())
            {
                youTubePlayer1.pause();
                play_pause_btn.setImageResource(R.drawable.play_button);
            }

            else if(null !=youTubePlayer1&& !youTubePlayer1.isPlaying())
            {
                youTubePlayer1.play();
                play_pause_btn.setImageResource(R.drawable.pause_button);
            }

        }
        if(view.getId()==R.id.config_change)
        {
            if(recyclerView.getVisibility()==View.VISIBLE )
            {
               // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                config_change.setImageResource(R.drawable.expand_less);
                recyclerView.setVisibility(View.GONE);
            }
            else if(recyclerView.getVisibility()== View.GONE )
            {
              //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                config_change.setImageResource(R.drawable.big);
                recyclerView.setVisibility(View.VISIBLE);
            }


        }
    }*//*

//////////////////////////////////////////////////////////////////////
    // seekbar controlling
    //@Override
   */
/* public void onProgressChanged(SeekBar seekBar,int i, boolean b) {
        j=i;

        long lengthPlayed = (youTubePlayer1.getDurationMillis() * i) / 100;
        youTubePlayer1.seekToMillis((int) lengthPlayed);
        //seekBar.postDelayed(runnable,lengthPlayed);
        //seekBar.setProgress(youTubePlayer1.getCurrentTimeMillis());
*//*
*/
/*


        if(b)
        {

        }*//*
*/
/*

*//*
*/
/*
runnable =new Runnable() {
    @Override
    public void run() {
        long lengthPlayed = (youTubePlayer1.getDurationMillis() * j) / 100;
        youTubePlayer1.seekToMillis((int) lengthPlayed);

    }
};
*//*
*/
/*
    }
*//*
*/
/*
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
*//*

    */
/*void playCycle()
    {
        seekBar.setProgress(youTubePlayer1.getCurrentTimeMillis());
        if(youTubePlayer1.isPlaying())
        {
            runnable=new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }*//*




    */
/*@Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayer1.release();
       // handler.removeCallbacks(runnable);
    }*//*


   */
/* @Override
    public void run() {
        if(youTubePlayer1!=null)
        {
    //        seekBar.setProgress(youTubePlayer1.getCurrentTimeMillis()/1000);
      //      handler.postDelayed(this,1000);
        }
    }
*//*

    /// / ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
*/
