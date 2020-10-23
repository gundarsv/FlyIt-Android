package com.flyit.application.adapters;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.fragments.ControlCenterMenuFragment;
import com.flyit.application.fragments.NewsItemFragment;
import com.flyit.application.models.News;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private FragmentActivity context;
    public ArrayList<News> newsArrayList;
    private FragmentManager fragmentManager;

    public NewsRecyclerViewAdapter(FragmentActivity context, ArrayList<News> newsArrayList){
        this.context = context;
        this.newsArrayList = newsArrayList;
        this.fragmentManager = context.getSupportFragmentManager();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.list_item_news, parent, false);
        return new NewsRecyclerViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        NewsRecyclerViewHolder viewHolder = (NewsRecyclerViewHolder) holder;

        viewHolder.newsTitle.setText(news.getTitle());
        Picasso.get().load(news.getImageurl()).into(viewHolder.newsImage);

        viewHolder.newsReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("News_ImageURL", news.getImageurl());
                bundle.putString("News_Body", news.getBody());
                bundle.putString("News_Title", news.getTitle());

                FragmentTransaction ft = fragmentManager.beginTransaction();

                Fragment frag = new NewsItemFragment();
                frag.setArguments(bundle);
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_container, frag);
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }
}
