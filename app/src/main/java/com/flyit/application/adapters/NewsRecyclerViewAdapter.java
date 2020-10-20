package com.flyit.application.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.models.News;

import java.util.ArrayList;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    public ArrayList<News> newsArrayList;

    public NewsRecyclerViewAdapter(Activity context, ArrayList<News> newsArrayList){
        this.context = context;
        this.newsArrayList = newsArrayList;
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

        viewHolder.newsTitle.setText(news.getNewsTitle());

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }
}
