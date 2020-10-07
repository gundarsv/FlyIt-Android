package com.flyit.application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.flyit.application.R;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.models.Flight;
import com.flyit.application.models.FlightSearch;
import com.flyit.application.models.SearchForFlight;
import com.flyit.application.viewModels.SearchForFlightViewModel;

public class SearchForFlightFragment extends Fragment {

    private FragmentManager fragmentManager;
    private SearchForFlightViewModel searchForFlightViewModel;
    private SearchView mSearchView;
    private TextView mSearchFlightNo;
    private Button mButtonSearch;
    private Button mbuttonGoToFlights;
    private Button mButtonAddFlight;
    private ProgressBar maddFlightProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchforflights, container, false);

        mSearchView = view.findViewById(R.id.searchFlight);
        mSearchFlightNo = view.findViewById(R.id.searchFlightNo);
        mButtonSearch = view.findViewById(R.id.buttonSearch);
        mbuttonGoToFlights = view.findViewById(R.id.buttonGoToFlights);
        mButtonAddFlight = view.findViewById(R.id.addFlight);
        maddFlightProgressBar = view.findViewById(R.id.addFlightProgressBar);

        searchForFlightViewModel = new ViewModelProvider(this).get(SearchForFlightViewModel.class);

        this.fragmentManager = getActivity().getSupportFragmentManager();

        mbuttonGoToFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new FlightsFragment(), "FlightsFragment");
            }
        });

       mButtonSearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               searchForFlightViewModel.searchFlight(mSearchView.getQuery().toString());
           }
       });

        searchForFlightViewModel.getSearchedFlights().observe(getViewLifecycleOwner(), new Observer<FlightSearch>() {
            @Override
            public void onChanged(FlightSearch flight) {
                mSearchFlightNo.setText(flight.getFlightNo());
            }
        });

        searchForFlightViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    maddFlightProgressBar.setVisibility(View.VISIBLE);
                }
                else {
                    maddFlightProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        mButtonAddFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchForFlightViewModel.addFlight(mSearchFlightNo.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
