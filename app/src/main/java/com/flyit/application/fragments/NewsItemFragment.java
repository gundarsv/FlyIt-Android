package com.flyit.application.fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flyit.application.R;
import com.flyit.application.viewModels.NewsViewModel;
import com.squareup.picasso.Picasso;

public class NewsItemFragment extends Fragment {
    private NewsViewModel newsViewModel;
    private ImageView news_image;
    private TextView news_title;
    private TextView news_body;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_item, container, false);
        news_image = view.findViewById(R.id.news_image);
        news_title = view.findViewById(R.id.news_title);
        news_body = view.findViewById(R.id.news_item_body);

        Picasso.get().load(getArguments().getString("News_ImageURL")).into(news_image);
        news_title.setText(getArguments().getString("News_Title"));
        news_body.setText(getArguments().getString("News_Body"));
        news_body.setMovementMethod(new ScrollingMovementMethod());


        return view;
    }
}
