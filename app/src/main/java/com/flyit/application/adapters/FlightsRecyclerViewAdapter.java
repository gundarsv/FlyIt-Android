package com.flyit.application.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.models.Flight;

import java.util.ArrayList;

public class FlightsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity context;
    public ArrayList<Flight> flightArrayList;

    public FlightsRecyclerViewAdapter(Activity context, ArrayList<Flight> flightArrayList) {
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return flightArrayList.size();
    }

}

