package com.sixteenmb.abhishekint.humor;

import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abhishekint on 09-05-2017.
 */

public class Youtube_Recycler_item_data {



        List<String> listId=new ArrayList<>();
        List<String> listWebSeriesName=new ArrayList<>();
        List<String> listImgID=new ArrayList<>();
        List<String> listVideoId=new ArrayList<>();
        List<String> listEpisodeName=new ArrayList<>();
        Youtube_recycler_view.YoutubeRecyclerView_Adapter youtubeRecyclerView_adapter;
        RecyclerView recyclerView;
        ImageLoader imageLoader;
        String web_name;


    public   String URL_THUMB;

    public Youtube_Recycler_item_data(Youtube_recycler_view.YoutubeRecyclerView_Adapter youtubeRecyclerView_adapter,RecyclerView recyclerView,String s)
        {

            web_name=s;
            imageLoader=AppController.getmInstance().getmImageLoader();
            this.youtubeRecyclerView_adapter=youtubeRecyclerView_adapter;
            this.recyclerView=recyclerView;

        }

    void setItem() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_THUMB, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                finalGet(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("web_series_name",web_name);
                return params;
            }
        };

        AppController.getmInstance().addToRequestQueue(stringRequest);
    }

    public String getURL(String s) {
        String temp=null;
        if(s.equals("Web"))
        {
            return "http://abhishekint.16mb.com/webspluscomedy/web/recyclerview_youtube/retrieve.php";
        }
        else if(s.equals("Comedy"))
        {
            return "http://abhishekint.16mb.com/webspluscomedy/comedy/recyclerview_youtube/retrieve2.php";
        }
        else  if (s.equals("Talk"))
        {
            return "http://abhishekint.16mb.com/webspluscomedy/news/recyclerview_youtube/retrieve2.php";
        }

        return temp;
    }

    void finalGet(String response)
      {
          try {
              JSONObject jsonObject=new JSONObject(response);
              JSONArray jsonArray=jsonObject.getJSONArray("server_response");

              for(int i=0;i<jsonArray.length();i++)
              {
                  JSONObject jsonObject1=(JSONObject)jsonArray.get(i);
                  String id=jsonObject1.getString("id");
                  String web_series_name=jsonObject1.getString("web_series_name");
                  String img_id=jsonObject1.getString("img_id");
                  String video_id=jsonObject1.getString("video_id");
                  String web_series_episode_name=jsonObject1.getString("web_series_episode_name");

                  listId.add(id);
                  listWebSeriesName.add(web_series_name);
                  listImgID.add(img_id);
                  listVideoId.add(video_id);
                  listEpisodeName.add(web_series_episode_name);

                  System.out.print(listWebSeriesName);
                  System.out.print(listEpisodeName);

              }

              recyclerView.setAdapter(youtubeRecyclerView_adapter);

          } catch (JSONException e) {
              e.printStackTrace();
          }
    }
}
