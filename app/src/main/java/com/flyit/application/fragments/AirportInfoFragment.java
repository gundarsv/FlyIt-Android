package com.flyit.application.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.flyit.application.R;
import com.flyit.application.models.Airport;
import com.flyit.application.viewModels.AirportInfoViewModel;

public class AirportInfoFragment extends Fragment {
    private TextView mAirportIata;
    private TextView mAirportName;
    private AirportInfoViewModel airportInfoViewModel;
    private ConstraintLayout mAirportInfoLayout;
    private Button mViewAirportMapButton;
    private ProgressBar mAirportInfoProgressBar;
    private String airportMapUrl;
    private TextView mRentingCompany;
    private TextView mRentingCompanyPhoneNumber;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_airport_info, container, false);

        mAirportIata = view.findViewById(R.id.airport_iata);
        mAirportName = view.findViewById(R.id.airport_name);
        mAirportInfoProgressBar = view.findViewById(R.id.airportInfoProgressBar);
        mAirportInfoLayout = view.findViewById(R.id.airport_info_layout);
        mViewAirportMapButton = view.findViewById(R.id.viewAirportMapButton);
        mRentingCompany = view.findViewById(R.id.renting_company);
        mRentingCompanyPhoneNumber = view.findViewById(R.id.renting_company_phone_number);

        mAirportInfoProgressBar.setVisibility(View.VISIBLE);
        mAirportInfoLayout.setVisibility(View.INVISIBLE);

        airportInfoViewModel = new ViewModelProvider(this).get(AirportInfoViewModel.class);

        airportInfoViewModel.getAirportByIata(getArguments().getString("airport_iata"));

        airportInfoViewModel.getAirport().observe(getViewLifecycleOwner(), new Observer<Airport>() {
            @Override
            public void onChanged(Airport airport) {
                if (airport != null) {
                    mAirportIata.setText(airport.getIata());
                    mAirportName.setText(airport.getName());
                    airportMapUrl = airport.getMapUrl();
                    mRentingCompany.setText(Html.fromHtml("<a href=" + airport.getRentingCompanyUrl() + "> " + airport.getRentingCompanyName()));
                    mRentingCompany.setMovementMethod(LinkMovementMethod.getInstance());
                    mRentingCompanyPhoneNumber.setText(airport.getRentingCompanyPhoneNo());
                }
                else {
                    mAirportInfoLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        airportInfoViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mAirportInfoLayout.setVisibility(View.INVISIBLE);
                    mAirportInfoProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mAirportInfoLayout.setVisibility(View.VISIBLE);
                    mAirportInfoProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        airportInfoViewModel.getMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }
        });

        mViewAirportMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri path = Uri.parse(airportMapUrl);
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setDataAndType(path, "application/pdf");
                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                try{
                    startActivity(pdfIntent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(getContext(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}