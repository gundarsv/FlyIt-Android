package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.Flight;
import com.flyit.application.models.Resource;
import com.flyit.application.repositories.FlightsRepository;

import java.util.ArrayList;
import java.util.List;


public class FlightViewModel extends AndroidViewModel {

    private FlightsRepository flightsRepository;
    private MutableLiveData<Resource<ArrayList<Flight>>> flight;

    public FlightViewModel(@NonNull Application application) {
        super(application);
        this.flightsRepository = FlightsRepository.getFLightsRepository(application.getApplicationContext());
    }

    public LiveData<Resource<ArrayList<Flight>>> getFlight() {
        if(flight == null) {
            this.flight = flightsRepository.getFlights();
        }
        return this.flight;
    }

}
