package com.flyit.application.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.flyit.application.EntertainmentActivity;
import com.flyit.application.R;
import com.flyit.application.fragments.utils.FragmentUtils;

public class ControlCenterMenuFragment extends Fragment {
    private FragmentManager fragmentManager;
    private Button mButtonSearchFlights;
    private Button mButtonMyFlights;
    private Button mButtonAirportInfo;
    private Button mButtonNews;
    private Button mButtonGames;
    private Button mButtonChat;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controlcentermenu, container, false);

        mButtonGames = view.findViewById(R.id.buttonGames);
        mButtonNews = view.findViewById(R.id.buttonNews);

        mButtonGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EntertainmentActivity.class);
                startActivity(intent);
            }
        });

        mButtonNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new NewsFragment(), "NewsFragment");
            }
        });


        fragmentManager = getActivity().getSupportFragmentManager();

        return view;

    }

}
