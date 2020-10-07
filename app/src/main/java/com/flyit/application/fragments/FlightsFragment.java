package com.flyit.application.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.adapters.FlightsRecyclerViewAdapter;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.models.Flight;
import com.flyit.application.models.Resource;
import com.flyit.application.viewModels.FlightViewModel;

import java.util.ArrayList;
import java.util.List;

public class FlightsFragment extends Fragment {

    private Button mNoFlightsButton;
    private FragmentManager fragmentManager;
    private FlightViewModel flightViewModel;
    private RecyclerView recyclerView;
    private FlightsRecyclerViewAdapter flightsRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flights, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_flights_list);

        mNoFlightsButton = view.findViewById(R.id.buttonNoFlights);

        flightViewModel = new ViewModelProvider(this).get(FlightViewModel.class);


        this.fragmentManager = getActivity().getSupportFragmentManager();


        mNoFlightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new SearchForFlightFragment(), "SearchForFlightsFragment");
            }
        });


        flightViewModel.getFlight().observe(getViewLifecycleOwner(), new Observer<Resource<ArrayList<Flight>>>() {
            @Override
            public void onChanged(Resource<ArrayList<Flight>> listResource) {
                if(listResource.getStatus().equals(Resource.Status.SUCCESS) ) {
                    flightsRecyclerViewAdapter = new FlightsRecyclerViewAdapter(getActivity(), listResource.getData());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(flightsRecyclerViewAdapter);
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
