package com.flyit.application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.adapters.FlightsRecyclerViewAdapter;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.models.Flight;
import com.flyit.application.models.Resource;
import com.flyit.application.models.User;
import com.flyit.application.viewModels.FlightViewModel;
import com.flyit.application.viewModels.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FlightsFragment extends Fragment {
    private FloatingActionButton mMenuFab, mSearchFab, mControlCenterFAB, mSignOutFab;
    private TextView searchFabText, controlCenterFabText, signOutTextFab;
    private Boolean isAllFabVisible;
    private ImageView greyBackground;
    private FragmentManager fragmentManager;
    private FlightViewModel flightViewModel;
    private UserViewModel userViewModel;
    private RecyclerView recyclerView;
    private FlightsRecyclerViewAdapter flightsRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flights, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_flights_list);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        flightViewModel = new ViewModelProvider(this).get(FlightViewModel.class);

        this.fragmentManager = getActivity().getSupportFragmentManager();

        flightViewModel.getFlight().observe(getViewLifecycleOwner(), new Observer<Resource<ArrayList<Flight>>>() {
            @Override
            public void onChanged(Resource<ArrayList<Flight>> listResource) {
                if (listResource.getStatus().equals(Resource.Status.SUCCESS)) {
                    flightsRecyclerViewAdapter = new FlightsRecyclerViewAdapter(getActivity(), listResource.getData());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(flightsRecyclerViewAdapter);
                } else {
                    Toast.makeText(getActivity(), listResource.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        // Floating Button settings

        // This FAB button is the Parent
        mMenuFab = view.findViewById(R.id.menufloatingbutton);

        mSearchFab = view.findViewById(R.id.floatingsearchflightsbutton);
        mControlCenterFAB = view.findViewById(R.id.floatingControlCenterButton);
        mSignOutFab = view.findViewById(R.id.floatingSignOutFab);

        searchFabText = view.findViewById(R.id.textFabSearchFlights);
        controlCenterFabText = view.findViewById(R.id.textFabControlCenter);
        signOutTextFab = view.findViewById(R.id.textFabSignOut);

        greyBackground = view.findViewById(R.id.lightGreyBackground);


        mSearchFab.setVisibility(View.GONE);
        mControlCenterFAB.setVisibility(View.GONE);
        mSignOutFab.setVisibility(View.GONE);
        searchFabText.setVisibility(View.GONE);
        controlCenterFabText.setVisibility(View.GONE);
        signOutTextFab.setVisibility(View.GONE);
        greyBackground.setVisibility(View.GONE);

        isAllFabVisible = false;

        mMenuFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabVisible) {
                    mSearchFab.show();
                    mControlCenterFAB.show();
                    mSignOutFab.show();
                    greyBackground.setVisibility(View.VISIBLE);
                    searchFabText.setVisibility(View.VISIBLE);
                    controlCenterFabText.setVisibility(View.VISIBLE);
                    signOutTextFab.setVisibility(View.VISIBLE);
                    isAllFabVisible = true;
                } else {
                    mSearchFab.hide();
                    mControlCenterFAB.hide();
                    mSignOutFab.hide();
                    greyBackground.setVisibility(View.GONE);
                    searchFabText.setVisibility(View.GONE);
                    controlCenterFabText.setVisibility(View.GONE);
                    signOutTextFab.setVisibility(View.GONE);

                    isAllFabVisible = false;
                }
            }
        });

        mSearchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new SearchForFlightFragment(), "SearchForFlightsFragment", null, R.id.fragment_container);
            }
        });

        mControlCenterFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new ControlCenterMenuFragment(), "ControlCenter", null, R.id.fragment_container);
            }
        });

        mSignOutFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewModel.signOut();
            }
        });

        userViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<Resource<User>>() {
            @Override
            public void onChanged(Resource<User> userResource) {
                if (userResource == null) {
                    FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new SignInFragment(), "LoginFragment", null, R.id.fragment_container);
                } else if (userResource.getStatus().equals(Resource.Status.UNAUTHORIZED)) {
                    FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new SignInFragment(), "LoginFragment", null, R.id.fragment_container);
                } else {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("Current_UserName", userResource.getData().getEmail()).apply();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
