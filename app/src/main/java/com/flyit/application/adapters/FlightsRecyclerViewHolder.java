package com.flyit.application.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.fragments.ControlCenterMenuFragment;
import com.flyit.application.fragments.NewsFragment;
import com.flyit.application.fragments.utils.FragmentUtils;

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
        private FragmentManager fragmentManager;

        public FlightsRecyclerViewHolder(@NonNull View itemView, FragmentActivity context) {
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
            this.fragmentManager = context.getSupportFragmentManager();

            mFlightItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putString("Destination_IATA", ArrivalAirport.getText().toString());
                    bundle.putString("Departure_IATA", DepartureAirport.getText().toString());

                    FragmentTransaction ft = fragmentManager.beginTransaction();

                    Fragment frag = new ControlCenterMenuFragment();
                    frag.setArguments(bundle);
                    ft.addToBackStack(null);
                    ft.replace(R.id.fragment_container, frag);
                    ft.commit();
                }
            });

        }
    }

