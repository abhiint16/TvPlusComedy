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
 * Created by abhishekint on 13-05-2017.
 */

public class News extends Fragment{

    ProgressDialog progressDialog;
    News.News_Adapter news_adapter=new News.News_Adapter();
    View view;
    RecyclerView recyclerViewOfNews;
    RecyclerView.LayoutManager layoutManager;
    News_data news_data;
String tabName="Talk";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news,container,false);

        recyclerViewOfNews=(RecyclerView)view.findViewById(R.id.news_recycler_view);
        layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerViewOfNews.setLayoutManager(layoutManager);
        recyclerViewOfNews.setItemAnimator(new DefaultItemAnimator());
        recyclerViewOfNews.setHasFixedSize(true);


        news_data=new News_data(news_adapter,recyclerViewOfNews);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");

        progressDialog.show();
        news_data.setItem();
        return view;

    }

    class News_Adapter extends RecyclerView.Adapter<News_Adapter.ViewHolder>
    {

        @Override
        public News_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_item,parent,false);
            progressDialog.cancel();
            return new News.News_Adapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(News_Adapter.ViewHolder holder, int position) {
            holder.networkImageView.setImageUrl(news_data.listImgID.get(position),news_data.imageLoader);
            //holder.networkImageView.setScaleType(NetworkImageView.ScaleType.CENTER_CROP);

            holder.networkImageView.setScaleType(NetworkImageView.ScaleType.FIT_XY);
            holder.textView.setText(news_data.listShowName.get(position));
        }

        @Override
        public int getItemCount() {
            return news_data.listId.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            NetworkImageView networkImageView;
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                networkImageView=(NetworkImageView)itemView.findViewById(R.id.news_recycler_item_imageview);
                textView=(TextView)itemView.findViewById(R.id.news_recycler_item_textview);
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
                int position=getAdapterPosition();
                Intent intent=new Intent(getActivity(),Youtube_recycler_view.class);
                intent.putExtra("tabName",tabName);
                intent.putExtra("web_series_name",news_data.listShowName.get(position));

                startActivity(intent);
            }
        }
    }
}
