package com.flyit.application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.adapters.NewsRecyclerViewAdapter;
import com.flyit.application.models.News;
import com.flyit.application.models.Resource;
import com.flyit.application.viewModels.NewsViewModel;

import java.util.ArrayList;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsViewModel newsViewModel;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_news_list);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        newsViewModel.getNewsByAirportIata(getArguments().getString("Departure_IATA")).observe(getViewLifecycleOwner(), new Observer<Resource<ArrayList<News>>>() {
            @Override
            public void onChanged(Resource<ArrayList<News>> arrayListResource) {
                if (arrayListResource.getStatus().equals(Resource.Status.SUCCESS)) {
                    newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getActivity(), arrayListResource.getData());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(newsRecyclerViewAdapter);
                } else {
                    Toast.makeText(getActivity(), arrayListResource.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }
}
