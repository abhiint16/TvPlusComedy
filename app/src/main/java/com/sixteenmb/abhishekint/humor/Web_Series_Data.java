package com.sixteenmb.abhishekint.humor;

import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishekint on 06-05-2017.
 */

public class Web_Series_Data {

    List<String> listId=new ArrayList<>();
    List<String> listWebSeriesName=new ArrayList<>();
    List<String> listImgID=new ArrayList<>();
    Web_Series.Web_Series_Adapter web_series_adapter;
    RecyclerView recyclerView;
    ImageLoader imageLoader;

    private  String URL_THUMB="http://abhishekint.16mb.com/webspluscomedy/web/retrieve.php";

    public Web_Series_Data(Web_Series.Web_Series_Adapter web_series_adapter,RecyclerView recyclerView)
    {
        imageLoader=AppController.getmInstance().getmImageLoader();
        this.web_series_adapter=web_series_adapter;
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
        }) ;

        AppController.getmInstance().addToRequestQueue(stringRequest);

    }


    void finalGet(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject1=(JSONObject)jsonArray.get(i);
                String id=jsonObject1.getString("id");
                String web_series_name=jsonObject1.getString("web_series_name");
                String img_id=jsonObject1.getString("img_id");

                listId.add(id);
                listWebSeriesName.add(web_series_name);
                listImgID.add(img_id);


            }


            recyclerView.setAdapter(web_series_adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
