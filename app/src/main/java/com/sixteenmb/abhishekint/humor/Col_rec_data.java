package com.sixteenmb.abhishekint.humor;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-07-2017.
 */

public class Col_rec_data {

     List<String> list_web_series_name=new ArrayList<>();
     List<String> list_web_series_episode_name=new ArrayList<>();
     List<String> list_img_id=new ArrayList<>();
     List<String> list_video_id=new ArrayList<>();
    RecyclerView recyclerView;
    MainActivity.Col_Recycler_Adapter recycler_adapter;
ImageLoader imageLoader;
    Col_rec_data(RecyclerView recyclerView,MainActivity.Col_Recycler_Adapter recycler_adapter)
    {
        this.imageLoader=AppController.getmInstance().getmImageLoader();
        this.recyclerView=recyclerView;
        this.recycler_adapter=recycler_adapter;
    }



    void PrefetchData()
    {


        Log.e("inside doinback","inside doinback");
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, "https://abhishekint16.000webhostapp.com/retrieve2.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("inside onresponse","onresponse");
                    JSONArray jsonArray = response.getJSONArray("server_response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        //String id = jsonObject.getString("id");
                        String web_series_name = jsonObject.getString("web_series_name");
                        String web_series_episode_name = jsonObject.getString("web_series_episode_name");
                        String img_id = jsonObject.getString("img_id");
                        String video_id = jsonObject.getString("video_id");

                        list_img_id.add(img_id);
                        list_video_id.add(video_id);
                        list_web_series_episode_name.add(web_series_episode_name);
                        list_web_series_name.add(web_series_name);
                    }
                    Log.e("list vakue"," "+list_web_series_name+list_img_id);
                    Log.e("setting adapeter ","setting adpter");
                    recyclerView.setAdapter(recycler_adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("json fetchin gerror"," "+error);
            }
        }
        );
        Log.e("before appcontroller","before appconroellr");


        AppController.getmInstance().addToRequestQueue(objectRequest);



    }
}
