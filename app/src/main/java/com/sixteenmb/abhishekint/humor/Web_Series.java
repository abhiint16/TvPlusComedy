package com.sixteenmb.abhishekint.humor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by abhishekint on 06-05-2017.
 */

public class Web_Series extends Fragment {

    ProgressDialog progressDialog;
    Web_Series_Adapter web_series_adapter=new Web_Series_Adapter();
    View view;
    RecyclerView recyclerViewOfWebSeries;
    RecyclerView.LayoutManager layoutManager;
    Web_Series_Data web_series_data;
    String tabName="Web";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.web_series,container,false);

        recyclerViewOfWebSeries=(RecyclerView)view.findViewById(R.id.web_series_recycler_view);
        layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerViewOfWebSeries.setLayoutManager(layoutManager);
        recyclerViewOfWebSeries.setItemAnimator(new DefaultItemAnimator());
        recyclerViewOfWebSeries.setHasFixedSize(true);

        web_series_data=new Web_Series_Data(web_series_adapter,recyclerViewOfWebSeries);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");

        progressDialog.show();
        web_series_data.setItem();


        return view;
    }


    class Web_Series_Adapter extends RecyclerView.Adapter<Web_Series_Adapter.ViewHolder>
    {

        @Override
        public Web_Series_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.web_series_recycler_item,parent,false);
            progressDialog.cancel();
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(Web_Series_Adapter.ViewHolder holder, int position) {

            holder.networkImageView.setImageUrl(web_series_data.listImgID.get(position),web_series_data.imageLoader);
            //holder.networkImageView.setScaleType(NetworkImageView.ScaleType.CENTER_CROP);

            holder.networkImageView.setScaleType(NetworkImageView.ScaleType.FIT_XY);
            holder.textView.setText(web_series_data.listWebSeriesName.get(position));
        }

        @Override
        public int getItemCount() {
            return web_series_data.listId.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            NetworkImageView networkImageView;
            TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);

                networkImageView=(NetworkImageView)itemView.findViewById(R.id.web_series_recycler_item_imageview);
                textView=(TextView)itemView.findViewById(R.id.web_series_recycler_item_textview);
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {

                int position=getAdapterPosition();
                Intent intent=new Intent(getActivity(),Youtube_recycler_view.class);
                intent.putExtra("tabName",tabName);
                intent.putExtra("web_series_name",web_series_data.listWebSeriesName.get(position));

                startActivity(intent);

            }
        }
    }
}
