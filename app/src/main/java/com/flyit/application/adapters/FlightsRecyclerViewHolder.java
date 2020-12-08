package com.flyit.application.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;

public class FlightsRecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView Date;
    public TextView FlightNo;
    public TextView Terminal;
    public TextView Status;
    public TextView DepartureTime;
    public TextView ArrivalTime;
    public TextView DepartureAirport;
    public TextView ArrivalAirport;
    public TextView LastUpdatedTime;
    public ImageView settingsMenu;
    public Button mFlightItem;
    public Button mRefreshFlight;
    public Button mDeleteFlight;
    public ImageView greyBackgroundSettings;


    public FlightsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        Date = itemView.findViewById(R.id.Date);
        FlightNo = itemView.findViewById(R.id.flightNo);
        Terminal = itemView.findViewById(R.id.terminal);
        Status = itemView.findViewById(R.id.status);
        DepartureTime = itemView.findViewById(R.id.departureTime);
        ArrivalTime = itemView.findViewById(R.id.arrivalTime);
        DepartureAirport = itemView.findViewById(R.id.departureAirport);
        ArrivalAirport = itemView.findViewById(R.id.arrivalAirport);
        LastUpdatedTime = itemView.findViewById(R.id.lastUpdatedTime);
        settingsMenu = itemView.findViewById(R.id.flightItemSettings);
        mFlightItem = itemView.findViewById(R.id.flightItemButton);
        mRefreshFlight = itemView.findViewById(R.id.refreshFlightButton);
        mDeleteFlight = itemView.findViewById(R.id.deleteFlightButton);
        greyBackgroundSettings = itemView.findViewById(R.id.greyBackgroundSettings);
    }
}

