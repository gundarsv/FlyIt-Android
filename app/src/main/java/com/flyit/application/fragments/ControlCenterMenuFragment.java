package com.flyit.application.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        String destination_iata=getArguments().getString("Destination_IATA");
        String departure_iata=getArguments().getString("Departure_IATA");
        Log.d("Clicked", destination_iata);
        Log.d("Clicked", departure_iata);
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
                Bundle bundle=new Bundle();
                bundle.putString("Destination_IATA", getArguments().getString("Destination_IATA"));
                bundle.putString("Departure_IATA", getArguments().getString("Departure_IATA"));

                FragmentTransaction ft = fragmentManager.beginTransaction();

                Fragment frag = new NewsFragment();
                frag.setArguments(bundle);
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_container, frag);
                ft.commit();
            }
        });


        fragmentManager = getActivity().getSupportFragmentManager();

        return view;

    }

}
