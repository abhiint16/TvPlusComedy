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

public class Comedy extends Fragment {

    Comedy.Comedy_Adapter comedy_adapter=new Comedy.Comedy_Adapter();
    View view;
    RecyclerView recyclerViewOfComedy;
    RecyclerView.LayoutManager layoutManager;
    Comedy_data comedy_data;

    String tabName="Comedy";
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.comedy,container,false);

        recyclerViewOfComedy=(RecyclerView)view.findViewById(R.id.comedy_recycler_view);
        layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerViewOfComedy.setLayoutManager(layoutManager);
        recyclerViewOfComedy.setItemAnimator(new DefaultItemAnimator());
        recyclerViewOfComedy.setHasFixedSize(true);


        comedy_data=new Comedy_data(comedy_adapter,recyclerViewOfComedy);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");

        progressDialog.show();
        comedy_data.setItem();
//getArguments().getString("sortingLang")
        return view;

    }

    class Comedy_Adapter extends RecyclerView.Adapter<Comedy_Adapter.ViewHolder>
    {

        @Override
        public Comedy_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.comedy_recycler_item,parent,false);
            progressDialog.cancel();
            return new Comedy.Comedy_Adapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(Comedy_Adapter.ViewHolder holder, int position) {
            holder.networkImageView.setImageUrl(comedy_data.listImgID.get(position),comedy_data.imageLoader);
            //holder.networkImageView.setScaleType(NetworkImageView.ScaleType.CENTER_CROP);

            holder.networkImageView.setScaleType(NetworkImageView.ScaleType.FIT_XY);
            holder.textView.setText(comedy_data.listArtistName.get(position));
        }

        @Override
        public int getItemCount() {
            return comedy_data.listId.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            NetworkImageView networkImageView;
            TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);

                networkImageView=(NetworkImageView)itemView.findViewById(R.id.comedy_recycler_item_imageview);
                textView=(TextView)itemView.findViewById(R.id.comedy_recycler_item_textview);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position=getAdapterPosition();
                Intent intent=new Intent(getActivity(),Youtube_recycler_view.class);
                intent.putExtra("tabName",tabName);
                intent.putExtra("web_series_name",comedy_data.listArtistName.get(position));
                startActivity(intent);
            }
        }
    }

}
