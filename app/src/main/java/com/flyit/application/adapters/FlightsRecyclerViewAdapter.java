package com.flyit.application.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.models.DepartureDestination;
import com.flyit.application.models.Flight;

import java.lang.reflect.Array;
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
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Flight flight = flightArrayList.get(position);
        RecyclerViewViewHolder viewViewHolder = (RecyclerViewViewHolder) holder;

        viewViewHolder.Date.setText(flight.getDate());
        viewViewHolder.FlightNo.setText(flight.getFlightNo());
        viewViewHolder.Terminal.setText(flight.getDeparture().getTerminal());
        viewViewHolder.Gate.setText(flight.getStatus());
        viewViewHolder.DepartureTime.setText(flight.getDeparture().getScheduled());
        viewViewHolder.ArrivalTime.setText(flight.getDestination().getScheduled());
    }

    @Override
    public int getItemCount() {
        return flightArrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView Date;
        TextView FlightNo;
        TextView Terminal;
        TextView Gate;
        TextView DepartureTime;
        TextView ArrivalTime;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);

            Date = itemView.findViewById(R.id.Date);
            FlightNo = itemView.findViewById(R.id.flightNo);
            Terminal = itemView.findViewById(R.id.terminal);
            Gate = itemView.findViewById(R.id.gate);
            DepartureTime = itemView.findViewById(R.id.departureTime);
            ArrivalTime = itemView.findViewById(R.id.arrivalTime);

        }
    }
}

