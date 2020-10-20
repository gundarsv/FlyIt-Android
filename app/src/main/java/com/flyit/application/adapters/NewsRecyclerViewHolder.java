package com.flyit.application.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;

public class NewsRecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView newsImage;
    TextView newsTitle;
    TextView newsReadMore;

    public NewsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        newsImage = itemView.findViewById(R.id.newsImage);
        newsTitle = itemView.findViewById(R.id.newsTitle);
        newsReadMore = itemView.findViewById(R.id.newsReadMore);
    }
}
