package com.sixteenmb.abhishekint.humor;

import android.support.v7.widget.RecyclerView;

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
 * Created by abhishekint on 13-05-2017.
 */

public class News_data  {

    List<String> listId=new ArrayList<>();
    List<String> listShowName=new ArrayList<>();
    List<String> listImgID=new ArrayList<>();

    News.News_Adapter news_adapter;
    RecyclerView recyclerView;
    ImageLoader imageLoader;
    String URL_THUMB="http://abhishekint.16mb.com/webspluscomedy/news/retrieve.php";

    public  News_data(News.News_Adapter news_adapter,RecyclerView recyclerView)
    {
        imageLoader=AppController.getmInstance().getmImageLoader();
        this.news_adapter=news_adapter;
        this.recyclerView=recyclerView;
    }


    void setItem()
    {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.POST, URL_THUMB, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("server_response");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String id=jsonObject.getString("id");
                        String show_name=jsonObject.getString("show_name");
                        String img_id=jsonObject.getString("img_id");

                        listId.add(id);
                        listShowName.add(show_name);
                        listImgID.add(img_id);


                    }

                    recyclerView.setAdapter(news_adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        AppController.getmInstance().addToRequestQueue(jsonObjectRequest);
    }


}
