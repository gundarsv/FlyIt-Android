package com.flyit.application.adapters;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.fragments.ControlCenterMenuFragment;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.models.Flight;
import com.flyit.application.viewModels.FlightViewModel;

import java.util.List;

public class FlightsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Flight> flightArrayList;
    private FragmentActivity context;
    private FragmentManager fragmentManager;
    private Boolean isSettingsMenuVisible;
    private FlightViewModel flightViewModel;

    public FlightsRecyclerViewAdapter(FragmentActivity context, List<Flight> flightArrayList, FlightViewModel flightViewModel) {
        this.context = context;
        this.fragmentManager = context.getSupportFragmentManager();
        this.flightArrayList = flightArrayList;
        this.flightViewModel = flightViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.list_item_flight, parent, false);
        return new FlightsRecyclerViewHolder(rootView);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Flight flight = flightArrayList.get(position);
        FlightsRecyclerViewHolder viewViewHolder = (FlightsRecyclerViewHolder) holder;

        viewViewHolder.Date.setText(flight.getDateTimeOffset().getDayOfMonth() + "/"
                + flight.getDateTimeOffset().getMonthValue() + "/"
                + flight.getDateTimeOffset().getYear());
        viewViewHolder.FlightNo.setText(flight.getFlightNo());
        viewViewHolder.Terminal.setText(flight.getDeparture().getTerminal());
        viewViewHolder.Status.setText(flight.getStatus());
        viewViewHolder.DepartureTime.setText(flight.getDeparture().getDateTimeOffset().getDayOfMonth() + "/"
                + flight.getDeparture().getDateTimeOffset().getMonthValue() + "/"
                + flight.getDeparture().getDateTimeOffset().getYear() + " " + flight.getDeparture().getDateTimeOffset().getHour() + ":" + flight.getDeparture().getDateTimeOffset().getMinute());
        viewViewHolder.ArrivalTime.setText(flight.getDestination().getDateTimeOffset().getDayOfMonth() + "/"
                + flight.getDestination().getDateTimeOffset().getMonthValue() + "/"
                + flight.getDestination().getDateTimeOffset().getYear() + " " + flight.getDestination().getDateTimeOffset().getHour() + ":" + flight.getDestination().getDateTimeOffset().getMinute());
        viewViewHolder.DepartureAirport.setText(flight.getDeparture().getIata());
        viewViewHolder.ArrivalAirport.setText(flight.getDestination().getIata());
        viewViewHolder.LastUpdatedTime.setText(flight.getLastUpdatedDateTime().getDayOfMonth() + "/"
                + flight.getLastUpdatedDateTime().getMonthValue() + "/"
                + flight.getLastUpdatedDateTime().getYear() + " " + flight.getLastUpdatedDateTime().getHour() + ":" + flight.getLastUpdatedDateTime().getMinute() + ":" + flight.getLastUpdatedDateTime().getSecond());

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

        viewViewHolder.mRefreshFlight.setVisibility(View.GONE);
        viewViewHolder.mDeleteFlight.setVisibility(View.GONE);
        viewViewHolder.greyBackgroundSettings.setVisibility(View.GONE);


        isSettingsMenuVisible = false;


        viewViewHolder.settingsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSettingsMenuVisible) {
                    viewViewHolder.greyBackgroundSettings.setVisibility(View.VISIBLE);
                    viewViewHolder.mRefreshFlight.setVisibility(View.VISIBLE);
                    viewViewHolder.mDeleteFlight.setVisibility(View.VISIBLE);
                    isSettingsMenuVisible = true;
                } else {
                    viewViewHolder.greyBackgroundSettings.setVisibility(View.GONE);
                    viewViewHolder.mRefreshFlight.setVisibility(View.GONE);
                    viewViewHolder.mDeleteFlight.setVisibility(View.GONE);
                    isSettingsMenuVisible = false;
                }
            }
        });

        viewViewHolder.mRefreshFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flight flight = flightArrayList.get(position);
                flightViewModel.refreshFlight(flight.getId());

                viewViewHolder.greyBackgroundSettings.setVisibility(View.GONE);
                viewViewHolder.mRefreshFlight.setVisibility(View.GONE);
                viewViewHolder.mDeleteFlight.setVisibility(View.GONE);
                isSettingsMenuVisible = false;

                // Toast.makeText(view.getContext(), "Flight number: " + flight.getFlightNo() + " was updated", Toast.LENGTH_LONG).show();
            }
        });


        viewViewHolder.mDeleteFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flight flight = flightArrayList.get(position);
                flightViewModel.deleteFlight(flight.getId());

                viewViewHolder.greyBackgroundSettings.setVisibility(View.GONE);
                viewViewHolder.mRefreshFlight.setVisibility(View.GONE);
                viewViewHolder.mDeleteFlight.setVisibility(View.GONE);
                isSettingsMenuVisible = false;

                //  Toast.makeText(view.getContext(), "Flight number: " + flight.getFlightNo() + " was deleted", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return flightArrayList.size();
    }

}

