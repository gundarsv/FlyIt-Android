package com.flyit.application.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.fragments.NewsItemFragment;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.models.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<News> newsArrayList;
    private FragmentActivity context;
    private FragmentManager fragmentManager;

    public NewsRecyclerViewAdapter(FragmentActivity context, ArrayList<News> newsArrayList) {
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
                Bundle bundle = new Bundle();
                bundle.putString("News_ImageURL", news.getImageurl());
                bundle.putString("News_Body", news.getBody());
                bundle.putString("News_Title", news.getTitle());

                FragmentUtils.changeFragment(context.getViewModelStore(), fragmentManager, new NewsItemFragment(), "NewsItemFragment", bundle, R.id.fragment_container);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }
}
