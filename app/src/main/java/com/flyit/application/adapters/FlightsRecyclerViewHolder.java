package com.flyit.application.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;

public class FlightsRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView Date;
        TextView FlightNo;
        TextView Terminal;
        TextView Status;
        TextView DepartureTime;
        TextView ArrivalTime;
        TextView DepartureAirport;
        TextView ArrivalAirport;
        ImageView settingsMenu;
        Button mFlightItem;

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
            settingsMenu = itemView.findViewById(R.id.flightItemSettings);
            mFlightItem = itemView.findViewById(R.id.flightItemButton);

        }
    }

