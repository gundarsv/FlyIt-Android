package com.flyit.application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.flyit.application.R;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AirportFragment extends Fragment {
    private BottomNavigationView mBottomNavigationView;
    private FragmentManager mFragmentManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_airport, container, false);

        mBottomNavigationView = view.findViewById(R.id.bottomNavigationView);

        initFragment();

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new ControlCenterMenuFragment(), "ControlCenterMenuFragment", getArguments(), R.id.fragment_container);
            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.destination_item:
                        changeFragment(getArguments().getString("Destination_IATA"));
                        return true;
                    case R.id.departure_item:
                        changeFragment(getArguments().getString("Departure_IATA"));
                        return true;
                    default:
                        return false;
                }
            }
        });

        return view;
    }

    private void initFragment() {
        this.mFragmentManager = getActivity().getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putString("airport_iata", getArguments().getString("Departure_IATA"));

        Fragment fragment = new AirportInfoFragment();
        fragment.setArguments(bundle);

        FragmentTransaction ft = this.mFragmentManager.beginTransaction();
        ft.add(R.id.airport_info_container, fragment);
        ft.commit();
    }

    private void changeFragment(String iata) {
        Bundle bundle = new Bundle();
        bundle.putString("airport_iata", iata);
        FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new AirportInfoFragment(), "AirportInfoFragment", bundle, R.id.airport_info_container);
    }
}
