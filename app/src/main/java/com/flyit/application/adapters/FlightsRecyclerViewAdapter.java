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
import com.flyit.application.fragments.ControlCenterMenuFragment;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.models.Flight;

import java.util.ArrayList;

public class FlightsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<Flight> flightArrayList;
    private FragmentActivity context;
    private FragmentManager fragmentManager;

    public FlightsRecyclerViewAdapter(FragmentActivity context, ArrayList<Flight> flightArrayList) {
        this.context = context;
        this.fragmentManager = context.getSupportFragmentManager();
        this.flightArrayList = flightArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.list_item_flight, parent, false);
        return new FlightsRecyclerViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Flight flight = flightArrayList.get(position);
        FlightsRecyclerViewHolder viewViewHolder = (FlightsRecyclerViewHolder) holder;

        viewViewHolder.Date.setText(flight.getDate());
        viewViewHolder.FlightNo.setText(flight.getFlightNo());
        viewViewHolder.Terminal.setText(flight.getDeparture().getTerminal());
        viewViewHolder.Status.setText(flight.getStatus());
        viewViewHolder.DepartureTime.setText(flight.getDeparture().getScheduled());
        viewViewHolder.ArrivalTime.setText(flight.getDestination().getScheduled());
        viewViewHolder.DepartureAirport.setText(flight.getDeparture().getIata());
        viewViewHolder.ArrivalAirport.setText(flight.getDestination().getIata());

        viewViewHolder.mFlightItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Destination_IATA", flight.getDestination().getIata());
                bundle.putString("Departure_IATA", flight.getDeparture().getIata());
                bundle.putString("Destination_ICAO", flight.getDestination().getIcao());
                bundle.putString("Departure_ICAO", flight.getDeparture().getIcao());
                bundle.putInt("ChatroomId", flight.getChatroomId());

                FragmentUtils.changeFragment(context.getViewModelStore(), fragmentManager, new ControlCenterMenuFragment(), "ControlCenterMenuFragment", bundle, R.id.fragment_container);
            }
        });
    }

    @Override
    public int getItemCount() {
        return flightArrayList.size();
    }

}

